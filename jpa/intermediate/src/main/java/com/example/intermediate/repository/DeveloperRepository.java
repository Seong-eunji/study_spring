package com.example.intermediate.repository;

import com.example.intermediate.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    // 파라미터로 받아오는 level을 developerLevel이라는 컬럼으로 사용할 거기 때문에
    // 내부적으로 JPQL이 나갈 수 있도록 키 값을 알려줌
    public List<Developer> findByDeveloperLevel(@Param("developerLevel") int level);
}
