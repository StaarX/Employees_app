package com.luism.Employees.validators;

import com.luism.Employees.models.Department;
import com.luism.Employees.models.Regex;
import javax.validation.constraints.*;

public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Employee name cannot be null nor blank")
    @Pattern(regexp = Regex.OnlyLetters, message = "Employees name can only contain letters")
    @Size(min = 4,max=32,message = "Employees name should be between 4 and 32 characters")
    private String name;
    @Min(value = 1,message = "Employee salary must be above 1")
    private Double salary;
    @Email(message = "Employee email not valid")
    private String email;
    private Department department;
    public EmployeeDTO() {
        this.id=-1L;
        this.name = "";
        this.salary = -1D;
        this.email = "";
        this.department=new Department();
    }

    public EmployeeDTO(Long id,String name, Double salary, String email,Department department) {
        this.id=id;
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.department=department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
