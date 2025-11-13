package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Employee;
import Model.Operation;

public class UpdateEmployee implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		
		System.out.println("Enter User ID (-1 to show all employees):");
		int userID = scanner.nextInt();
		while (userID<0) {
			new ReadEmployees().oper(database, scanner, id);
			System.out.println("Enter User ID (-1 to show all employees):");
			userID = scanner.nextInt();
		}
		
		Employee employee = new Employee(userID, database);
		
		System.out.println("Enter First Name (-1 to keep "+employee.getFirstName()+"):");
		String firstName = scanner.next();
		if (!firstName.equals("-1")) employee.setFirstName(firstName);
		
		System.out.println("Enter Last Name (-1 to keep "+employee.getLastName()+"):");
		String lastName = scanner.next();
		if (!lastName.equals("-1")) employee.setLastName(lastName);
		
		System.out.println("Enter Email (-1 to keep "+employee.getEmail()+"):");
		String email = scanner.next();
		if (!email.equals("-1")) employee.setEmail(email);
		
		System.out.println("Enter Phone Number (-1 to keep "+employee.getPhoneNumber()+"):");
		String phoneNumber = scanner.next();
		if (!phoneNumber.equals("-1")) employee.setPhoneNumber(phoneNumber);
		
		System.out.println("Enter Birth Date (-1 to keep "+employee.getBirthDate()+"):");
		String birthDate = scanner.next();
		if (!birthDate.equals("-1")) employee.setBirthDate(birthDate);
		
		System.out.println("Enter Salary (-1 to keep "+employee.getSalary()+"$ ):");
		double salary = scanner.nextDouble();
		if (salary!=-1) employee.setSalary(salary);
		
		System.out.println("Enter Password (-1 to keep old password):");
		String password = scanner.next();
		if (!password.equals("-1")) {
			System.out.println("Confirm Password:");
			String confirmPassword = scanner.next();
			while (!password.equals(confirmPassword)) {
				System.out.println("Enter Password:");
				password = scanner.next();
				System.out.println("Confirm Password:");
				confirmPassword = scanner.next();
			}
			employee.setPassword(password);
		}
		
		System.out.println("Enter Department ID (-1 to keep "+employee.getDepartment().getName()+" )\n"
				+ "(-2 to show all departments):");
		int deptID = scanner.nextInt();
		if (deptID!=-1) {
			while (deptID==-2) {
				new ReadDepartments().oper(database, scanner, id);
				deptID = scanner.nextInt();
			}
			employee.setDepartment(new Department(deptID, database));
		}
		
		employee.update(database);
	}

}
