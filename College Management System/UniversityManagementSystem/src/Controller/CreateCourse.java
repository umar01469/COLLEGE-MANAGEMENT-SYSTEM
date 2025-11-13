package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Class;
import Model.Course;
import Model.Database;
import Model.Department;
import Model.Employee;
import Model.Operation;

public class CreateCourse implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		
		Course c = new Course();
		
		int ID = 0;
		ArrayList<Course> courses = new ReadCourses().getAllCourses(database);
		if (courses.size()!=0) {
			ID = courses.get(courses.size()-1).getID()+1;
		}
		c.setID(ID);
		
		scanner.nextLine();
		System.out.println("Enter Course Name:");
		c.setName(scanner.nextLine());
		
		System.out.println("Enter Class ID (-1 to show all classes):");
		int classID = scanner.nextInt();
		while (classID<0) {
			new ReadClasses().oper(database, scanner, id);
			System.out.println("Enter Class ID (-1 to show all classes):");
			classID = scanner.nextInt();
		}
		c.setClass(new Class(classID, database));
		
		scanner.nextLine();
		System.out.println("Enter Course Description:");
		c.setDescription(scanner.nextLine());
		
		System.out.println("Enter Course Limit (int):");
		c.setLimit(scanner.nextInt());
		
		System.out.println("Enter Prof ID (-1 to show all employees):");
		int employeeID = scanner.nextInt();
		while (employeeID<0) {
			new ReadEmployees().oper(database, scanner, id);
			System.out.println("Enter Prof ID (-1 to show all employees):");
			employeeID = scanner.nextInt();
		}
		c.setProf(new Employee(employeeID, database));
		
		System.out.println("Enter Department ID (-1 to show all departments):");
		int deptID = scanner.nextInt();
		while (deptID<0) {
			new ReadDepartments().oper(database, scanner, id);
			System.out.println("Enter Department ID (-1 to show all departments):");
			deptID = scanner.nextInt();
		}
		c.setDepartment(new Department(deptID, database));
		
		c.create(database);
		
	}

}
