package tech.kbtg.springboothomework.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kbtg.springboothomework.Entity.Employee;
import tech.kbtg.springboothomework.Entity.Exception.BadRequest;
import tech.kbtg.springboothomework.Entity.Exception.UserNotFound;
import tech.kbtg.springboothomework.Entity.Response.SuccessfullyResponse;
import tech.kbtg.springboothomework.Entity.UpdateEmployee;
import tech.kbtg.springboothomework.Service.EmployeeService;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        employee.setStatus("Current");
        Employee newEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(newEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        try {
            Employee employee = employeeService.findById(id);
            employee.setStatus("Deleted");
            Employee updateEmployee = employeeService.save(employee);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody UpdateEmployee updateEmployee) {
        String name = updateEmployee.getName();
        String lastname = updateEmployee.getLastname();
        String nickname = updateEmployee.getNickname();
        String address = updateEmployee.getAddress();
        Employee employee = employeeService.findById(id);
        employee.setName(name);
        employee.setLastName(lastname);
        employee.setAddress(address);
        employee.setNickName(nickname);
        Employee data = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @RequestMapping("/employees/salary/{id}")
    public ResponseEntity<Employee> updateSalary(@PathVariable int id, @RequestParam int salary) {
        Employee employee = employeeService.findById(id);
        System.out.println(salary);
        if (salary > 100) {
            throw new UserNotFound("Bad Request");
        }
        employee.setSalary((employee.getSalary() * (100 + salary)) / 100);
        Employee updateSalary = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(updateSalary);
    }

    @RequestMapping("/employees/{position}/{id}")
    public ResponseEntity<?> checkPosition(@PathVariable String position, @PathVariable int id, @RequestParam int salary) {
        Employee employee = employeeService.findById(id);
        String currentPosition = employee.getPosition();
        System.out.println(currentPosition);
        System.out.println(currentPosition.equals(position));
        if (currentPosition.equals(position) == false) {
            return new ResponseEntity("Current Position Incorrect", HttpStatus.BAD_REQUEST);
        }
        employee.setSalary(salary);
        Employee updateSalary = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.OK).body(updateSalary);
    }

    @GetMapping("/employees/name")
    public ResponseEntity<List<Employee>> checkPosition(@RequestParam String q) {
        List<Employee> employees = employeeService.findByName(q);
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @RequestMapping("/employees/delete")
    public ResponseEntity<?> deleteMultipleEmployee(@RequestParam List<Integer> id) {
        List<Employee> employees = employeeService.findAll();
        for (int i = 0; i < id.size(); i++) {
            Integer index = id.get(i);
            Employee employee = employeeService.findById(index);
            employee.setStatus("Deleted");
            Employee newEmployee = employeeService.save(employee);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employees);
    }
}
