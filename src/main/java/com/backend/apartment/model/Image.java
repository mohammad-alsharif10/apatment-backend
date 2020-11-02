package com.backend.apartment.model;

import com.skeleton.model.BaseModel;
import com.skeleton.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseModel<Long> {

    private String path;

    private Boolean isCurrentProfileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;
}
