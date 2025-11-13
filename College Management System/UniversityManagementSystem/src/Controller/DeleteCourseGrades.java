package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class DeleteCourseGrades implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		System.out.println("Enter Course ID (-1 to show all your courses):");
		int courseID = scanner.nextInt();
		while (courseID<0) {
			new ReadEmployeeCourses().oper(database, scanner, ID);
			System.out.println("Enter Course ID (-1 to show all your courses):");
			courseID = scanner.nextInt();
		}
		
		String delete = "DELETE FROM `grades` WHERE `Course` = "+courseID+" ;";
		try {
			database.getStatement().execute(delete);
			System.out.println("Course Grades deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
