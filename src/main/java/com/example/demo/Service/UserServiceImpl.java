package com.example.demo.Service;

import com.example.demo.DTO.LoginReqDto;
import com.example.demo.DTO.ResetDto;
import com.example.demo.DTO.UserDto;
import com.example.demo.DTO.UserListDto;
import com.example.demo.Entity.AdminEntity;
import com.example.demo.Entity.AssetEntity;
import com.example.demo.Entity.DummyEntity;
import com.example.demo.Entity.ItemEntity;
import com.example.demo.Entity.LogEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Exception.ApplicationException;
import com.example.demo.Repo.AdminRepo;
import com.example.demo.Repo.AdminRoleProjection;
import com.example.demo.Repo.AssetRepository;
import com.example.demo.Repo.ItemRepo;
import com.example.demo.Repo.LoggerRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Repo.UserSpecification;
import jakarta.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInter {
    @Autowired
    private UserDto userDto;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private LoggerRepo loggerRepo;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserEntity userEntity;
    @Autowired
    private EmailSender emailSender;

    public UserServiceImpl() {
    }

    public ResponseEntity<String> addUser(UserDto userDto) {
        Optional<UserEntity> storedUser = this.userRepo.findByNtId(userDto.getNtId());
        if (storedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate NT-ID: " + userDto.getNtId());
        } else {
            if (!userDto.getDeviceId().isEmpty()) {
                this.sendEmail(userDto.getNtId());
            }

            UserEntity userEntity = (UserEntity)this.modelMapper.map(userDto, UserEntity.class);
            LocalDateTime currentTime = LocalDateTime.now();
            userEntity.setTimeFormatted(currentTime);
            this.userRepo.save(userEntity);
            Optional<UserEntity> optionalUserEntity = this.userRepo.findByNtId(userDto.getNtId());
            if (optionalUserEntity.isPresent()) {
                UserEntity userEntity1 = (UserEntity)optionalUserEntity.get();
                Iterator var7 = userDto.getDeviceId().iterator();

                while(var7.hasNext()) {
                    String deviceId = (String)var7.next();
                    AssetEntity assetEntity = this.assetRepository.findOneByAssetID(deviceId);
                    if (assetEntity != null) {
                        assetEntity.setUserEntity(userEntity1);
                        assetEntity.setIsActive(true);
                        this.assetRepository.save(assetEntity);
                    }
                }
            }

            this.createAndSaveAdminEntity(userDto);
            return ResponseEntity.ok("NT-ID: " + userDto.getNtId() + " is added");
        }
    }

    public UserDto updateUser(String ntId, UserDto userDto) {
        Optional<UserEntity> optionalUserEntity = this.userRepo.findByNtId(ntId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = (UserEntity)optionalUserEntity.get();
            List existingDeviceIds;
            if (!userEntity.getDeviceId().isEmpty()) {
                existingDeviceIds = userEntity.getDeviceId();
            } else {
                existingDeviceIds = Collections.singletonList("null");
            }

            List<String> newDeviceIds = userDto.getDeviceId();
            List<String> removedDeviceIds = new ArrayList(existingDeviceIds);
            removedDeviceIds.removeAll(newDeviceIds);
            List<String> addedDeviceIds = new ArrayList(newDeviceIds);
            addedDeviceIds.removeAll(existingDeviceIds);
            if (!userDto.getDeviceId().equals(userEntity.getDeviceId())) {
                this.sendUpdateEmail(userDto.getNtId(), addedDeviceIds, removedDeviceIds);
            }

            userEntity.setName(userDto.getName());
            userEntity.setDeviceId(userDto.getDeviceId());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setManager(userDto.getManager());
            userEntity.setTimeFormatted(LocalDateTime.now());
            UserEntity updatedUserEntity = (UserEntity)this.userRepo.save(userEntity);
            UserDto updatedUserDto = (UserDto)this.modelMapper.map(updatedUserEntity, UserDto.class);
            LogEntity logEntity = (LogEntity)this.modelMapper.map(userDto, LogEntity.class);
            logEntity.setTimeFormatted(LocalDateTime.now());
            this.loggerRepo.save(logEntity);
            this.updateAndSaveAdminEntity(userDto);
            Optional<UserEntity> optionalUserEntity1 = this.userRepo.findByNtId(userDto.getNtId());
            if (optionalUserEntity1.isPresent()) {
                UserEntity userEntity1 = (UserEntity)optionalUserEntity1.get();
                Iterator var14 = userDto.getDeviceId().iterator();

                while(var14.hasNext()) {
                    String deviceId = (String)var14.next();
                    AssetEntity assetEntity = this.assetRepository.findOneByAssetID(deviceId);
                    if (assetEntity != null) {
                        assetEntity.setUserEntity(userEntity1);
                        assetEntity.setIsActive(true);
                        this.assetRepository.save(assetEntity);
                    }
                }
            }

            return updatedUserDto;
        } else {
            return userDto;
        }
    }

    @Transactional
    public ResponseEntity<String> deleteUser(String ntId) {
        Optional<UserEntity> optionalUserEntity = this.userRepo.findByNtId(ntId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = (UserEntity)optionalUserEntity.get();
            this.userRepo.deleteByNtId(ntId);
            this.deleteAdminEntity(userEntity.getEmail());
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.ok("Not Deleted");
        }
    }

    @Transactional
    public ResponseEntity<String> deleteMultiUser(List<String> ntIds) {
        List<UserEntity> users = this.userRepo.findByNtIdIn(ntIds);
        if (!users.isEmpty()) {
            List<String> emails = (List)users.stream().map(UserEntity::getEmail).collect(Collectors.toList());
            emails.forEach(this::deleteAdminEntity);
            this.userRepo.deleteByNtIdIn(ntIds);
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.ok("Not Deleted");
        }
    }

    public Page<UserListDto> getAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Direction.DESC, new String[]{"timeFormatted"});
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<UserEntity> mainPage = this.userRepo.findAll(pageable);
        return new PageImpl(mainPage.stream().map((mp) -> {
            return (UserListDto)this.modelMapper.map(mp, UserListDto.class);
        }).toList(), mainPage.getPageable(), mainPage.getTotalElements());
    }

    public ResponseEntity<String> addClients(List<UserDto> userDtos) {
        boolean checkDup = false;
        Iterator var3 = userDtos.iterator();

        while(var3.hasNext()) {
            UserDto userDto = (UserDto)var3.next();
            Optional<UserEntity> storedUser = this.userRepo.findByNtId(userDto.getNtId());
            if (storedUser.isPresent()) {
                checkDup = true;
            } else {
                UserEntity userEntity = (UserEntity)this.modelMapper.map(userDto, UserEntity.class);
                userEntity.setTimeFormatted(LocalDateTime.now());
                this.userRepo.save(userEntity);
                this.createAndSaveAdminEntity(userDto);
                LogEntity logEntity = (LogEntity)this.modelMapper.map(userDto, LogEntity.class);
                logEntity.setTimeFormatted(LocalDateTime.now());
                this.loggerRepo.save(logEntity);
            }
        }

        if (checkDup) {
            return ResponseEntity.ok("Users added except the duplicate ones.");
        } else {
            return ResponseEntity.ok("All the UserIds have been added.");
        }
    }

//    public Page<UserListDto> formSearch(Integer page, Integer size, String search, Sort sort) {
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page mainPage;
//        if (search != null && !search.isEmpty()) {
//            mainPage = this.userRepo.searchAll(search, pageable);
//        } else {
//            mainPage = this.userRepo.findAll(pageable);
//        }
//
//        return mainPage.map((mainEntity) -> {
//            LocalDate currentDate = LocalDate.now();
//            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//            String formattedTime = LocalTime.now().format(timeFormatter);
//                return new UserListDto(
//                        mainEntity.getName(),
//                        mainEntity.getNtId(),
//                        mainEntity.getDeviceId(),
//                        mainEntity.getEmail(),
//                        mainEntity.getManager(),
//                        mainEntity.getTimeFormatted());
//        });
//    }

    public Page<UserListDto> formSearch(Integer page, Integer size, String search, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserEntity> mainPage;
        if (search != null && !search.isEmpty()) {
//            Long id = Long.parseLong(search);
            mainPage = userRepo.searchAll(search, pageable);
        } else {
            mainPage = userRepo.findAll(pageable);
        }

        return mainPage.map(mainEntity -> {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = LocalTime.now().format(timeFormatter);

            return new UserListDto(
                    mainEntity.getName(),
                    mainEntity.getNtId(),
                    mainEntity.getDeviceId(),
                    mainEntity.getEmail(),
                    mainEntity.getManager(),
                    mainEntity.getTimeFormatted()
            );
        });
    }

//    public Page<UserListDto> formKeySearch(Integer page, Integer size, String search, Sort sort, String key) {
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page mainPage;
//        if (search != null && !search.isEmpty()) {
//            Page var10000;
//            switch (key) {
//                case "ntId" -> var10000 = this.userRepo.searchNtIdKeyAll(search, pageable);
//                case "mail" -> var10000 = this.userRepo.searchMailKeyAll(search, pageable);
//                case "name" -> var10000 = this.userRepo.searchNameKeyAll(search, pageable);
//                case "manager" -> var10000 = this.userRepo.searchManagerKeyAll(search, pageable);
//                default -> var10000 = this.userRepo.searchAll(search, pageable);
//            }
//
//            mainPage = var10000;
//        } else {
//            mainPage = this.userRepo.findAll(pageable);
//        }
//
//        assert mainPage != null;
//
//        return mainPage.map((mainEntity) -> {
//            LocalDate currentDate = LocalDate.now();
//            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//            String formattedTime = LocalTime.now().format(timeFormatter);
//            return new UserListDto(
//                    mainEntity.getName(),
//                    mainEntity.getNtId(),
//                    mainEntity.getDeviceId(),
//                    mainEntity.getEmail(),
//                    mainEntity.getManager(),
//                    mainEntity.getTimeFormatted());
//        });
//    }

    public Page<UserListDto> formKeySearch(Integer page, Integer size, String search, Sort sort, String key) {
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserEntity> mainPage;
        if (search != null && !search.isEmpty()) {
//            Long id = Long.parseLong(search);

            mainPage = switch (key) {
                case "ntId" -> userRepo.searchNtIdKeyAll(search, pageable);
                case "mail" -> userRepo.searchMailKeyAll(search, pageable);
                case "name" -> userRepo.searchNameKeyAll(search, pageable);
                case "manager" -> userRepo.searchManagerKeyAll(search, pageable);
                default -> userRepo.searchAll(search,pageable);
            };

        } else {
            mainPage = userRepo.findAll(pageable);
        }

        assert mainPage != null;
        return mainPage.map(mainEntity -> {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = LocalTime.now().format(timeFormatter);

            return new UserListDto(
                    mainEntity.getName(),
                    mainEntity.getNtId(),
                    mainEntity.getDeviceId(),
                    mainEntity.getEmail(),
                    mainEntity.getManager(),
                    mainEntity.getTimeFormatted()
            );
        });
    }

    public Page<UserListDto> formAnySearch(Integer page, Integer size, String search, Sort sort, List<String> keys) {
        Specification<UserEntity> specification = UserSpecification.searchByColumns(search, keys);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserEntity> userEntities = this.userRepo.findAll(specification, pageable);
        List<UserListDto> userListDtos = (List)userEntities.getContent().stream().map((userEntity) -> {
            return (UserListDto)this.modelMapper.map(userEntity, UserListDto.class);
        }).collect(Collectors.toList());
        return new PageImpl(userListDtos, pageable, userEntities.getTotalElements());
    }

    @Transactional
    public String reqAccept(ItemEntity item) {
        Optional<UserEntity> userEntityOptional = this.userRepo.findByNtId(item.getNtId());
        if (userEntityOptional.isPresent()) {
            UserEntity user = (UserEntity)userEntityOptional.get();
            List<String> deviceIds = user.getDeviceId();
            if (!deviceIds.contains(item.getItemType())) {
                this.itemRepo.deleteByItemType(item.getItemType());
                return "Device set!";
            }
        }

        return "Failed to set device";
    }

    public String resetPass(ResetDto resetDto) {
        UserDto userDto = this.userRepo.findByEmail(resetDto.getEmail());
        if (userDto == null) {
            return "Mail doesn't exist";
        } else {
            int randomOtp = this.generateRandomOtp();
            String emailResult = this.sendOTP(userDto.getEmail(), randomOtp);
            UserEntity userEntity1 = this.userRepo.findEntityByEmail(resetDto.getEmail());
            userEntity1.setOtp(randomOtp);
            userEntity1.setOtpExpiry(LocalDateTime.now());
            this.userRepo.save(userEntity1);
            return emailResult;
        }
    }

    public String verifyOtp(Integer otp, String email) {
        UserEntity userEntity1 = this.userRepo.findEntityByEmail(email.trim());
        if (Objects.equals(userEntity1.getOtp(), otp) && !LocalDateTime.now().isAfter(userEntity1.getOtpExpiry().plusMinutes(3L))) {
            AdminEntity adminEntity1 = this.adminRepo.findByAdminID(email.trim());
            if (adminEntity1 != null) {
                adminEntity1.setPassword((String)null);
                this.userRepo.save(userEntity1);
            }

            return "true";
        } else {
            return "Wrong otp";
        }
    }

    public String changePass(String email, String password) {
        AdminEntity adminEntity = this.adminRepo.findByAdminID(email.trim());
        if (adminEntity != null) {
            adminEntity.setPassword(password);
            this.userRepo.save(this.userEntity);
            return "changed";
        } else {
            return "fail";
        }
    }

    private int generateRandomOtp() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return this.userRepo.findAll(pageable);
    }

    public String sendEmail(String ntId) {
        Optional<UserEntity> userEntityOptional = this.userRepo.findByNtId(ntId);
        if (userEntityOptional.isPresent()) {
            UserEntity user = (UserEntity)userEntityOptional.get();
            String recipientEmail = user.getEmail();
            String subject = "Hello from Asset Manager";
            String var10000 = user.getName();
            String content = "<p>Hello " + var10000 + "</p><br><p>Login Credentials</p><br>,</p><p>User Id:</p>" + user.getEmail() + ",</p><p>Password :</p>" + user.getNtId() + "<br>,</p><p>The devices you are assigned with are below.</p><br><p>These are the deviceIds: " + user.getDeviceId() + "</p>";

            try {
                this.emailSender.sendEmail(recipientEmail, subject, content);
                return "Email sent successfully.";
            } catch (ApplicationException var8) {
                return "Application Exception";
            } catch (Exception var9) {
                Exception e = var9;
                return "An unexpected error occurred while sending the email: " + e.getMessage();
            }
        } else {
            return "User with ntId " + ntId + " not found.";
        }
    }

    public String sendOTP(String mail, int otp) {
        UserDto userDto = this.userRepo.findByEmail(mail);
        if (userDto == null) {
            return "Mail doesn't exist";
        } else {
            String subject = "Hello from Asset Manager";
            String var10000 = userDto.getName();
            String content = "<p>Hello " + var10000 + "</p><br><p>Your OTP for resetting the password is: " + otp + "</p><br><p>Please use this OTP to reset your password.</p>";

            try {
                this.emailSender.sendEmail(mail, subject, content);
                return "Email sent successfully.";
            } catch (ApplicationException var7) {
                return "Application Exception";
            } catch (Exception var8) {
                Exception e = var8;
                return "An unexpected error occurred while sending the email: " + e.getMessage();
            }
        }
    }

    public String sendUpdateEmail(String ntId, List<String> addedIds, List<String> removedIds) {
        Optional<UserEntity> userEntityOptional = this.userRepo.findByNtId(ntId);
        if (userEntityOptional.isPresent()) {
            UserEntity user = (UserEntity)userEntityOptional.get();
            String recipientEmail = user.getEmail();
            String subject = "Hello from Asset Manager";
            String content = "<p>Hello " + user.getName() + ",</p><p>This is an email regarding the devices you are assigned with.</p><br>";
            if (!addedIds.isEmpty()) {
                content = content + "<p>These are the deviceIds added: " + addedIds + "</p><br>";
            }

            if (!removedIds.isEmpty()) {
                content = content + "<p>These are the deviceIds removed: " + removedIds + "</p>";
            }

            try {
                this.emailSender.sendEmail(recipientEmail, subject, content);
                return "Email sent successfully.";
            } catch (ApplicationException var10) {
                return "Application Exception";
            } catch (Exception var11) {
                Exception e = var11;
                return "An unexpected error occurred while sending the email: " + e.getMessage();
            }
        } else {
            return "User with ntId " + ntId + " not found.";
        }
    }

    private void createAndSaveAdminEntity(UserDto userDto) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAdminID(userDto.getEmail());
        adminEntity.setPassword(this.generateSha256Hash(userDto.getNtId()));
        adminEntity.setAccessTags(List.of("USER"));
        this.adminRepo.save(adminEntity);
    }

    private void deleteAdminEntity(String email) {
        this.adminRepo.deleteByAdminID(email);
    }

    private void updateAndSaveAdminEntity(UserDto userDto) {
        Optional<AdminEntity> optionalAdminEntity = this.adminRepo.findByPassword(this.generateSha256Hash(userDto.getNtId()));
        if (optionalAdminEntity.isPresent()) {
            AdminEntity adminEntity = (AdminEntity)optionalAdminEntity.get();
            adminEntity.setAdminID(userDto.getEmail());
            this.adminRepo.save(adminEntity);
        } else {
            this.createAndSaveAdminEntity(userDto);
        }

    }

    private String generateSha256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            byte[] var5 = hash;
            int var6 = hash.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                byte b = var5[var7];
                String hex = Integer.toHexString(255 & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException var10) {
            NoSuchAlgorithmException e = var10;
            throw new RuntimeException("Error generating SHA-256 hash", e);
        }
    }

    public String createAndSaveAdminViewEntity(LoginReqDto loginReqDto) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAdminID(loginReqDto.getAdminID());
        adminEntity.setPassword(this.generateSha256Hash(loginReqDto.getPassword()));
        adminEntity.setAccessTags(List.of("ADMINUSER"));
        this.adminRepo.save(adminEntity);
        return "Success";
    }

    public List<DummyEntity> getAdminRoles() {
        List<AdminRoleProjection> projections = this.adminRepo.findByAdminRole();
        return (List)projections.stream().map((projection) -> {
            return new DummyEntity(projection.getAdminId(), Collections.singletonList(projection.getAccessTag()));
        }).collect(Collectors.toList());
    }
}
