package com.example.varietyOfImplementations;

import com.example.varietyOfImplementations.exception.EmployeeAlreadyAddedException;
import com.example.varietyOfImplementations.exception.EmployeeNotFoundException;
import com.example.varietyOfImplementations.exception.EmployeeStorageIsFullException;
import com.example.varietyOfImplementations.exception.ValidateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
//import SpringUtils.java;




import java.util.*;

@Service
public class EmployeeService {
    private static final int MaxSize = 10;
    public Map<String, Employee> employees = new HashMap<>();

    public EmployeeService(){
        this.employees = new HashMap<>();
        add("Иван", "Иванович", 1, 50000.0);
        add("Петр", "Петрович", 2, 68000.0);
        add("Сидор", "Сидорович", 3, 45000.0);
        add("Алексей", "Алексеевич", 1, 50000.0);
        add("Виктор","Викторович", 2, 60000.0);
        add("Иван", "Алексеевич", 3, 65000.0);
    }

    public  Employee add(String firstName, String lastName, int department, double salary){

        // 1)Проверка записи с большой буквы
        firstName = StringUtils.capitalize(firstName);
//        if(!firstName.equals(firstNameCapitalize)){
//            firstName = firstNameCapitalize;
//            //throw new ValidateException("Имя сотрудника должно быть с большой буквы");
//        }
        lastName = StringUtils.capitalize(lastName);
//        if(!lastName.equals(lastNameCapitalize)){
//            lastName = lastNameCapitalize;
//        }

        // 2) Проверка на отсутствие запрещёныных символов
        validateFirstAndLastNames(firstName, lastName);

        if (employees.size()>MaxSize){
            throw new EmployeeStorageIsFullException("Нет места для добавления сотрудника.");
        }
        String key = getKey(firstName, lastName);

        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже существует.");
        }

        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        employees.put(key, newEmployee);
        return newEmployee;
    }

    public Employee remove(String firstName, String lastName){
        // 1)Проверка записи с большой буквы
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        // 2) Проверка на отсутствие запрещёныных символов
        validateFirstAndLastNames(firstName, lastName);

        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Невозможно удалить. Такого сотрудника не существует.");
        }

        Employee employeeForRemove = employees.get(key);

        employees.remove(key);
        return employeeForRemove;
    }

    public Employee find(String firstName, String lastName){
        // 1)Проверка записи с большой буквы
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        // 2) Проверка на отсутствие запрещёныных символов
        validateFirstAndLastNames(firstName, lastName);

        String key = getKey(firstName, lastName);

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такого сотрудника не существует.");
        }

        return employees.get(key);

    }
    public Collection<Employee> getAll(){
        return employees.values();

    }
    public Collection<Employee> findAll(){
        return Collections.unmodifiableCollection(employees.values());
    }
    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
    private void validateFirstAndLastNames(String firstName, String lastName){
        if(!StringUtils.isAlpha(firstName)) {
            throw new ValidateException("Имя содержит запрещенные символы");
        }

        if(!StringUtils.isAlpha(lastName)) {
            throw new ValidateException("Фамилия содержит запрещенные символы");
        }
    }
}
