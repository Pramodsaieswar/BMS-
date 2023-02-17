package com.bms.floor.service;

import com.bms.floor.entity.Floors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FloorsService {

//List<Floors> getallflats(int floorNo);

      public   void saveAllData(MultipartFile file);

    List<Floors>getAll();

      Integer save(Floors floors);

   //WRK   List<Floors> getFlatsByFloor(int floorNo);
    //Floors update(@PathVariable ("floorid") int floorid, Floors floors);
    Floors update(int floorid, Floors myfloor);

      List<Floors> getFlatsByFloorId(@PathVariable int floorNo);
     Floors getbyid (int floorid);

     void deleteById(int floorid);

      /////////////////////////////////////



}
