package com.backend.apartment.dto;

import com.skeleton.dto.BaseDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto extends BaseDto<Long> {

    private String path;

    private Boolean isCurrentProfileImage;
}
