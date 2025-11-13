package Controller;

import java.util.Scanner;

import Model.Class;
import Model.Database;
import Model.Operation;
import Model.Student;

public class UpdateStudent implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int id) {
		
		System.out.println("Enter ID (-1 to show all students):");
		int ID = scanner.nextInt();
		while (ID<0) {
			new ReadStudents().oper(database, scanner, id);
			System.out.println("Enter ID (-1 to show all students):");
			ID = scanner.nextInt();
		}
		
		Student s = new Student(ID, database);
		
		System.out.println("Enter First Name (-1 to keep "+s.getFirstName()+"):");
		String firstName = scanner.next();
		if (!firstName.equals("-1")) s.setFirstName(firstName);
		
		System.out.println("Enter Last Name (-1 to keep "+s.getLastName()+"):");
		String lastName = scanner.next();
		if (!lastName.equals("-1")) s.setLastName(lastName);
		
		System.out.println("Enter Email (-1 to keep "+s.getEmail()+"):");
		String email = scanner.next();
		if (!email.equals("-1")) s.setEmail(email);
		
		System.out.println("Enter Phone Number (-1 to keep "+s.getPhoneNumber()+"):");
		String phoneNumber = scanner.next();
		if (!phoneNumber.equals("-1")) s.setPhoneNumber(phoneNumber);
		
		System.out.println("Enter Birth Date (-1 to keep "+s.getBirthDate()+"):");
		String birthDate = scanner.next();
		if (!birthDate.equals("-1")) s.setBirthDate(birthDate);
		
		System.out.println("Enter Class ID (-1 to keep "+s.getCurrentClass().getName()+")\n"
				+ "(-2 to show all classes):");
		int classID = scanner.nextInt();
		if (classID!=-1) {
			while (classID==-2) {
				new ReadClasses().oper(database, scanner, id);
				System.out.println("Enter Class ID (-2 to show all classes):");
				classID = scanner.nextInt();
			}
			s.setClass(new Class(classID, database));
		}
		
		System.out.println("Enter Password (-1 to keep old one):");
		String password = scanner.next();
		if (!password.equals("-1")) {
			System.out.println("Confirm Password:");
			String confirmPassword = scanner.next();
			while (!password.equals(confirmPassword)) {
				System.out.println("Enter Password:");
				password = scanner.next();
				System.out.println("Confirm Password:");
				confirmPassword = scanner.next();
			}
			s.setPassword(password);
		}
		
		s.update(database);
		
	}

}
