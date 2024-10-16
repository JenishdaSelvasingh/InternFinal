package com.example.demo.Repo;

import com.example.demo.Entity.AssetEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, String> {
    @Query("SELECT  assetID FROM AssetEntity WHERE userEntity IS NULL")
    List<String> getInactiveAssets();

    @Query("SELECT  assetType FROM AssetEntity WHERE userEntity IS NULL")
    List<String> getInactiveAssetTypes();

    @Query("SELECT a.assetType FROM AssetEntity a")
    List<String> getAllAssetTypes();

    AssetEntity findOneByAssetID(String id);
}
