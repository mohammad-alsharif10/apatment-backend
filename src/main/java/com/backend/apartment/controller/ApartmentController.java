package com.backend.apartment.controller;

import com.backend.apartment.dto.ApartmentDto;
import com.backend.apartment.service.ApartmentService;
import com.skeleton.response.PageResult;
import com.skeleton.response.SingleResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apartment")
@AllArgsConstructor
@CrossOrigin(origins = ("http://localhost:4200"))
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
}

