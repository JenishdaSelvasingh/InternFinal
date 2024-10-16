package com.example.demo.Service;

import com.example.demo.GenericResponse;
import com.example.demo.DTO.AssetDto;
import com.example.demo.DTO.IdDto;
import com.example.demo.Entity.AssetEntity;
import com.example.demo.Repo.AssetRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    public AssetServiceImpl(AssetRepository assetRepository, ModelMapper modelMapper) {
        this.assetRepository = assetRepository;
        this.modelMapper = modelMapper;
    }

    public List<AssetEntity> getAllData() {
        return this.assetRepository.findAll();
    }

    public List<IdDto> getAllAssetIds() {
        List<String> ids = this.assetRepository.getInactiveAssets();
        List<String> types = this.assetRepository.getInactiveAssetTypes();
        if (ids.size() != types.size()) {
            throw new IllegalStateException("Mismatch between the number of IDs and types");
        } else {
            List<IdDto> idWithTypes = new ArrayList();

            for(int i = 0; i < ids.size(); ++i) {
                IdDto idDto = new IdDto((String)ids.get(i), (String)types.get(i));
                idWithTypes.add(idDto);
            }

            return idWithTypes;
        }
    }

    public Map<String, List<String>> getAllTypes() {
        List<String> types = this.assetRepository.getAllAssetTypes();
        Set<String> uniqueTypes = new HashSet(types);
        Map<String, List<String>> result = new HashMap();
        result.put("types", new ArrayList(uniqueTypes));
        return result;
    }

    public AssetEntity createAsset(AssetEntity asset) {
        return (AssetEntity)this.assetRepository.saveAndFlush(asset);
    }

    public String updateAsset(AssetDto asset, String id) {
        AssetEntity assetUpdate = (AssetEntity)this.assetRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Asset Not Found");
        });
        assetUpdate.setAssetID(asset.getAssetID());
        assetUpdate.setAssetModel(asset.getAssetModel());
        assetUpdate.setAssetType(asset.getAssetType());
        assetUpdate.setIsActive(asset.getIsActive());
        assetUpdate.setNotes(asset.getNotes());
        this.assetRepository.save(assetUpdate);
        return "Asset updated successfully";
    }

    public String deleteAsset(String id) {
        this.assetRepository.deleteById(id);
        return "Asset deleted successfully";
    }

    public String deleteAllAssets() {
        this.assetRepository.deleteAll();
        return "All assets have been deleted";
    }

    public Page<AssetDto> getPageData(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AssetEntity> assetEntities = this.assetRepository.findAll(pageable);
        List<AssetDto> assetDtos = assetEntities.stream().map((assetEntity) -> {
            return (AssetDto)this.modelMapper.map(assetEntity, AssetDto.class);
        }).toList();
        return new PageImpl(assetDtos, pageable, assetEntities.getTotalElements());
    }

    public GenericResponse updateAssetsFromExcel(MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            GenericResponse var15;
            try {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator var4 = sheet.iterator();

                while(var4.hasNext()) {
                    Row row = (Row)var4.next();
                    if (row.getRowNum() != 0) {
                        String assetID = this.getCellValue(row.getCell(0));
                        String assetType = this.getCellValue(row.getCell(1));
                        String assetModel = this.getCellValue(row.getCell(2));
                        boolean isActive = "yes".equalsIgnoreCase(this.getCellValue(row.getCell(3)));
                        String notes = this.getCellValue(row.getCell(4));
                        this.updateAssetInDatabase(assetID, assetType, assetModel, isActive, notes);
                    }
                }

                var15 = GenericResponse.builder().status(true).message("Assets updated successfully").build();
            } catch (Throwable var12) {
                try {
                    workbook.close();
                } catch (Throwable var11) {
                    var12.addSuppressed(var11);
                }

                throw var12;
            }

            workbook.close();
            return var15;
        } catch (IOException var13) {
            IOException e = var13;
            e.printStackTrace();
            return GenericResponse.builder().status(false).message("Failed to process file").build();
        }
    }

    private void updateAssetInDatabase(String assetID, String assetType, String assetModel, boolean isActive, String notes) {
        AssetEntity assetEntity = (AssetEntity)this.assetRepository.findById(assetID).orElse(new AssetEntity());
        assetEntity.setAssetID(assetID);
        assetEntity.setAssetType(assetType);
        assetEntity.setAssetModel(assetModel);
        assetEntity.setIsActive(isActive);
        assetEntity.setNotes(notes);
        this.assetRepository.save(assetEntity);
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        } else {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().toString();
                    }

                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                case BLANK:
                default:
                    return "";
            }
        }
    }
}
