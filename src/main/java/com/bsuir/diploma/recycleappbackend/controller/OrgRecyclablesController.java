package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRecyclablesDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgRecyclablesDto;
import com.bsuir.diploma.recycleappbackend.model.dto.OrgRecyclablesDto;
import com.bsuir.diploma.recycleappbackend.service.OrgRecyclablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/org-recyclables")
public class OrgRecyclablesController {

    private final OrgRecyclablesService orgRecyclablesService;

    @PostMapping
    public OrgRecyclablesDto save(@RequestBody OrgRecyclablesDto orgRecyclablesDto) {
        return orgRecyclablesService.saveOrgRecyclables(orgRecyclablesDto);
    }


    @GetMapping
    public List<OrgRecyclablesDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                           @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<OrgRecyclablesDto> orgRecyclablesPage = orgRecyclablesService.findAllOrgRecyclables(PageRequest.of(page, size));
        return new ArrayList<>(orgRecyclablesPage.getContent());
    }


    @GetMapping("/type/{type}")
    public OrgRecyclablesDto findOrgRecyclablesByType(@PathVariable String type) {
        return orgRecyclablesService.findOrgRecyclablesByType(type);
    }

    @GetMapping("/org-name/{name}")
    public List<OrgRecyclablesDto> findOrgRecyclablesByOrgName(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                               @PathVariable String name) {
        Page<OrgRecyclablesDto> orgRecyclablesPage = orgRecyclablesService.findAllOrgRecyclablesByOrgName(name, PageRequest.of(page, size));
        return new ArrayList<>(orgRecyclablesPage.getContent());
    }

    @PatchMapping
    public OrgRecyclablesDto update(@RequestBody OrgRecyclablesDto orgRecyclablesDto) {
        return orgRecyclablesService.updateOrgRecyclables(orgRecyclablesDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        orgRecyclablesService.deleteOrgRecyclablesById(id);
    }

    @GetMapping("/count")
    public Long getOrgRecyclablesCount() {
        return orgRecyclablesService.getOrgRecyclablesCount();
    }

    @GetMapping("/{orgName}/count")
    public Long getOrgRecyclablesCount(@PathVariable String orgName) {
        return orgRecyclablesService.getOrgRecyclablesCountByOrgName(orgName);
    }
}
