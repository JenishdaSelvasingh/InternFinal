package com.example.demo.Controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.example.demo.DTO.AssetDto;
import com.example.demo.DTO.LoginReqDto;
import com.example.demo.DTO.ResetDto;
import com.example.demo.DTO.UserDto;
import com.example.demo.DTO.UserListDto;
import com.example.demo.Entity.DummyEntity;
import com.example.demo.Entity.ItemEntity;
import com.example.demo.Entity.LogEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Exception.ApplicationException;
import com.example.demo.Repo.ItemRepo;
import com.example.demo.Repo.LoggerRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Service.EmailSender;
import com.example.demo.Service.MailtrapClient;
import com.example.demo.Service.TwilioService;
import com.example.demo.Service.UserServiceImpl;
import com.example.demo.Service.UserServiceInter;
import com.example.demo.Storage.GlobalStorage;
import com.example.demo.enums.AccessTag;
import com.example.demo.enums.AccessTags;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(
        origins = {"*"},
        exposedHeaders = {"**"}
)
public class UserController {
    @Autowired
    private UserServiceInter userServiceInter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private LoggerRepo loggerRepo;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private final MailtrapClient mailtrapClient;
    private final EmailSender emailSender;

    public UserController(MailtrapClient mailtrapClient, EmailSender emailSender) {
        this.mailtrapClient = mailtrapClient;
        this.emailSender = emailSender;
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER, AccessTag.ADMINUSER})
    @GetMapping({"/getUser"})
    public UserDto getUserDetails() {
        String emailId = GlobalStorage.user_identifier;
        return this.userRepo.findByEmail(emailId);
    }

    @AccessTags({AccessTag.ADMIN})
    @PostMapping({"/save"})
    public ResponseEntity<String> saveClient(@RequestBody List<UserDto> formDto) {
        Iterator var2 = formDto.iterator();

        while(var2.hasNext()) {
            UserDto userDto = (UserDto)var2.next();
            LogEntity logEntity = (LogEntity)this.modelMapper.map(userDto, LogEntity.class);
            logEntity.setTimeFormatted(LocalDateTime.now());
            this.loggerRepo.save(logEntity);
        }

        return this.userServiceInter.addClients(formDto);
    }

    @AccessTags({AccessTag.ADMIN})
    @PostMapping({"/add"})
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        LogEntity logEntity = (LogEntity)this.modelMapper.map(userDto, LogEntity.class);
        logEntity.setTimeFormatted(LocalDateTime.now());
        this.loggerRepo.save(logEntity);
        return this.userServiceInter.addUser(userDto);
    }

    @AccessTags({AccessTag.ADMIN})
    @PostMapping({"/setViewAdmin"})
    public String createAndSaveAdminViewEntity(@RequestBody LoginReqDto loginReqDto) {
        String result = this.userServiceImpl.createAndSaveAdminViewEntity(loginReqDto);
        return result;
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER, AccessTag.ADMINUSER})
    @GetMapping({"/users"})
    public Page<UserDto> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size, @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{sortBy});
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserEntity> result = this.userRepo.findAll(pageable);
        Page<UserDto> dtoPage = result.map((entity) -> {
            UserDto userDto = (UserDto)this.modelMapper.map(entity, UserDto.class);
            if (userDto.getAssetEntity() != null && !userDto.getAssetEntity().isEmpty()) {
                userDto.setAssetEntity(userDto.getAssetEntity().stream().map((m) -> {
                    return (AssetDto)this.modelMapper.map(m, AssetDto.class);
                }).toList());
            }

            return userDto;
        });
        return dtoPage;
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER, AccessTag.ADMINUSER})
    @GetMapping({"/data"})
    public Page<UserListDto> getForms(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "8") Integer size) {
        return this.userServiceImpl.getAll(page, size);
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.ADMINUSER})
    @GetMapping({"/getRoles"})
    public List<DummyEntity> getRoles() {
        return this.userServiceImpl.getAdminRoles();
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER, AccessTag.ADMINUSER})
    @GetMapping({"/search"})
    public Page<UserListDto> searchForms(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "8") Integer size, @RequestParam(required = false) String search, @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{sortBy});
        return this.userServiceInter.formSearch(page, size, search, sort);
    }

    @GetMapping({"/searchKey"})
    public Page<UserListDto> searchKeyForms(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "8") Integer size, @RequestParam(required = false,defaultValue = "name",name = "key") List<String> keys, @RequestParam(name = "search") String search, @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{sortBy});
        return this.userServiceInter.formAnySearch(page, size, search, sort, keys);
    }

    @GetMapping({"/searchAny"})
    public Page<UserListDto> searchAny(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "8") Integer size, @RequestParam(required = false,defaultValue = "name",name = "key") List<String> key, @RequestParam(name = "search") String search, @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{sortBy});
        return Objects.equals(search, "") ? this.userServiceInter.formSearch(page, size, search, sort) : this.userServiceInter.formAnySearch(page, size, search, sort, key);
    }

    @AccessTags({AccessTag.ADMIN})
    @PutMapping({"/update/{ntId}"})
    public ResponseEntity<String> updateUser(@PathVariable String ntId, @RequestBody UserDto userDto) {
        UserDto userDto1 = this.userServiceInter.updateUser(ntId, userDto);
        return userDto1 != null ? ResponseEntity.ok("User with ID " + ntId + " has been Updated.") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + ntId + " not found.");
    }

    @AccessTags({AccessTag.ADMIN})
    @DeleteMapping({"/delete/{ntId}"})
    public ResponseEntity<String> deleteUser(@PathVariable String ntId) {
        return this.userServiceInter.deleteUser(ntId);
    }

    @AccessTags({AccessTag.ADMIN})
    @PostMapping({"/multiDelete"})
    public ResponseEntity<String> deleteMultiUser(@RequestBody List<String> delList) {
        return this.userServiceInter.deleteMultiUser(delList);
    }

    @AccessTags({AccessTag.ADMIN})
    @PostMapping({"/upload"})
    public ResponseEntity<String> uploadUsers(@RequestParam("file") MultipartFile file) throws IOException {
        List<UserDto> userDtoList = this.convertCsvToJson(file);
        ResponseEntity<String> ResponseString = this.userServiceInter.addClients(userDtoList);
        return ResponseString;
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER})
    @PostMapping({"/setReq"})
    public ResponseEntity<String> setRequestItem(@RequestBody ItemEntity item) {
        if (item.getItemType() != null && !item.getItemType().isEmpty()) {
            this.itemRepo.save(item);
            this.twilioService.sendOTPSms(item.getNtId(), item.getItemType());
            return ResponseEntity.ok("Request Set Success!");
        } else {
            return ResponseEntity.badRequest().body("Item type is missing");
        }
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER})
    @GetMapping({"/getReq"})
    public List<ItemEntity> setRequestItem() {
        return this.itemRepo.findAll();
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER})
    @GetMapping({"/getReqC"})
    public int getRequestCounts() {
        return this.itemRepo.findAll().size();
    }

    @PostMapping({"/reset-password"})
    public String restPass(@RequestBody ResetDto resetDto) {
        return this.userServiceImpl.resetPass(resetDto);
    }

    @PostMapping({"/verify-otp"})
    public String verifyOtp(@RequestParam Integer otp, @RequestParam String email) {
        return this.userServiceImpl.verifyOtp(otp, email);
    }

    @PostMapping({"/pass-change"})
    public String verifyOtp(@RequestParam String email, @RequestParam String password) {
        return this.userServiceImpl.changePass(email, password);
    }

    @PostMapping({"/acceptReq"})
    @AccessTags({AccessTag.ADMIN, AccessTag.USER})
    public String acceptReq(@RequestBody ItemEntity item) {
        this.itemRepo.deleteByItemType(item.getItemType());
        return "success";
    }

    public List<UserDto> convertCsvToJson(MultipartFile csvFile) throws IOException {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class).with(csvSchema).readValues(csvFile.getInputStream());

        List list;
        try {
            list = mappingIterator.readAll();
        } catch (Throwable var11) {
            if (mappingIterator != null) {
                try {
                    mappingIterator.close();
                } catch (Throwable var10) {
                    var11.addSuppressed(var10);
                }
            }

            throw var11;
        }

        if (mappingIterator != null) {
            mappingIterator.close();
        }

        List<UserDto> userDtos = new ArrayList();
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            Map<String, String> row = (Map)var6.next();
            UserDto userDto = new UserDto();
            userDto.setName((String)row.get("name"));
            userDto.setNtId((String)row.get("ntId"));
            List<String> deviceIds = new ArrayList();
            row.forEach((key, value) -> {
                if (key.startsWith("deviceId/")) {
                    deviceIds.add(value);
                }

            });
            userDto.setDeviceId(deviceIds);
            userDto.setEmail((String)row.get("email"));
            userDto.setManager((String)row.get("manager"));
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @AccessTags({AccessTag.ADMIN})
    @GetMapping({"/send-email/{ntId}"})
    public ResponseEntity<String> sendEmail(@PathVariable String ntId) {
        Optional<UserEntity> userEntityOptional = this.userRepo.findByNtId(ntId);
        if (userEntityOptional.isPresent()) {
            UserEntity user = (UserEntity)userEntityOptional.get();
            String recipientEmail = user.getEmail();
            String subject = "Hello from Asset Manager";
            String var10000 = user.getName();
            String content = "<p>Hello " + var10000 + ",</p><p>This is an email regarding the devices you are assigned with.</p><br><p>These are the deviceIds: " + user.getDeviceId() + "</p>";

            try {
                this.emailSender.sendEmail(recipientEmail, subject, content);
                return ResponseEntity.ok("Email sent successfully.");
            } catch (ApplicationException var8) {
                ApplicationException e = var8;
                return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
            } catch (Exception var9) {
                Exception e = var9;
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while sending the email: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ntId " + ntId + " not found.");
        }
    }
}
