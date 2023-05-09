package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.OrgRepresentativeDto;
import com.bsuir.diploma.recycleappbackend.service.OrgRepresentativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{id}")
    public OrgRepresentativeDto findById(@PathVariable Long id) {
        return orgRepresentativeService.findOrgRepresentativeById(id);
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
