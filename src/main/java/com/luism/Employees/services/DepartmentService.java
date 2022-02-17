package com.luism.Employees.services;

import com.luism.Employees.models.Department;
import com.luism.Employees.repos.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo repo;

   public List<Department> getAll(){
        return repo.findAll();
    }
   public Department insert(Department dep){
       return repo.save(dep);
   }
}
