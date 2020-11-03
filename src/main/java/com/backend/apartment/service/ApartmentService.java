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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Slf4j
public class ApartmentService extends BaseService<Long, Apartment, ApartmentDto> {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;
    private final ImageService imageService;

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

    public ResponseEntity<SingleResult<Long, ApartmentDto>> postApartment(MultipartFile[] images, ApartmentDto apartmentDto) {
        Apartment apartment = apartmentRepository.save(apartmentMapper.toBaseEntity(apartmentDto));
        apartment.setImages(imageService.uploadApartmentImages(images, apartment.getId()));
        return new ResponseEntity<>(
                new SingleResult<>(
                        false,
                        200,
                        "Images have been uploaded",
                        apartmentMapper.toBaseDto(apartmentRepository.save(apartment))),
                HttpStatus.OK);
    }
}
