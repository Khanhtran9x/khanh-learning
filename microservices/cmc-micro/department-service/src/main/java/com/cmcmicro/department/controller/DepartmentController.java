package com.cmcmicro.department.controller;

import com.cmcmicro.department.entity.Department;
import com.cmcmicro.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department saveDeparment(@RequestBody Department department) {
        log.info("Inside saveDepartment method of controller");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentId(@PathVariable("id") Long departmentId) {
        log.info("Inside findDepartmentId method of controller");
        return departmentService.findDepartmentById(departmentId);
    }
}
