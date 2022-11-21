package com.example.advanced.repository;

import com.example.advanced.entity.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    public List<Pet> findByPetName(@Param("petName") String petName);

    @Query("select p from Pet p join fetch p.owner")
    public List<Pet> findAllFetchJoin();

    @Override
    @EntityGraph(attributePaths = "owner")
//    fetchJoin하고 싶은 객체를 써줌, 객체의 Graph를 찾으면 어디까지 참조할 수 있게 해줄까?라는 영역을 정해주는 것
    public List<Pet> findAll();

    @EntityGraph(attributePaths = "owner")
//    EntityGraph를 쓸 때에는 무조건 @Query가 있어야함, findAll()은 이미 @Query를 내포하고 있으므로 생략이 가능
    @Query("select p from Pet p where p.petName = :petName")
    public List<Pet> findAllWithOwner(String petName);
}
