package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class UpdateCourseGrade implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		new ReadCourseGrades().oper(database, scanner, ID);
		
		System.out.println("Enter grade ID:");
		int gradeID = scanner.nextInt();
		
		System.out.println("Enter new grade:");
		double grade = scanner.nextDouble();
		
		String update = "UPDATE `grades` SET `Grade`='"+grade+"' "
				+ "WHERE `ID` = "+gradeID+" ;";
		try {
			database.getStatement().execute(update);
			System.out.println("Grade updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
