package com.example.demo.Service;

import com.example.demo.Entity.LogEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LogServiceInter {
    List<LogEntity> getLogsByEmail(String adminId);
}
