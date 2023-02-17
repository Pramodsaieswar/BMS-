package com.bms.floor.repo;

import com.bms.floor.entity.Floors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FloorsRepo extends JpaRepository<Floors,Integer> {

//when ever u r creating a method us should give same name like findbyFloorNO and im searching for floorNo
    List<Floors> findByFloorNo(Integer floorNo);
    Optional<Floors> findByFloorid(Integer floorid);
}
