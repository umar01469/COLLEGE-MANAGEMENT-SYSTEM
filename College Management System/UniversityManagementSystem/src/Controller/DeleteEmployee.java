package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class DeleteEmployee implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {

		System.out.println("Enter Employee ID (-1 to show all employees):");
		int ID = scanner.nextInt();
		while (ID<0) {
			new ReadEmployees().oper(database, scanner, id);
			System.out.println("Enter Employee ID (-1 to show all employees):");
			ID = scanner.nextInt();
		}
		
		String delete = "DELETE FROM `employees` WHERE `ID` = "+ID+" ;";
		try {
			database.getStatement().execute(delete);
			System.out.println("Employee deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
