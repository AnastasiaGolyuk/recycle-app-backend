package com.bsuir.diploma.recycleappbackend.service.impl;

import com.bsuir.diploma.recycleappbackend.exception.EntityNotFoundException;
import com.bsuir.diploma.recycleappbackend.exception.ValidationException;
import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.model.entity.User;
import com.bsuir.diploma.recycleappbackend.model.mapper.UserMapper;
import com.bsuir.diploma.recycleappbackend.repository.UserRepository;
import com.bsuir.diploma.recycleappbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto saveUser(UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(entity);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public Page<UserDto> findAllUsers(Pageable pageable) {
        List<UserDto> userDtoList = userRepository.findAll(pageable)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(userDtoList, pageable, userRepository.count());
    }

    @Override
    public Long getUsersCount() {
        return userRepository.count();
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));
        User entity = userMapper.toEntity(userDto);
        User updatedUser = userRepository.save(entity);
        return userMapper.toDto(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

}
