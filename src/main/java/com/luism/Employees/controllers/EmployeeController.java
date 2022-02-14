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
        if (e.getId()<0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee");
        Employee em=service.insert(e);
        if (em==null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee could not be added");
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody Employee e){
        if (e.getId()<0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee");
        Employee em=service.update(e);
        if (em==null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee could not be updated");
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@RequestParam(name = "id", defaultValue = "-1")Long id){
        if (id<0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID");
        if (!service.delete(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee does not exist or cannot be deleted");
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
    }
    @GetMapping("/employee")
    public ResponseEntity getEmployee(@RequestParam(name = "id", defaultValue = "-1")Long id){
        if (id<0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID");
        Employee e=service.get(id);
        if (e==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee could not be found");
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }
    @GetMapping("/employees")
    public ResponseEntity getEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
