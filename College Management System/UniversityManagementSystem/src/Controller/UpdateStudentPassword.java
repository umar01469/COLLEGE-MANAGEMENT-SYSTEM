package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.Student;

public class UpdateStudentPassword implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		Student student = new Student(ID, database);
		System.out.println("Enter old password:");
		String oldPassword = scanner.next();
		if (!student.getPassword().equals(oldPassword)) {
			System.out.println("Wrong Password.\nTry again later");
		} else {
			System.out.println("Enter new password:");
			String newPassword = scanner.next();
			System.out.println("Confirm password:");
			String confirmPassword = scanner.next();
			while (!newPassword.equals(confirmPassword)) {
				System.out.println("Enter new password:");
				newPassword = scanner.next();
				System.out.println("Confirm password:");
				confirmPassword = scanner.next();
			}
			String update = "UPDATE `students` SET `Password`='"+newPassword+"' "
					+ "WHERE `ID` = "+student.getID()+" ;";
			try {
				database.getStatement().execute(update);
				System.out.println("Password updated successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
