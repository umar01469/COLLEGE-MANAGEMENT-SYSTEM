package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Class;
import Model.Database;
import Model.Operation;
import Model.Student;

public class ReadStudents implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		ArrayList<Student> students = getAllStudents(database);
		for (Student s : students) {
			s.print();
		}
	}
	
	public ArrayList<Student> getAllStudents(Database database) {
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Integer> classes = new ArrayList<>();
		String select = "SELECT * FROM `students`;";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			while (rs.next()) {
				Student s = new Student();
				s.setID(rs.getInt("ID"));
				s.setFirstName(rs.getString("FirstName"));
				s.setLastName(rs.getString("LastName"));
				s.setEmail(rs.getString("Email"));
				s.setPhoneNumber(rs.getString("PhoneNumber"));
				s.setBirthDate(rs.getString("BirthDate"));
				classes.add(rs.getInt("Class"));
				s.setPassword(rs.getString("Password"));
				students.add(s);
			}
			
			for (int i=0;i<students.size();i++) {
				students.get(i).setClass(new Class(classes.get(i), database));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

}
