package com.example.demo.Controller;

import com.example.demo.DTO.APIResponse;
import com.example.demo.DTO.LoginReqDto;
import com.example.demo.Services.LoginService;
import com.example.demo.Storage.GlobalStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class LoginController {
    @Autowired
    private LoginService loginService;

    public LoginController() {
    }

    @PostMapping({"/login"})
    public ResponseEntity<APIResponse> loginCont(@RequestBody LoginReqDto loginReqDto) {
        APIResponse apiResponse = this.loginService.login(loginReqDto);
        GlobalStorage.user_identifier = loginReqDto.getAdminID();
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
