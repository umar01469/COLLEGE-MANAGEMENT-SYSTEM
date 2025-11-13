package Controller;

import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class RegisterCourse implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		Student student = new Student(ID, database);
		System.out.println("Enter Course ID (-1 to show all courses):");
		int courseID = scanner.nextInt();
		while (courseID<0) {
			new ReadClassCourses().oper(database, scanner, ID);
			System.out.println("Enter Course ID (-1 to show all courses):");
			courseID = scanner.nextInt();
		}
		
		Course selectedCourse = new Course(courseID, database);
		if (selectedCourse.getStudents().size()>=selectedCourse.getLimit()) {
			System.out.println("This course is full.\nTry again later");
		} else if (selectedCourse.getStudentsIDs().contains(student.getID())) {
			System.out.println("You have already registered this course before");
		} else {
			student.registerCourse(database, courseID);
		}
	}

}
