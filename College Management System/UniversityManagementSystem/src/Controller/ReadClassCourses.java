package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Operation;
import Model.Student;

public class ReadClassCourses implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		Student student = new Student(ID, database);
		ArrayList<Course> courses = new ReadCourses().getAllCourses(database);
		System.out.println("ID\tName\t\tDescription\tProf\t\t\tDepartment");
		for (Course c : courses) {
			if (c.getCurrentClass().getID()==student.getCurrentClass().getID()) {
				System.out.print(c.getID()+"\t");
				System.out.print(c.getName()+"\t");
				if (c.getName().length()<8) System.out.print("\t");
				System.out.print(c.getDescription()+"\t");
				if (c.getDescription().length()<8) System.out.print("\t");
				System.out.print("Dr. "+c.getProf().getFirstName()+" "+
				c.getProf().getLastName()+"\t");
				System.out.print(c.getDepartment().getName()+"\n");
			}
		}
	}

}
