package com.cmc.school;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SchoolService {

    List<SchoolEntity> schoolEntities = Arrays.asList(
            SchoolEntity.builder().id(1).name("Quang Ninh").studentNumbers(300).build(),
            SchoolEntity.builder().id(2).name("Le Thuy").studentNumbers(350).build(),
            SchoolEntity.builder().id(3).name("Vo Nguyen Giap").studentNumbers(500).build()
    );

    public List<SchoolEntity> getSchoolList() {
        return schoolEntities;
    }

    public SchoolEntity createNewSchool(SchoolEntity schoolEntity) {
        schoolEntity.setId(schoolEntities.stream()
                .filter(school -> school.getId() == schoolEntities.size() - 1)
                .map(SchoolEntity::getId)
                .findAny()
                .orElse(schoolEntities.size()));
        return schoolEntity;
    }
}
