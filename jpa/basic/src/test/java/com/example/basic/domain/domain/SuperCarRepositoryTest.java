package com.example.basic.domain.domain;

import com.example.basic.domain.entity.SuperCar;
import com.example.basic.repository.SuperCarRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SuperCarRepositoryTest {
    @Autowired
    private SuperCarRepository superCarRepository;

    @Test
    public void findBySuperCarReleaseDateTest(){
        superCarRepository.findBySuperCarReleaseDate(LocalDateTime.of(2019, 12, 4, 0, 0))
                .stream().map(SuperCar::toString).forEach(log::info);
    }

    @Test
    public void findBySuperCarColorContainingTest(){
        superCarRepository.findBySuperCarColorContaining("e").stream().map(SuperCar::toString).forEach(log::info);

    }

    @Test
    public void getCountOfSuperCarTest(){
        superCarRepository.count();
    }

    @Test
    public void findAll(){
        // PageRequest는 Page의 자식이므로 upcasting되며 들어감
        // 시작할 페이지(0부터 시작), 가져올 페이지 수, 정렬 기준이 될 엔티티명을 넘겨주면 Sort가 정렬해줌
        // startNumber, endNumber, total 등등 페이징에 필요한 것 다 알아서 가져옴, 따로 직접 계산해줄 필요 없음
        Page<SuperCar> superCars = superCarRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "superCarId")));
        log.info("number : " + superCars.getNumber());      // 현재 페이지
        log.info("number : " + superCars.getNumber());      // 총 페이지 개수(realEndPage)
        log.info("number : " + superCars.getNumber());      // 다음 페이지가 있는지 여부
        log.info("number : " + superCars.getNumber());      // 이전 페이지가 있는지 여부
        log.info("number : " + superCars.getNumber());      // 첫번째 페이지인지
        log.info("number : " + superCars.getNumber());      // 마지막 페이지인지
    }

    @Test
    public void findBySuperCarReleaseDateBetweenTest(){
        LocalDateTime startDate = LocalDateTime.of(2019, 1, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 11, 11, 0, 0, 0);
        Assertions.assertThat(superCarRepository.findBySuperCarReleaseDateBetween(startDate, endDate)).isNotEmpty();
        log.info("이 기간동안 출시된 차 : " + superCarRepository.findBySuperCarReleaseDateBetween(startDate, endDate));
    }

    @Test
    public void findTop10OrderBySuperCarIdDescTest(){
        superCarRepository.findTop10ByOrderBySuperCarIdDesc().stream().map(SuperCar::toString).forEach(log::info);
    }

    @Test
    public void bulkUpdateTest(){
        log.info("count : " + superCarRepository.update(LocalDateTime.of(2019,12,4,0,0,0)));
    }
}
