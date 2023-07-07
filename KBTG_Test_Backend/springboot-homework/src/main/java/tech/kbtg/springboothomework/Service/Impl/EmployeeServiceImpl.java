package tech.kbtg.springboothomework.Service.Impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.kbtg.springboothomework.DAO.EmployeeDAO;
import tech.kbtg.springboothomework.Entity.Employee;
import tech.kbtg.springboothomework.Service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public Employee deleteEmployee(Integer id) {
        employeeDAO.deleteEmployee(id);
        return null;
    }

    @Override
    public Employee findById(Integer  id) {
        return employeeDAO.findById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeDAO.findByName(name);
    }
}
