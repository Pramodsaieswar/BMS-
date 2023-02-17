package com.bms.floor.service;

import com.bms.floor.Helper.FloorsExcel;
import com.bms.floor.entity.Floors;
import com.bms.floor.repo.FloorsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.*;

@Service
public class FloorsServiceImpl implements FloorsService {
  //  private List<Floors> flatss = new ArrayList<>();
    @Autowired
    FloorsRepo floorsRepo;


    @Override
    public void saveAllData(MultipartFile file) {
        try {
            List<Floors> floors = FloorsExcel.convertExcelToListOfProduct(file.getInputStream());
            floorsRepo.saveAll(floors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Floors> getAll() {

        return floorsRepo.findAll();
    }


    @Override
    //Floors save(Floordto flootdto)
    public Integer save(Floors floors) {
        Floors floordata = new Floors();
        //floors.get we have to use dto//
        floordata.setFloorNo(floors.getFloorNo());
        floordata.setFlatNo(floors.getFlatNo());
        floordata.setFlatStatus(floors.getFlatStatus());
        return  floorsRepo.save(floordata).getFloorid();
    }

    @Override
    public Floors update(int floorid, Floors floors) {

        Optional<Floors> floor =   floorsRepo.findByFloorid(floorid);
        if(floor.isEmpty()){
           return null;
        }

        Floors floors1 = floor.get();
        floors1.setFloorNo(floors.getFloorNo());
        floors1.setFlatNo(floors.getFlatNo());
        floors1.setFlatStatus(floors.getFlatStatus());
        return floorsRepo.save(floors1);
    }

/*    @Override
    public Floors update(@PathVariable("floorid") int floorid, Floors floors) {
        Optional<Floors> existingModel = floorsRepo.findById(floorid);
        if (existingModel.isEmpty()) {
            throw new RuntimeException("Object with ID " + floorid + " not found.");
        }

        // Update the fields of the existing object
        Floors updatedata = existingModel.get();
       updatedata.setFloorNo(floors.getFloorNo());
       updatedata.setFlatNo(floors.getFlatNo());
       updatedata.setFlatStatus(floors.getFlatStatus());
        // Add code to update other fields as needed

        // Save the updated object to the repository
        return floorsRepo.save(updatedata);


    }*/
    ////working but not showing data//
//    @Override                   //
//    public List<Floors> getFlatsByFloor(int floorNo) {
//       List<Floors> floorsList = new ArrayList<>();
//       for(Floors floors:flatss){
//            if(floors.getFloorNo() == floorNo){
//                floorsList.add(floors);
//            }
//       }
//       return floorsList;
//    }


//    public List<Floors> getallflats(int floorNo){
//        Floors flrs = (Floors) floorsRepo.findById(floorNo).get();
//        return (List<Floors>) flrs;
//    }
    @Override
  public Floors getbyid(int floorid) {

        return floorsRepo.findById(floorid).get();

    }


    @Override
    public void deleteById(int floorid) {
         floorsRepo.deleteById(floorid);
    }

    @Override
    public List<Floors> getFlatsByFloorId(@PathVariable int floorNo) {
        return floorsRepo.findByFloorNo(floorNo);
    }
}





