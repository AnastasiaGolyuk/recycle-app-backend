package com.bsuir.diploma.recycleappbackend.model.mapper;

import com.bsuir.diploma.recycleappbackend.model.dto.BonusDto;
import com.bsuir.diploma.recycleappbackend.model.entity.Bonus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface BonusMapper {

    BonusDto toDto(Bonus bonus);

    Bonus toEntity(BonusDto bonusDto);
}