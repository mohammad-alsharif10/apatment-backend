package com.backend.apartment.model;


import com.skeleton.model.BaseModel;
import com.skeleton.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "apartment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apartment extends BaseModel<Long> {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(
            mappedBy = "apartment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Image> images = new ArrayList<>();

}
