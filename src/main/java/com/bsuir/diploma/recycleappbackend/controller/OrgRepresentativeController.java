package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import com.bsuir.diploma.recycleappbackend.model.dto.UserDto;
import com.bsuir.diploma.recycleappbackend.model.entity.OrgRepresentative;
import com.bsuir.diploma.recycleappbackend.model.entity.Role;
import com.bsuir.diploma.recycleappbackend.service.OrgRepresentativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/org-representatives")
public class OrgRepresentativeController {

    private final OrgRepresentativeService orgRepresentativeService;

    @PostMapping
    public OrgRepresentativeDto save(@RequestBody OrgRepresentativeDto orgRepresentativeDto) {
        return orgRepresentativeService.saveOrgRepresentative(orgRepresentativeDto);
    }

    @GetMapping
    public List<OrgRepresentativeDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                              @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<OrgRepresentativeDto> businessOwnerPage = orgRepresentativeService.findAllOrgRepresentatives(PageRequest.of(page, size));
        return new ArrayList<>(businessOwnerPage.getContent());
    }

    @GetMapping("/all")
    public List<OrgRepresentativeDto> findAllList() {
        List<OrgRepresentativeDto> orgRepresentativePage = orgRepresentativeService.findAllOrgRepresentativeList();
        return new ArrayList<>(orgRepresentativePage);
    }

    @GetMapping("/{id}")
    public OrgRepresentativeDto findById(@PathVariable Long id) {
        return orgRepresentativeService.findOrgRepresentativeById(id);
    }

    @GetMapping("/email/{email}")
    public OrgRepresentativeDto findOrgRepresentativeByEmail(@PathVariable String email) {
        return orgRepresentativeService.findOrgRepresentativeByEmail(email);
    }

    @GetMapping("/org-name/{orgName}")
    public OrgRepresentativeDto findOrgRepresentativeByOrgName(@PathVariable String orgName) {
        return orgRepresentativeService.findOrgRepresentativeByOrgName(orgName);
    }

    @PatchMapping
    public OrgRepresentativeDto update(@RequestBody OrgRepresentativeDto orgRepresentativeDto) {
        return orgRepresentativeService.updateOrgRepresentative(orgRepresentativeDto);
    }

    @GetMapping("/count")
    public Long getOrgRepresentativesCount() {
        return orgRepresentativeService.getOrgRepresentativesCount();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        orgRepresentativeService.deleteOrgRepresentativeById(id);
    }
}
