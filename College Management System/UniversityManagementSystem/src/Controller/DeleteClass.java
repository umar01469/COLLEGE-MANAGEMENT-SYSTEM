package Controller;

import java.util.Scanner;

import Model.Class;
import Model.Database;
import Model.Operation;

public class DeleteClass implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		System.out.println("Enter Class ID (-1 to show all classes):");
		int ID = scanner.nextInt();
		while (ID<0) {
			new ReadClasses().oper(database, scanner, id);
			System.out.println("Enter Class ID (-1 to show all classes):");
			ID = scanner.nextInt();
		}
		
		Class c = new Class(ID);
		c.delete(database);
	}

}
