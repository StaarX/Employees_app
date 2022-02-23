package com.luism.Employees.mockito;

import com.luism.Employees.models.Employee;
import com.luism.Employees.repos.EmployeeRepo;
import com.luism.Employees.services.EmployeeService;
import com.luism.Employees.validators.EmployeeDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @InjectMocks
    EmployeeService empService;
    @Mock
    EmployeeRepo empRepo;
    @Mock
    ModelMapper modelMapper;

    @Test
    public void insertTest(){
        EmployeeDTO empDTO=new EmployeeDTO();
        empDTO.setId(0L);
        empDTO.setName("Kayla");
        empDTO.setEmail("Kayla@tcs.com");
        empDTO.setSalary(21D);
        Employee empEntity=new Employee();
        empEntity.setId(0L);
        empEntity.setName("Kayla");
        empEntity.setEmail("Kayla@tcs.com");
        empEntity.setSalary(21D);

        given(modelMapper.map(empDTO,Employee.class)).willReturn(empEntity);
        empEntity.setId(1L);
        given(empRepo.save(empEntity)).willReturn(empEntity);

        Employee empReturned=empService.insert(empDTO);
        Assert.assertTrue(empReturned.getId()>0);

        verify(modelMapper,times(1)).map(empDTO,Employee.class);
        verify(empRepo,times(1)).save(empEntity);
    }

}
