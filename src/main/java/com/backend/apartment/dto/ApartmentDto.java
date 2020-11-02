package com.backend.apartment.dto;

import com.skeleton.dto.BaseDto;
import com.skeleton.model.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDto extends BaseDto<Long> {

    private Float area;

    private Integer maximumRentersNumber;

    private Integer monthlyRent;

    private Integer status;

    private String arabicFullName;

    private String regionName;

    private Integer floorNumber;

    private String streetName;

    private String blockName;

    private String neighborhoodName;

    private String englishFullName;

    private String arabicShortName;

    private String englishShortName;

    private Float latitude;

    private Float longitude;

    private UserDto user;

    private List<ImageDto> images = new ArrayList<>();
}
