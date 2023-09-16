package com.example.varietyOfImplementations.service;

import com.example.varietyOfImplementations.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(Integer departmentId);
    Employee getEmployeeWithMinSalary(Integer departmentId);
    Collection<Employee> getEmployees(Integer departmentId);
    Map<Integer, List<Employee>> getEmployees();
}
