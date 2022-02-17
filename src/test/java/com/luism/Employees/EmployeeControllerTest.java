package com.luism.Employees;

import com.luism.Employees.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    private TestRestTemplate restTemplate=new TestRestTemplate();
     static String insertURL;
     static String updateURL;
     static String deleteURL;
     static String getURL;

    @LocalServerPort
     private int port;

//        insertURL="http://localhost:"+port+"/employee/insert";
//        updateURL="http://localhost:"+port+"/employee/update";
//        deleteURL="http://localhost:"+port+"/employee/delete";
//        getURL="http://localhost:"+port+"/employee/employee?id=1";

    @Test
    public void validFieldsInsertion() throws Exception{
        insertURL="http://localhost:"+port+"/employee/insert";
        Employee empIns= new Employee(0L,"Peter Parker",12D,"Peter@gmail.com",null);
        Employee emp = restTemplate.postForObject(insertURL,empIns, Employee.class);
        Assertions.assertNotNull(emp);
        Assertions.assertTrue(emp.getId()>0);
    }
    @Test
    public void validFieldsUpdate() throws Exception{
        updateURL="http://localhost:"+port+"/employee/update";
        insertURL="http://localhost:"+port+"/employee/insert";
        Employee empIns= new Employee(0L,"Peter Parker",12D,"Peter@gmail.com",null);
        Employee emp = restTemplate.postForObject(insertURL,empIns, Employee.class);
        Assertions.assertNotNull(emp);
        Assertions.assertTrue(emp.getId()>0);
        //Update test
        Employee empToUpdate = new Employee(emp.getId(),"Parker Peter",16D,"Parker@gmail.com",null);
        Employee empUpdated = restTemplate.postForObject(updateURL,empToUpdate, Employee.class);
        Assertions.assertNotNull(empUpdated);
        Assertions.assertTrue(empToUpdate.getId()==empUpdated.getId());
        Assertions.assertTrue(empToUpdate.getSalary().equals(empUpdated.getSalary()));
        Assertions.assertTrue(empToUpdate.getName().equals(empUpdated.getName()));
        Assertions.assertTrue(empToUpdate.getEmail().equals(empUpdated.getEmail()));
    }
    @Test
    public void validFieldsDelete() throws Exception{
        deleteURL="http://localhost:"+port+"/employee/delete?id=";
        insertURL="http://localhost:"+port+"/employee/insert";
        Employee empIns= new Employee(0L,"Peter Parker",12D,"Peter@gmail.com",null);
        Employee emp = restTemplate.postForObject(insertURL,empIns, Employee.class);
        Assertions.assertNotNull(emp);
        Assertions.assertTrue(emp.getId()>0);
        //Delete test
        String response=restTemplate.postForObject(deleteURL+emp.getId(),null, String.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Employee deleted",response);
    }
}
