package tech.kbtg.springboothomework.DAO.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.kbtg.springboothomework.DAO.EmployeeDAO;
import tech.kbtg.springboothomework.Entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeImpl implements EmployeeDAO {
    private EntityManager entityManager;

    public EmployeeImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public List<Employee> findByName (String name) {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> checkWording = query.getResultList();
        List<Employee> result = new ArrayList<Employee>();
        for (Employee buffer: checkWording) {
            if(buffer.getName().contains(name)){
                result.add(buffer);
            }
        }
        return result;
    }
}
