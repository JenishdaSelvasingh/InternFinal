package com.example.demo.Controller;

import com.example.demo.GenericResponse;
import com.example.demo.DTO.AssetDto;
import com.example.demo.DTO.IdDto;
import com.example.demo.DTO.ReqRes;
import com.example.demo.Entity.AssetEntity;
import com.example.demo.Repo.AssetRepository;
import com.example.demo.Service.AssetService;
import com.example.demo.enums.AccessTag;
import com.example.demo.enums.AccessTags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping({"/api"})
public class AssetController {
    @Autowired
    private AssetRepository assetRepository;
    private AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping({"/data"})
    public List<AssetEntity> getAllData() {
        return this.assetService.getAllData();
    }

    @GetMapping({"/listid"})
    public List<IdDto> getAllId() {
        return this.assetService.getAllAssetIds();
    }

    @GetMapping({"/listType"})
    public Map<String, List<String>> getAllTypes() {
        return this.assetService.getAllTypes();
    }

    @PostMapping({"/saveAsset"})
    @AccessTags({AccessTag.ADMIN})
    public AssetEntity createAsset(@RequestBody AssetEntity asset) {
        return this.assetService.createAsset(asset);
    }

    @PostMapping({"/bulkUpload"})
    @AccessTags({AccessTag.ADMIN})
    public ResponseEntity<GenericResponse> bulkUploadAssets(@RequestParam("file") MultipartFile file) {
        GenericResponse result = this.assetService.updateAssetsFromExcel(file);
        return ResponseEntity.ok(result);
    }

    @PutMapping({"/update/{id}"})
    @AccessTags({AccessTag.ADMIN})
    public ResponseEntity<Map<String, String>> updateAsset(@PathVariable String id, @RequestBody AssetDto asset) {
        String result = this.assetService.updateAsset(asset, id);
        Map<String, String> response = new HashMap();
        response.put("message", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping({"/delete/{id}"})
    @AccessTags({AccessTag.ADMIN})
    public GenericResponse deleteAsset(@PathVariable String id) {
        return GenericResponse.builder().message(this.assetService.deleteAsset(id)).status(true).build();
    }

    @DeleteMapping({"/deleteAll"})
    @AccessTags({AccessTag.ADMIN})
    public String deleteAllAssets() {
        this.assetService.deleteAllAssets();
        return "All assets have been deleted";
    }

    @GetMapping({"/pagedata"})
    public Page<AssetDto> PageData(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return this.assetService.getPageData(page, size);
    }

    @GetMapping({"/public/asset"})
    public ResponseEntity<Object> getAllAssets() {
        return ResponseEntity.ok(this.assetRepository.findAll());
    }

    @PostMapping({"/superadmin/saveasset"})
    @AccessTags({AccessTag.ADMIN})
    public ResponseEntity<Object> signUp(@RequestBody ReqRes assetRequest) {
        AssetEntity assetToSave = new AssetEntity();
        assetToSave.setAssetID(assetRequest.getAssetID());
        assetToSave.setAssetType(assetRequest.getAssetType());
        assetToSave.setAssetModel(assetRequest.getAssetModel());
        assetToSave.setIsActive(assetRequest.getIsActive());
        assetToSave.setNotes(assetRequest.getNotes());
        return ResponseEntity.ok(this.assetRepository.save(assetToSave));
    }

    @GetMapping({"/admin/alone"})
    @AccessTags({AccessTag.ADMINUSER})
    public ResponseEntity<Object> userAlone() {
        return ResponseEntity.ok("Users alone can access this Api only");
    }

    @GetMapping({"/superadminadmin/both"})
    @AccessTags({AccessTag.ADMIN, AccessTag.ADMINUSER})
    public ResponseEntity<Object> bothAdminaAndUsersApi() {
        return ResponseEntity.ok("Both Admin and Users Can access the api");
    }

    @GetMapping({"/public/email"})
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getName());
        return authentication.getName();
    }
}
