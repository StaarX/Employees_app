package com.luism.Employees.models;

import javax.persistence.*;

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

//    @ManyToOne (cascade = CascadeType.ALL)
//    @JoinColumn(name="employee_id",referencedColumnName = "id")
//    private Department department;

    public Employee() {
        this.id = -1L;
        this.email="";
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
