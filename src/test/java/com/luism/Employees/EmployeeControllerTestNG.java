package com.luism.Employees;

import com.luism.Employees.models.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class EmployeeControllerTestNG {
    RestTemplate restTemplate=new RestTemplate();
    private String URL="http://localhost:8080/employee";
    private String urlWithID="http://localhost:8080/employee?id=";
    private Employee emp;
    /*
    Tests that an Employee is inserted correctly
     */
    @BeforeClass
    public void setUp(){
        emp=new Employee();
        emp.setId(0L);
        emp.setName("Peter");
        emp.setEmail("Peter@tcs.com");
        emp.setSalary(30D);
    }
    @Test(priority = 1)
    public void insertEmployeeTest(){
        ResponseEntity<Employee> returned=restTemplate.postForEntity(URL,emp,Employee.class);
        Assert.assertTrue(returned.getStatusCode().value()==200);
        Assert.assertTrue(returned.getBody().getId()>0);
        emp=returned.getBody();
    }
    /*
    Tests that an Employee is updated correctly
     */
    @Test(priority = 2)
    public void updateEmployeeTest(){
        emp.setName("John Cena");
        emp.setEmail("John@tcs.com");
        emp.setSalary(12D);
        HttpEntity<Employee> requestEntity = new HttpEntity<>(emp, null);
        ResponseEntity<Employee> returned=restTemplate.exchange(URL, HttpMethod.PUT,requestEntity,Employee.class);
        Assert.assertTrue(returned.getStatusCode().value()==200);
        Assert.assertTrue(returned.getBody().getId()==emp.getId());
        Assert.assertTrue(returned.getBody().getName().equals(emp.getName()));
        Assert.assertTrue(returned.getBody().getEmail().equals(emp.getEmail()));
        Assert.assertTrue(returned.getBody().getSalary().equals(emp.getSalary()));
    }
    /*
    Tests that an Employee is retrieved correctly
     */
    @Test(priority = 3)
    public void getEmployeeTest(){
        String urlWithID=this.urlWithID+emp.getId();
        ResponseEntity<Employee[]> returned = restTemplate.exchange(urlWithID,HttpMethod.GET,new HttpEntity<>(null,null),Employee[].class);
        Assert.assertTrue(returned.getStatusCode().value()==200);
        Assert.assertTrue(returned.getBody()[0].getId()==emp.getId());
        Assert.assertTrue(returned.getBody()[0].getName().equals(emp.getName()));
        Assert.assertTrue(returned.getBody()[0].getEmail().equals(emp.getEmail()));
        Assert.assertTrue(returned.getBody()[0].getSalary().equals(emp.getSalary()));
    }
    /*
    Tests that an Employee is deleted correctly
     */
    @Test(priority = 4)
    public void deleteEmployeeTest(){
        String urlWithID=this.urlWithID+emp.getId();
        ResponseEntity<String> returned = restTemplate.exchange(urlWithID,HttpMethod.DELETE,new HttpEntity<>(null,null),String.class);
        Assert.assertTrue(returned.getStatusCode().value()==200);
        Assert.assertTrue(returned.getBody().equals("Employee deleted"));

        try{
            returned=restTemplate.exchange(urlWithID,HttpMethod.GET,new HttpEntity<>(null,null),String.class);
        }catch (HttpClientErrorException ex){
            System.out.println(ex.getMessage());
            Assert.assertTrue(ex.getMessage().contains("404"));
            Assert.assertTrue(ex.getMessage().contains("Employee not found."));
        }
    }

}
