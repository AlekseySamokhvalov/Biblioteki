package com.example.varietyOfImplementations.utils;

import com.example.varietyOfImplementations.entity.Employee;
import java.util.Arrays;

import java.util.List;

public class EmployeeGenerator {
    public static final String FIRST_NAME = "Алексей";
    public static final String LAST_NAME = "Самохвалов";
    public static final double SALARY = 145000;

    public static final String FIRST_NAME_2 = "АлексейПервый";
    public static final String LAST_NAME_2 = "СамохваловПервый";
    public static final double SALARY_2 = 124000;

    public static final String FIRST_NAME_3 = "АлексейВторой";
    public static final String LAST_NAME_3 = "СамохваловВторой";
    public static final double SALARY_3 = 137000;

    public static final int FIRST_DEPARTMENT_ID = 1;

    public static final int SECOND_DEPARTMENT_ID = 2;

    public static Employee getEmployee() {
        return new Employee(FIRST_NAME, LAST_NAME, FIRST_DEPARTMENT_ID, SALARY);
    }

    public static Employee getEmployee2() {
        return new Employee(FIRST_NAME_2, LAST_NAME_2, FIRST_DEPARTMENT_ID, SALARY_2);
    }

    public static Employee getEmployee3() {
        return new Employee(FIRST_NAME_3, LAST_NAME_3, SECOND_DEPARTMENT_ID, SALARY_3);
    }

    public static List<Employee> getAllEmployee() {
        return Arrays.asList(getEmployee(), getEmployee2(), getEmployee3());
    }
}
