package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Employee;
import Model.Operation;

public class CreateEmployee implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		
		Employee e = new Employee();
		
		System.out.println("Enter First Name:");
		e.setFirstName(scanner.next());
		System.out.println("Enter Last Name:");
		e.setLastName(scanner.next());
		System.out.println("Enter Email:");
		e.setEmail(scanner.next());
		System.out.println("Enter Phone Number:");
		e.setPhoneNumber(scanner.next());
		System.out.println("Enter Birth Date:");
		e.setBirthDate(scanner.next());
		System.out.println("Enter Salary (double):");
		e.setSalary(scanner.nextDouble());
		System.out.println("Enter Department ID (-1 to show all departments):");
		int deptID = scanner.nextInt();
		while (deptID<0) {
			new ReadDepartments().oper(database, scanner, id);
			System.out.println("Enter Department ID (-1 to show all departments):");
			deptID = scanner.nextInt();
		}
		
		e.setDepartment(new Department(deptID, database));
		
		System.out.println("Enter Password:");
		String password = scanner.next();
		System.out.println("Confirm Password:");
		String confirmPassword = scanner.next();
		
		while (!confirmPassword.equals(password)) {
			System.out.println("Enter Password:");
			password = scanner.next();
			System.out.println("Confirm Password:");
			confirmPassword = scanner.next();
		}
		e.setPassword(password);
		
		ArrayList<Employee> employees = new ReadEmployees().getAllEmployees(database);
		int ID = 0;
		if (employees.size()!=0) {
			ID = employees.get(employees.size()-1).getID()+1;
		}
		e.setID(ID);
		e.create(database);
	}
	
	

}
