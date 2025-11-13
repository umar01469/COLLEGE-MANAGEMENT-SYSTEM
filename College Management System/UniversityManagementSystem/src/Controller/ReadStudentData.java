package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Student;

public class ReadStudentData implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		Student student = new Student(ID, database);
		student.print();
	}

}
