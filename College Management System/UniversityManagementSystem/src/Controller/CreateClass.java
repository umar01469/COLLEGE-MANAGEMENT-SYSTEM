package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Database;
import Model.Operation;

public class CreateClass implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		scanner.nextLine();
		System.out.println("Enter Class Name:");
		String name = scanner.nextLine();
		
		ArrayList<Model.Class> classes = new ReadClasses().getAllClasses(database);
		int ID = 0;
		if (classes.size()!=0) {
			ID = classes.get(classes.size()-1).getID()+1;
		}
		
		Model.Class c = new Model.Class();
		c.setID(ID);
		c.setName(name);
		c.create(database);
		
	}

}
