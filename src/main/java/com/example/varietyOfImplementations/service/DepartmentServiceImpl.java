package com.example.varietyOfImplementations.service;

import com.example.varietyOfImplementations.Employee;
import com.example.varietyOfImplementations.EmployeeNotFoundException;
import com.example.varietyOfImplementations.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class DepartmentServiceImpl implements  DepartmentService{

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Ничего не найдено"));

    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Ничего не найдено"));
    }

    @Override
    public Collection<Employee> getEmployees(Integer departmentId) {
        return employeeService.findAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployees() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
