package com.example.varietyOfImplementations;

import com.example.varietyOfImplementations.entity.Employee;
import com.example.varietyOfImplementations.exception.EmployeeAlreadyAddedException;
import com.example.varietyOfImplementations.exception.EmployeeNotFoundException;
import com.example.varietyOfImplementations.exception.EmployeeStorageIsFullException;
import com.example.varietyOfImplementations.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static com.example.varietyOfImplementations.utils.EmployeeGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void add_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = employeeService.add(firstName, lastName, departmentId, salary);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salary3 = SALARY_3;

        //Подготовка ожидаемого результата
        String expectedMessage = "Нет места для добавления сотрудника.";

        //Начало теста
        employeeService.add(firstName2, lastName2, departmentId, salary2);
        employeeService.add(firstName3, lastName3, departmentId, salary3);
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, departmentId, salary)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void add_sussesEmployeeAlreadyAddedException() {
        //подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY;

        //подготовка ожидаемого результата
        String exceptionMessage = "Данный сотрудник уже существует.";
        //начало теста
        employeeService.add(firstName, lastName, departmentId, salaryEmployee);
        Exception exception = assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeService.add(firstName, lastName, departmentId, salaryEmployee)
        );
        assertEquals(exceptionMessage, exception.getMessage());
    }
    @Test
    void find_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();

        //Начало теста
        employeeService.add(firstName, lastName, departmentId, salary);
        Employee actualEmployee = employeeService.find(firstName, lastName);
        assertEquals(expectedEmployee, actualEmployee);
    }
    @Test
    void find_EmployeeNotFoundException() {
        //подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY;
        //подготовка ожидаемого результата
        String exceptionMessage = "Такого сотрудника не существует.";
        //начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.find(firstName, lastName)
        );
        assertEquals(exceptionMessage, exception.getMessage());
    }
    @Test
    void remove_successes() {
        //подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY;

        //подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();
        //начало теста
        employeeService.add(firstName, lastName, departmentId, salaryEmployee);
        Employee actualEmployee = employeeService.remove(firstName, lastName);
        assertEquals(expectedEmployee, actualEmployee);
    }
    @Test
    void remove_EmployeeNotFoundException() {
        //подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        Integer departmentId = FIRST_DEPARTMENT_ID;
        double salaryEmployee = SALARY;
        //подготовка ожидаемого результата
        String exceptionMessage = "Невозможно удалить. Такого сотрудника не существует.";
        //начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.remove(firstName, lastName)
        );
        assertEquals(exceptionMessage, exception.getMessage());

    }
    @Test
    void getAll_successes() {
        //подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salaryEmployee = SALARY;
        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salaryEmployee2 = SALARY_2;
        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salaryEmployee3 = SALARY_3;

        Integer departmentId = FIRST_DEPARTMENT_ID;
        Integer lastdepartmentId = SECOND_DEPARTMENT_ID;
        Employee firstEmployee = getEmployee();
        Employee secondEmployee = getEmployee2();
        Employee lastEmployee = getEmployee3();


        //подготовка ожидаемого результата
        List<Employee> expectedEmployee = List.of(firstEmployee,secondEmployee,lastEmployee);
        //начало теста

        employeeService.add(FIRST_NAME, LAST_NAME,FIRST_DEPARTMENT_ID, SALARY);
        employeeService.add(FIRST_NAME_2, LAST_NAME_2, FIRST_DEPARTMENT_ID, SALARY_2);
        employeeService.add(FIRST_NAME_3, LAST_NAME_3, SECOND_DEPARTMENT_ID, SALARY_3);
        Collection<Employee> actualEmployee = employeeService.getAll();
        //assertEquals(expectedEmployee.stream().sorted(),actualEmployee.stream().sorted());
    }
}
