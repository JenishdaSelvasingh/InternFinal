package com.example.demo.Service;

import com.example.demo.GenericResponse;
import com.example.demo.DTO.AssetDto;
import com.example.demo.DTO.IdDto;
import com.example.demo.Entity.AssetEntity;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface AssetService {
    List<AssetEntity> getAllData();

    AssetEntity createAsset(AssetEntity asset);

    String updateAsset(AssetDto asset, String id);

    String deleteAsset(String id);

    String deleteAllAssets();

    Page<AssetDto> getPageData(Integer page, Integer size);

    GenericResponse updateAssetsFromExcel(MultipartFile file);

    List<IdDto> getAllAssetIds();

    Map<String, List<String>> getAllTypes();
}
