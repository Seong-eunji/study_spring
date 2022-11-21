package com.example.advanced.entity;

import com.example.advanced.type.CarBrand;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_CAR")
@Getter @Setter @ToString(exclude  = "carOwner")
// 양방향이 되었을 경우, ToString에서 Car와 CarOwner가 서로를 순환 참조하면서
// 무한 루프에 빠질 수 있으므로 ToString에서 연관관계에 있는 객체를 빼주어야함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Car extends Period{
    @Id @GeneratedValue
    private Long carId;
    @Enumerated(EnumType.STRING)
    private CarBrand carBrand;
    private String carName;
    private String carColor;
    private Long carPrice;
    private LocalDateTime carReleaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_OWNER_ID")
    // @ToString.Exclude   // @ToString(exclude = "carOwner")랑 같음. 둘 중 아무거나 쓰기
    private CarOwner carOwner;

    public void create(CarBrand carBrand, String carName, String carColor, Long carPrice, LocalDateTime carReleaseDate) {
        this.carBrand = carBrand;
        this.carName = carName;
        this.carColor = carColor;
        this.carPrice = carPrice;
        this.carReleaseDate = carReleaseDate;
    }

    public void changeCarOwner(CarOwner carOwner){
        this.carOwner = carOwner;
    }
}
