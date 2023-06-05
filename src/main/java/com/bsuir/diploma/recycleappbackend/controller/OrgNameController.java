package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgNameDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgNameDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Role;
import com.bsuir.diploma.recycleappbackend.service.OrgNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizations")
public class OrgNameController {

    private final OrgNameService orgNameService;

    @PostMapping
    public OrgNameDto save(@RequestBody OrgNameDto orgNameDto) {
        System.out.println(orgNameDto.getName());
        return orgNameService.saveOrgName(orgNameDto);
    }

    @GetMapping
    public List<OrgNameDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<OrgNameDto> orgNamePage = orgNameService.findAllOrgNames(PageRequest.of(page, size));
        return new ArrayList<>(orgNamePage.getContent());
    }

    @GetMapping("/all")
    public List<OrgNameDto> findAllList() {
        List<OrgNameDto> orgNamePage = orgNameService.findAllOrgNamesList();
        return new ArrayList<>(orgNamePage);
    }

    @GetMapping("/{id}")
    public OrgNameDto findById(@PathVariable Long id) {
        return orgNameService.findOrgNameById(id);
    }

    @GetMapping("/name/{name}")
    public OrgNameDto findOrgNameByName(@PathVariable String name) {
        return orgNameService.findOrgByName(name);
    }

    @GetMapping("/exists-by-name/{name}")
    public boolean existsByName(@PathVariable String name) {
        return orgNameService.existsByName(name);
    }

    //    @PreAuthorize("hasAuthority('USER_PERMISSION')")
    @PatchMapping
    public OrgNameDto update(@RequestBody OrgNameDto orgNameDto) {
        return orgNameService.updateOrgName(orgNameDto);
    }

    @GetMapping("/count")
    public Long getOrgNamesCount() {
        return orgNameService.getOrgNamesCount();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        orgNameService.deleteOrgNameById(id);
    }
}

