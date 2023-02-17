package com.bms.floor.controller;

import com.bms.floor.Helper.FloorsExcel;
import com.bms.floor.entity.Floors;

import com.bms.floor.repo.FloorsRepo;
import com.bms.floor.service.FloorsService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/floorapi")
public class FloorsController {
    @Autowired
    FloorsService floorsService;
    @Autowired
    FloorsRepo floorsRepo;



    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadAllExcelFile(@RequestParam("excel") MultipartFile file) {
        if (FloorsExcel.checkExcelFormat(file)) {
            this.floorsService.saveAllData(file);

            return ResponseEntity.ok((Map.of("message", "file uploaded  successfully")));
        }

        return ResponseEntity.ok(Map.of("message", "file is not uploaded"));
    }

@GetMapping("/all")
    public List<Floors>getall(){
        List<Floors> flrs = floorsService.getAll();
        return flrs;
    }

@GetMapping("/find/{floorid}")
public ResponseEntity<Floors> getbyID(@PathVariable("floorid") int floorid){
        Floors fid = floorsService.getbyid(floorid);
        return ResponseEntity.status(HttpStatus.OK).body(fid);
}




    // This is just an example list of fields. You would replace this with your own data source.

    @GetMapping("/floor/floornum/{floorNo}")
    public List<Floors> getEmployeesByCompany(@PathVariable Integer floorNo) {

        return floorsService.getFlatsByFloorId(floorNo);
    }

     /*  @PutMapping("/update/{floorid}")                                          //   here we can use  Dto dto/ and entity en ,en.set(dto.get)
       public ResponseEntity<?>updatebyid(@RequestBody Floors floors ,@PathVariable Integer floorid){
            Floors floors1 = floorsService.getbyid(floorid);
            floors1.setFloorNo(floors.getFloorNo());
            floors1.setFlatNo(floors.getFlatNo());
            floors1.setFlatStatus(floors.getFlatStatus());
            floorsService.save(floors1);
            return ResponseEntity.ok(Map.of("message", "the data is updated :" + floorid));
    }*/

    @PutMapping("/{floorid}")
    public ResponseEntity<Floors> updateMyModel(@PathVariable("floorid") int    floorid, @RequestBody Floors myModel) {
        // Call the service implementation method to update the object
        Floors updatedModel = floorsService.update(floorid,myModel);

        // Return the updated object in the response body
        return ResponseEntity.ok(updatedModel);
    }

    @PostMapping("/saveone")
    public ResponseEntity<?> savesingledata(@RequestBody Floors floors){
        floors.setFloorNo(floors.getFloorNo());
        floors.setFlatNo(floors.getFlatNo());
        floors.setFlatStatus(floors.getFlatStatus());
        Integer sd = floorsService.save(floors);
        return ResponseEntity.ok(Map.of("message", "your file is upload successfully"));
    }


    @DeleteMapping ("/delete/{floorid}")
    public ResponseEntity<?>deletedata(@PathVariable int floorid){
        floorsService.deleteById(floorid);
        return ResponseEntity.ok(Map.of("message","the data is deleted at :"+floorid));
    }

}







