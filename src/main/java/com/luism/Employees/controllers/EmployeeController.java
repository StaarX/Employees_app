package com.luism.Employees.controllers;

import com.luism.Employees.models.Employee;
import com.luism.Employees.services.EmployeeService;
import com.luism.Employees.validators.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    Logger log=LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping
    public ResponseEntity insertEmployee(@Valid@RequestBody(required = false) EmployeeDTO e){
        Employee em=service.insert(e);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @PutMapping
    public ResponseEntity updateEmployee(@Valid@RequestBody(required = false) EmployeeDTO e){
        Employee em=service.update(e);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @DeleteMapping
    public ResponseEntity deleteEmployee(@RequestParam(name = "id", defaultValue = "-1")Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
    }
    @GetMapping
    public ResponseEntity getEmployee(@RequestParam(name = "id", defaultValue = "getAll")String id){
            List<Employee> e=service.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(e);
    }
}
