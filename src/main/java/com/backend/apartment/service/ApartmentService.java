package com.backend.apartment.service;

import com.backend.apartment.database.ApartmentRepository;
import com.backend.apartment.dto.ApartmentDto;
import com.backend.apartment.mapper.ApartmentMapper;
import com.backend.apartment.model.Apartment;
import com.skeleton.database.BaseRepository;
import com.skeleton.mapper.BaseMapper;
import com.skeleton.response.PageResult;
import com.skeleton.response.SingleResult;
import com.skeleton.service.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ApartmentService extends BaseService<Long, Apartment, ApartmentDto> {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    @Override
    public BaseRepository<Apartment, Long> getRepository() {
        return apartmentRepository;
    }

    @Override
    public BaseMapper<Long, ApartmentDto, Apartment> getBaseMapper() {
        return apartmentMapper;
    }

    @Override
    public ResponseEntity<PageResult<Long, ApartmentDto>> findAll(Integer pageNumber, Integer size, String sort) {
        return super.findAll(pageNumber, size, sort);
    }

    @Override
    public ResponseEntity<SingleResult<Long, ApartmentDto>> findById(Long modelId) {
        return super.findById(modelId);
    }

    @Override
    public ResponseEntity<SingleResult<Long, ApartmentDto>> delete(Long modelId) {
        return super.delete(modelId);
    }
}
