package com.example.demo.Services;

import com.example.demo.DTO.APIResponse;
import com.example.demo.DTO.LoginReqDto;
import com.example.demo.Entity.AdminEntity;
import com.example.demo.Entity.UserEntity;
import com.example.demo.JWTUtil.JWTUtils;
import com.example.demo.Repo.AdminRepo;
import com.example.demo.Repo.UserRepo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTUtils jwtUtils;

    public LoginService() {
    }

    public APIResponse login(LoginReqDto loginReqDto) {
        APIResponse apiResponse = new APIResponse();
        System.out.println(loginReqDto.getPassword());
        AdminEntity admin = this.adminRepo.findOneByAdminIDIgnoreCaseAndPassword(loginReqDto.getAdminID().trim(), loginReqDto.getPassword());
        UserEntity user = this.userRepo.findOneByEmailIgnoreCaseAndNtId(loginReqDto.getAdminID().trim(), loginReqDto.getPassword());
        if (admin == null && user == null) {
            apiResponse.setData("User login failed");
            return apiResponse;
        } else {
            String token;
            if (user == null) {
                token = this.jwtUtils.generateJWT(admin);
            } else {
                token = this.jwtUtils.generateJWTUser(user);
            }

            long milliTime = System.currentTimeMillis();
            long expiryTime = milliTime + JWTUtils.expiryDuration * 1000L;
            Map<String, Object> data = new HashMap();
            data.put("accessToken", token);
            data.put("access", "pass");
            data.put("expiryAt", expiryTime);
            if (admin != null) {
                data.put("Role", admin.getAccessTags());
            } else {
                data.put("Role", "USER");
            }

            apiResponse.setData(data);
            return apiResponse;
        }
    }
}
