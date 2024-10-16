package com.example.demo.Service;

import com.example.demo.DTO.LoggerDto;
import com.example.demo.Entity.LogEntity;
import com.example.demo.Repo.LoggerRepo;
import com.example.demo.Storage.GlobalStorage;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogServiceInter {
    @Autowired
    private LoggerRepo loggerRepo;
    @Autowired
    private ModelMapper modelMapper;

    public LogServiceImpl() {
    }

    public List<LoggerDto> getAll(int pageNumber, int pageSize) {
        List<LogEntity> mainLog = this.loggerRepo.findByEmail(GlobalStorage.user_identifier);
        return (List)(new PageImpl(mainLog.stream().map((mp) -> {
            return (LoggerDto)this.modelMapper.map(mp, LoggerDto.class);
        }).toList()));
    }

    public List<LogEntity> getLogsByEmail(String adminId) {
        List<LogEntity> test = this.loggerRepo.findByEmail(adminId);
        return test;
    }
}
