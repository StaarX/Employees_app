package com.luism.Employees.services;

import com.luism.Employees.errorHandlers.exceptions.InternalException;
import com.luism.Employees.errorHandlers.exceptions.InvalidFieldException;
import com.luism.Employees.errorHandlers.exceptions.NoEmployeeFoundException;
import com.luism.Employees.models.Department;
import com.luism.Employees.models.Employee;
import com.luism.Employees.repos.DepartmentRepo;
import com.luism.Employees.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;
    @Autowired
    DepartmentRepo dptRepo;
    public Employee insert(Employee e){
        if (e==null) throw new InvalidFieldException("Invalid employee, employee cannot be null");
        if (e.getId()!=0) throw new InvalidFieldException("For insertions, ID must be 0.");
        if (e.getDepartment().getId()==-1) e.setDepartment(null);
        Employee emp=repo.save(e);
        if (emp.getId()<0) throw new InvalidFieldException("Employee could not be added.");
        return emp;
    }
    public Employee update(Employee e){
        if (e==null || e.getId()<0) throw new InvalidFieldException("Invalid employee, employee cannot be null and must contain a valid ID.");
        if (!repo.existsById(e.getId())) throw new NoEmployeeFoundException("Employee not found.");
        if (e.getDepartment().getId()>0){
           if (dptRepo.findById(e.getDepartment().getId()).isPresent()){
               e.setDepartment(dptRepo.findById(e.getDepartment().getId()).get());
           }else{
               throw new InvalidFieldException("Department could not be found, update failed.");
           }
        }else{
            e.setDepartment(null);
        }
        Employee emp=repo.save(e);
        if (emp.getId()<0) throw new InternalException("Employee could not be updated.");
        return emp;
    }
    public boolean delete(Long id){
        if (id<0) throw new InvalidFieldException("Invalid employee.");
        if (!repo.existsById(id)) throw new NoEmployeeFoundException("Employee not found.");
        repo.deleteById(id);
        if(repo.existsById(id)) throw new InternalException("Employee cannot be deleted.");
        return true;
    }
    public List<Employee> get(String id){
        List<Employee> listOne= new ArrayList<>();
        if (id.equals("getAll")){
            return repo.findAll();
        }else{
            Long idL;
            try {
                idL=Long.parseLong(id);
            }catch (NumberFormatException nfe){
                throw new InvalidFieldException("Id must contain only numbers");
            }
            if (idL<1) throw new InvalidFieldException("Invalid ID for employee.");
            if (repo.findById(idL).isPresent()){
             listOne.add(repo.findById(idL).get());
             return listOne;
            }
            throw new NoEmployeeFoundException("Employee not found.");
        }
    }
}
