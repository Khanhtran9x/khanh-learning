package com.cmc.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<SchoolEntity>> getSchools() {
        return new ResponseEntity<>(schoolService.getSchoolList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SchoolEntity> createNewSchool(@RequestBody SchoolEntity schoolEntity) {
          return new ResponseEntity<>(schoolService.createNewSchool(schoolEntity), HttpStatus.CREATED);
    }
}
