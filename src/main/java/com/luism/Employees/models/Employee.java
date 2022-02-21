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
    private String name;
    @Column
    private Double salary;
    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name="department_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Department department;

    public Employee() {
        this.id = -1L;
        this.salary=-1D;
        this.name="";
        this.email="";
        this.department=new Department();
    }

    public Employee(Long id, String name, Double salary, String email,Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.department=department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
