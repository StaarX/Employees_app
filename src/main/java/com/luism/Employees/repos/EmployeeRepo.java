package com.luism.Employees.repos;

import com.luism.Employees.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Long> {

    List<Employee> findAll();
}
