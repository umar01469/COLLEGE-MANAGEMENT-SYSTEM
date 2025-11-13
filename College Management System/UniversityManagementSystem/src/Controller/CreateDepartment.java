package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Department;
import Model.Operation;

public class CreateDepartment implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {

		scanner.nextLine();
		System.out.println("Enter Department Name:");
		String name = scanner.nextLine();
		
		int ID = 0;
		ArrayList<Department> departments = new ReadDepartments().getAllDepartments(database);
		if (departments.size()!=0) {
			ID = departments.get(departments.size()-1).getID()+1;
		}
		
		Department department = new Department();
		department.setID(ID);
		department.setName(name);
		department.create(database);

	}

}
