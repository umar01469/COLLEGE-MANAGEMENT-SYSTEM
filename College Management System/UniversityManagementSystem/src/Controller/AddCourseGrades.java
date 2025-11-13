package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class AddCourseGrades implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		System.out.println("Enter Course ID (-1 to show all your courses):");
		int courseID = scanner.nextInt();
		while (courseID<0) {
			new ReadEmployeeCourses().oper(database, scanner, ID);
			System.out.println("Enter Course ID (-1 to show all your courses):");
			courseID = scanner.nextInt();
		}
		
		Course course = new Course(courseID, database);
		System.out.println("Enter max (double):");
		double max = scanner.nextDouble();
		
		System.out.println("Type the grade for each name of these students (double):");
		ArrayList<Student> students = course.getStudents();
		for (int i=0;i<students.size();i++) {
			System.out.println(students.get(i).getFirstName()+" "+students.get(i).getLastName());
			double grade = scanner.nextDouble();
			String insert = "INSERT INTO `grades`(`Course`, `Class`, `Student`,"
					+ " `Grade`, `Max`) VALUES ('"+course.getID()+"','"+
					course.getCurrentClass().getID()+"','"+students.get(i).getID()
					+"','"+grade+"','"+max+"');";
			try {
				database.getStatement().execute(insert);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Grades added successfully");
		
	}
	
}
