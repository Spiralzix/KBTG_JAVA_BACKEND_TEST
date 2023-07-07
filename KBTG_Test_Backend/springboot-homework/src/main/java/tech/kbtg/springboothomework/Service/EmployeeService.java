package tech.kbtg.springboothomework.Service;

import tech.kbtg.springboothomework.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);
    List< Employee > findAll();
    Employee deleteEmployee( Integer id);

    Employee findById(Integer id);

    List<Employee> findByName(String name);
}
