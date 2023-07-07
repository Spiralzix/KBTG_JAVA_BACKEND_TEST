package tech.kbtg.springboothomework.DAO;

import tech.kbtg.springboothomework.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee save(Employee employee);
    List< Employee > findAll();
    void deleteEmployee( Integer id);

    Employee findById(Integer id);

    List<Employee> findByName(String name);
}
