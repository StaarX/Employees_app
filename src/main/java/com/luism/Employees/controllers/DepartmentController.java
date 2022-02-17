package com.luism.Employees.controllers;

import com.luism.Employees.models.Department;
import com.luism.Employees.services.DepartmentService;
import com.luism.Employees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService service;

    @PostMapping("/insert")
    public ResponseEntity insertDepartment(@RequestBody(required = false) Department dep){
        Department em=service.insert(dep);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @GetMapping("/departments")
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
