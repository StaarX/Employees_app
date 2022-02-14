package com.luism.Employees.controllers;

import com.luism.Employees.models.Employee;
import com.luism.Employees.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    Logger log=LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/insert")
    public ResponseEntity insertEmployee(@RequestBody Employee e){
//        if (e.getDepartment().getId()<0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee, department must be specified");
        Employee em=service.insert(e);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody Employee e){
        Employee em=service.update(e);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@RequestParam(name = "id", defaultValue = "-1")Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
    }
    @GetMapping("/employee")
    public ResponseEntity getEmployee(@RequestParam(name = "id", defaultValue = "-1")Long id){
        Employee e=service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }
    @GetMapping("/employees")
    public ResponseEntity getEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
