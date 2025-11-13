package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class DeleteSpecCourseGrade implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		new ReadCourseGrades().oper(database, scanner, ID);
		
		System.out.println("Enter grade ID:");
		int gradeID = scanner.nextInt();
		
		String delete = "DELETE FROM `grades` WHERE `ID` = "+gradeID+" ;";
		try {
			database.getStatement().execute(delete);
			System.out.println("Grade deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
