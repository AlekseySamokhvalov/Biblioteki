package com.example.varietyOfImplementations;

import com.example.varietyOfImplementations.entity.Employee;
import com.example.varietyOfImplementations.exception.EmployeeNotFoundException;
import com.example.varietyOfImplementations.service.DepartmentService;
import com.example.varietyOfImplementations.service.DepartmentServiceImpl;
import com.example.varietyOfImplementations.service.EmployeeService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.varietyOfImplementations.utils.EmployeeGenerator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMaxSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMaxSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee2();

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMinSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMinSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void getEmployeesByDepartment_withDepartmentId() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, java.util.Arrays.asList(getEmployee(), getEmployee2()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartment(), getEmployee2().getDepartment());
        assertNotEquals(getEmployee().getDepartment(), getEmployee3().getDepartment());
    }
    @Test
    void getEmployeesByDepartment_withoutDepartmentId() {
        //Подготовка входных данных
        Integer departmentId = null;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, java.util.Arrays.asList(getEmployee(), getEmployee2()));
        expectedMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(getEmployee3()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartment(), getEmployee2().getDepartment());
        assertNotEquals(getEmployee().getDepartment(), getEmployee3().getDepartment());
    }
}
