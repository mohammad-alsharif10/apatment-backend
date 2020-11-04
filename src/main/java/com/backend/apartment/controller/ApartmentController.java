package com.backend.apartment.controller;

import com.backend.apartment.dto.ApartmentDto;
import com.backend.apartment.service.ApartmentService;
import com.skeleton.response.PageResult;
import com.skeleton.response.SingleResult;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/apartment")
@AllArgsConstructor
@CrossOrigin(origins = ("*"))
public class ApartmentController {

    private final ApartmentService apartmentService;

    @RequestMapping(path = "/apartment-list", method = RequestMethod.GET)
    public ResponseEntity<PageResult<Long, ApartmentDto>> listAll(@RequestParam("page") int page,
                                                                  @RequestParam("size") int size,
                                                                  @RequestParam(required = false, value = "sort") String sort) {

        return apartmentService.findAll(page, size, sort);
    }

    @RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<SingleResult<Long, ApartmentDto>> findById(@PathVariable(name = "id") Long id) {
        return apartmentService.findById(id);
    }


    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SingleResult<Long, ApartmentDto>> deleteEntity(@PathVariable("id") Long id) {
        return apartmentService.delete(id);
    }

    @RequestMapping(value = "/post-apartment", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SingleResult<Long, ApartmentDto>> postApartment(
            @RequestParam(value = "images") MultipartFile[] images,
            @RequestParam(name = "apartment") String apartmentDto) {
        return null;
//        return apartmentService.postApartment(images, apartmentDto);
    }
}

