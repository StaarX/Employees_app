package com.luism.Employees.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Employee name cannot be null nor blank")
    @Pattern(regexp = Regex.OnlyLetters, message = "Employees name can only contain letters")
    @Size(min = 4,max=32,message = "Employees name should be between 4 and 32 characters")
    private String name;

    @Column
    @Min(value = 1,message = "Employee salary must be above 1")
    private Double salary;
    @Email(message = "Employee email not valid")
    @Column
    private String email;

//    @ManyToOne (cascade = CascadeType.ALL)
//    @JoinColumn(name="employee_id",referencedColumnName = "id")
//    private Department department;

    public Employee() {
        this.id = -1L;
        this.salary=-1D;
        this.name="";
        this.email="";
    }

    public Employee(Long id, String name, Double salary, String email) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
//        this.department=department;
    }

//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                '}';
    }
}
