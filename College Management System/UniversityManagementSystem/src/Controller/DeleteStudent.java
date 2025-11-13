package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Student;

public class DeleteStudent implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {

		System.out.println("Enter Student ID (-1 to show all students)");
		int ID = scanner.nextInt();
		while (ID<0) {
			new ReadStudents().oper(database, scanner, id);
			System.out.println("Enter Student ID (-1 to show all students)");
			ID = scanner.nextInt();
		}
		
		Student s = new Student(ID);
		s.delete(database);
		
	}

}
