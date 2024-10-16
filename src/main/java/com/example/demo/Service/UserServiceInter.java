package com.example.demo.Service;

import com.example.demo.DTO.UserDto;
import com.example.demo.DTO.UserListDto;
import com.example.demo.Entity.ItemEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInter {
    ResponseEntity<String> addUser(UserDto userDto);

    UserDto updateUser(String ntId, UserDto userDto);

    ResponseEntity<String> deleteUser(String ntId);

    ResponseEntity<String> deleteMultiUser(List<String> ntId);

    Page<UserListDto> getAll(int pageNumber, int pageSize);

    ResponseEntity<String> addClients(List<UserDto> formDto);

    Page<UserListDto> formSearch(Integer page, Integer size, String search, Sort sort);

    Page<UserListDto> formKeySearch(Integer page, Integer size, String search, Sort sort, String key);

    Page<UserListDto> formAnySearch(Integer page, Integer size, String search, Sort sort, List<String> keys);

    String reqAccept(ItemEntity item);
}
