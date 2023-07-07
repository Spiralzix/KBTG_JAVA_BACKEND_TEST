package tech.kbtg.springboothomework;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.kbtg.springboothomework.Entity.Employee;
import tech.kbtg.springboothomework.Service.EmployeeService;

@SpringBootApplication
public class SpringbootHomeworkApplication {

	public static void main(String[] args) {SpringApplication.run(SpringbootHomeworkApplication.class, args);}

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeService employeeService){
		return runer -> {
			initData(employeeService);
		};
	}

	private void initData(EmployeeService employeeService){
		Employee emp1 = new Employee(1, "Jankson", "Wang", "JJ", 100000, "current", "China", "Pop star");
		Employee emp2 = new Employee(2, "John", "Smith", "Jonny", 80000, "current", "USA", "Producer");
		Employee emp3 = new Employee(3, "Jenny", "Tian", "JT", 120000, "current", "Thailand", "Boss");

		employeeService.save(emp1);
		employeeService.save(emp2);
		employeeService.save(emp3);
	}
}
