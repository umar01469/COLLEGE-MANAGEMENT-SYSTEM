package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Employee;
import Model.Student;

public class Login {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Database database = new Database();
		System.out.println("Welcome to University Management System");
		System.out.println("1. Staff");
		System.out.println("2. Student");
		int selected = scanner.nextInt();
		
		System.out.println("Enter email:");
		String email = scanner.next();
		System.out.println("Enter password:");
		String password = scanner.next();
		
		boolean loggedIn = false;
		
		if (selected==1) {
			String select = "SELECT `ID`, `Email`, `Password` "
					+ "FROM `employees` WHERE `Email` = '"+email+"' ;";
			try {
				ResultSet rs = database.getStatement().executeQuery(select);
				while (rs.next()) {
					if (password.equals(rs.getString("Password"))) {
						Employee e = new Employee(rs.getInt("ID"), database);
						loggedIn = true;
						e.showList(database, scanner);
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String select = "SELECT `ID`, `Email`, `Password` "
					+ "FROM `students` WHERE `Email` = '"+email+"' ;";
			try {
				ResultSet rs = database.getStatement().executeQuery(select);
				while (rs.next()) {
					if (password.equals(rs.getString("Password"))) {
						Student e = new Student(rs.getInt("ID"), database);
						loggedIn = true;
						e.showList(database, scanner);
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (!loggedIn) {
			System.out.println("Wrong email or password.\nTry agin later");
		}
	}

}
