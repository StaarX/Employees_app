package com.luism.Employees.services;

import com.luism.Employees.errorHandlers.exceptions.InternalException;
import com.luism.Employees.errorHandlers.exceptions.InvalidFieldException;
import com.luism.Employees.errorHandlers.exceptions.NoEmployeeFoundException;
import com.luism.Employees.models.Employee;
import com.luism.Employees.repos.DepartmentRepo;
import com.luism.Employees.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;
    public Employee insert(Employee e){
        if (e.getId()<0) throw new InvalidFieldException("Invalid employee");
        Employee emp=repo.save(e);
        if (emp.getId()<0) throw new InvalidFieldException("Employee could not be added");
        return emp;
    }
    public Employee update(Employee e){
        if (e.getId()<0) throw new InvalidFieldException("Invalid employee");
        if (!repo.existsById(e.getId())) throw new NoEmployeeFoundException("Employee not found");
        Employee emp=repo.save(e);
        if (emp.getId()<0) throw new InternalException("Employee could not be updated");
        return emp;
    }
    public boolean delete(Long id){
        if (id<0) throw new InvalidFieldException("Invalid employee");
        if (!repo.existsById(id)) throw new NoEmployeeFoundException("Employee not found");
        repo.deleteById(id);
        if(repo.existsById(id)) throw new InternalException("Employee cannot be deleted");
        return true;
    }
    public Employee get(Long id){
        if (id<0) throw new InvalidFieldException("Invalid ID for employee");
        if (repo.findById(id).isPresent()) return repo.findById(id).get();
        throw new NoEmployeeFoundException("Employee not found");
    }
    public List<Employee> getAll(){
        return repo.findAll();
    }
}
