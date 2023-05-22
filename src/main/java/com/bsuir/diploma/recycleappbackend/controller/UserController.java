package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping
    public List<UserDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<UserDto> userPage = userService.findAllUsers(PageRequest.of(page, size));
        return new ArrayList<>(userPage.getContent());
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/email/{email}")
    public UserDto findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/exists-by-email/{email}")
    public boolean existsByEmail(@PathVariable String email) {
        return userService.existsByEmail(email);
    }

    @PatchMapping
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @GetMapping("/count")
    public Long getUsersCount() {
        return userService.getUsersCount();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
