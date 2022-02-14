package com.luism.Employees.services;

import com.luism.Employees.models.Employee;
import com.luism.Employees.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    public Employee insert(Employee e){
        return repo.save(e);
    }
    public Employee update(Employee e){
        if (!repo.existsById(e.getId())) return null;
        return repo.save(e);
    }
    public boolean delete(Long id){
        repo.deleteById(id);
        if(repo.existsById(id)) return false;
        return true;
    }
    public Employee get(Long id){
        Employee found=null;
        if (repo.findById(id).isPresent()) found=repo.findById(id).get();
        return found;
    }
    public List<Employee> getAll(){
        return repo.findAll();
    }
}
