package com.example.demo.Repo;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.UserEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByNtId(String ntId);

    UserEntity findOneByEmailIgnoreCaseAndNtId(String emailId, String password);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserEntity u WHERE u.ntId = :ntId")
    void deleteByNtId(String ntId);

    UserEntity findEntityByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.ntId) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.manager) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<UserEntity> searchAll(String search, Pageable pageable);

    @Query(
            value = "SELECT * FROM user_entity WHERE LOWER(name) LIKE LOWER(CONCAT('%', :search, '%'))",
            nativeQuery = true
    )
    Page<UserEntity> searchNameKeyAll(@Param("search") String search, Pageable pageable);

    @Query(
            value = "SELECT * FROM user_entity WHERE LOWER(email_id) LIKE LOWER(CONCAT('%', :search, '%'))",
            nativeQuery = true
    )
    Page<UserEntity> searchMailKeyAll(@Param("search") String search, Pageable pageable);

    @Query(
            value = "SELECT * FROM user_entity WHERE LOWER(nt_id) LIKE LOWER(CONCAT('%', :search, '%'))",
            nativeQuery = true
    )
    Page<UserEntity> searchNtIdKeyAll(@Param("search") String search, Pageable pageable);

    @Query(
            value = "SELECT * FROM user_entity WHERE LOWER(manager) LIKE LOWER(CONCAT('%', :search, '%'))",
            nativeQuery = true
    )
    Page<UserEntity> searchManagerKeyAll(@Param("search") String search, Pageable pageable);

    @Query("SELECT u FROM UserEntity u WHERE u.ntId IN :ntIds")
    List<UserEntity> findByNtIdIn(@Param("ntIds") List<String> ntIds);

    @Transactional
    @Modifying
    void deleteByNtIdIn(List<String> ntIds);

    @Query("SELECT new com.example.demo.DTO.UserDto(u.deviceId, u.name, u.email, u.manager, u.ntId) FROM UserEntity u WHERE u.email = :email")
    UserDto findByEmail(@Param("email") String email);
}

