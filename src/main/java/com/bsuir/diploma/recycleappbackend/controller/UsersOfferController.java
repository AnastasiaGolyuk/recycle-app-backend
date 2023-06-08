package com.bsuir.diploma.recycleappbackend.controller;

import com.bsuir.diploma.recycleappbackend.model.dto.UsersOfferDto;
import com.bsuir.diploma.recycleappbackend.service.UsersOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users-offer")
public class UsersOfferController {

    private final UsersOfferService usersOfferService;

    @PostMapping
    public UsersOfferDto save(@RequestBody UsersOfferDto usersOfferDto) {
        return usersOfferService.saveUsersOffer(usersOfferDto);
    }

    @GetMapping
    public List<UsersOfferDto> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                      @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<UsersOfferDto> usersOfferPage = usersOfferService.findAllUsersOffers(PageRequest.of(page, size));
        return new ArrayList<>(usersOfferPage.getContent());
    }

    @GetMapping("/{id}")
    public UsersOfferDto findById(@PathVariable Long id) {
        return usersOfferService.findUsersOfferById(id);
    }


    @GetMapping("/users/{userId}")
    public List<UsersOfferDto> findAllByUserId(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                 @PathVariable Long userId) {
        Page<UsersOfferDto> usersOfferPage = usersOfferService.findUsersOfferByUserId(PageRequest.of(page, size), userId);
        return new ArrayList<>(usersOfferPage.getContent());
    }



    @PatchMapping
    public UsersOfferDto update(@RequestBody UsersOfferDto usersOfferDto) {
        return usersOfferService.updateUsersOffer(usersOfferDto);
    }

    @GetMapping("/count")
    public Long getUsersOffersCount() {
        return usersOfferService.getUsersOffersCount();
    }

    @GetMapping("/user/{id}/count")
    public Long getUsersOffersCountByUserId(@PathVariable Long id) {
        return usersOfferService.getUsersOffersCountByUserId(id);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        usersOfferService.deleteUsersOfferById(id);
    }

}
