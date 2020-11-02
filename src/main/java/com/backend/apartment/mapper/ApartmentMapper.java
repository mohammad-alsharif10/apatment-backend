package com.backend.apartment.mapper;

import com.backend.apartment.dto.ApartmentDto;
import com.backend.apartment.model.Apartment;
import com.skeleton.mapper.BaseMapper;
import com.skeleton.mapper.UserMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserMapper.class,ImageMapper.class})
public interface ApartmentMapper extends BaseMapper<Long, ApartmentDto, Apartment> {

    @Override
    ApartmentDto toBaseDto(Apartment baseModel);

    @Override
    Apartment toBaseEntity(ApartmentDto baseDto);

    @Override
    List<ApartmentDto> toBaseDtoList(List<Apartment> list);

    @Override
    List<Apartment> toBaseEntityList(List<ApartmentDto> list);
}
