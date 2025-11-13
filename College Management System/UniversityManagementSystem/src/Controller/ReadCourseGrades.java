package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Course;
import Model.Database;
import Model.Grade;
import Model.Operation;
import Model.Student;

public class ReadCourseGrades implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		System.out.println("Enter Course ID (-1 to show all your courses):");
		int courseID = scanner.nextInt();
		while (courseID<0) {
			new ReadEmployeeCourses().oper(database, scanner, ID);
			System.out.println("Enter Course ID (-1 to show all your courses):");
			courseID = scanner.nextInt();
		}
		
		Course course = new Course(courseID, database);
		String select = "SELECT * FROM `grades` WHERE `Course` = "+course.getID()+" ;";
		ArrayList<Grade> grades = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			ArrayList<Integer> studentsIDs = new ArrayList<>();
			while (rs.next()) {
				Grade g = new Grade();
				g.setID(rs.getInt("ID"));
				g.setCourse(course);
				g.setClass(course.getCurrentClass());
				studentsIDs.add(rs.getInt("Student"));
				g.setGrade(rs.getDouble("Grade"));
				g.setMax(rs.getDouble("Max"));
				grades.add(g);
			}
			
			for (int i=0;i<grades.size();i++) {
				grades.get(i).setStudent(new Student(studentsIDs.get(i), database));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (grades.size()!=0) {
			System.out.println("ID\tStudent\t\tClass\tGrade/"+grades.get(grades.size()-1).getMax());
		}
		for (Grade g : grades) {
			System.out.print(g.getID()+"\t");
			System.out.print(g.getStudent().getFirstName()+" "+
						g.getStudent().getLastName()+"\t");
			System.out.print(g.getCurrentClass().getName()+"\t");
			System.out.print(g.getGrade()+"\n");
		}
	}

}
