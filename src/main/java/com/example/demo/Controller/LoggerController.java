package com.example.demo.Controller;


import com.example.demo.Entity.LogEntity;
import com.example.demo.Repo.LoggerRepo;
import com.example.demo.Service.LogServiceImpl;
import com.example.demo.Storage.GlobalStorage;
import com.example.demo.enums.AccessTag;
import com.example.demo.enums.AccessTags;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class LoggerController {
    @Autowired
    private LoggerRepo loggerRepo;
    @Autowired
    private LogServiceImpl logServiceImpl;

    public LoggerController() {
    }

    @AccessTags({AccessTag.ADMIN, AccessTag.USER})
    @GetMapping({"/logs"})
    public List<LogEntity> getLogs() {
        String adminId = GlobalStorage.user_identifier;
        return this.logServiceImpl.getLogsByEmail(adminId);
    }
}
