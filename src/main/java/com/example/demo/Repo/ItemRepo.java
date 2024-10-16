package com.example.demo.Repo;

import com.example.demo.Entity.ItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<ItemEntity, Long> {
    ItemEntity getByNtId(String userIdentifier);

    @Transactional
    @Modifying
    void deleteByItemType(String itemType);
}
