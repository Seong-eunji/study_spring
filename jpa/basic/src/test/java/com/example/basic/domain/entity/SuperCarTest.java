package com.example.basic.domain.entity;

import com.example.basic.type.SuperCarBrand;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class SuperCarTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(false)
    public void SuperCarTest() {
        SuperCar supercar1 = new SuperCar();
        SuperCar supercar2 = new SuperCar();

        supercar1.setSuperCarBrand(SuperCarBrand.LAMBORGHINI);
        supercar1.setSuperCarColor("노란색");
        supercar1.setSuperCarName("붕붕이");
        supercar1.setSuperCarPrice(4000000000L);

        supercar2.setSuperCarBrand(SuperCarBrand.BENTLEY);
        supercar2.setSuperCarColor("파란색");
        supercar2.setSuperCarName("타요");
        supercar2.setSuperCarPrice(2900000000L);

        entityManager.persist(supercar1);
        entityManager.persist(supercar2);

        entityManager.flush();

        SuperCar findCar1 = entityManager.find(SuperCar.class, 1L);
        SuperCar findCar2 = entityManager.find(SuperCar.class, 2L);

        log.info(findCar1.toString());
        log.info(findCar2.toString());

        entityManager.clear();

        findCar1 = entityManager.find(SuperCar.class, 2L);
        findCar2 = entityManager.find(SuperCar.class, 1L);

        log.info(findCar1.toString());
        log.info(findCar2.toString());

        entityManager.detach(entityManager.find(SuperCar.class, 2L));

        log.info("Suuuuuuuuuuuper : " + Optional.ofNullable(entityManager.find(SuperCar.class, 2L)).orElse(entityManager.find(SuperCar.class, 2L)));
    }
}