package Controller;

import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class ReadCourseStudents implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		System.out.println("Enter Course ID (-1 to show all my courses):");
		int courseID = scanner.nextInt();
		while (courseID<0) {
			new ReadEmployeeCourses().oper(database, scanner, ID);
			System.out.println("Enter Course ID (-1 to show all my courses):");
			courseID = scanner.nextInt();
		}
		
		Course course = new Course(courseID, database);
		System.out.println("ID\tName\t\tEmail\t\t\tPhone Number\tBirth Date\tClass");
		for (Student s : course.getStudents()) {
			System.out.print(s.getID()+"\t");
			System.out.print(s.getFirstName()+" "+s.getLastName()+"\t");
			System.out.print(s.getEmail()+"\t");
			System.out.print(s.getPhoneNumber()+"\t\t");
			System.out.print(s.getBirthDate()+"\t");
			System.out.print(s.getCurrentClass().getName()+"\n");
		}
	}

}
