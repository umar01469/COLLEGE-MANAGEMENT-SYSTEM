package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Class;
import Model.Database;
import Model.Operation;

public class ReadClasses implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {

		for (Class c : getAllClasses(database)) {
			c.print();
		}
		
	}
	
	public ArrayList<Model.Class> getAllClasses(Database database) {
		ArrayList<Model.Class> classes = new ArrayList<>();
		String select = "SELECT * FROM `classes`;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				Class c = new Class();
				c.setID(rs.getInt("ID"));
				c.setName(rs.getString("Name"));
				classes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;
	}

}
