package com.example.demo.Repo;

import com.example.demo.Entity.AdminEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, Long> {
    AdminEntity findOneByAdminIDIgnoreCaseAndPassword(String emailId, String password);

    AdminEntity findByAdminID(String adminID);

    @Query(
            value = "SELECT ae.adminid AS adminId, at.access_tag AS accessTag FROM admin_entity ae INNER JOIN access_tags at ON ae.id = at.admin_id WHERE at.access_tag NOT IN ('USER')",
            nativeQuery = true
    )
    List<AdminRoleProjection> findByAdminRole();

    @Transactional
    @Modifying
    @Query("DELETE FROM AdminEntity a WHERE a.adminID = :adminID")
    void deleteByAdminID(@Param("adminID") String adminID);

    Optional<AdminEntity> findByPassword(String s);
}
