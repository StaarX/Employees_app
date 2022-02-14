package com.luism.Employees.repos;

import com.luism.Employees.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentRepo extends CrudRepository<Department,Long> {
    List<Department> findAll();
}
