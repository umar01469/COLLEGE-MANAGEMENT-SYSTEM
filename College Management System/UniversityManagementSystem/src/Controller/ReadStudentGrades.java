package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Class;
import Model.Course;
import Model.Database;
import Model.Grade;
import Model.Operation;
import Model.Student;

public class ReadStudentGrades implements Operation {

	@Override
	public void oper(Database database, Scanner scanner, int ID) {
		String select = "SELECT * FROM `grades` WHERE `Student` = "+ID+" ;";
		ArrayList<Grade> grades = new ArrayList<>();
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			ArrayList<Integer> coursesIDs = new ArrayList<>();
			ArrayList<Integer> classesIDs = new ArrayList<>();
			while (rs.next()) {
				Grade g = new Grade();
				g.setID(rs.getInt("ID"));
				coursesIDs.add(rs.getInt("Course"));
				classesIDs.add(rs.getInt("Class"));
				g.setGrade(rs.getDouble("Grade"));
				g.setMax(rs.getDouble("Max"));
				grades.add(g);
			}
			
			Student student = new Student(ID, database);
			for (int i=0;i<grades.size();i++) {
				grades.get(i).setCourse(new Course(coursesIDs.get(i), database));
				grades.get(i).setClass(new Class(classesIDs.get(i), database));
				grades.get(i).setStudent(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("ID\tCourse\t\tClass\tGrade\tMax");
		for (Grade g : grades) {
			System.out.print(g.getID()+"\t");
			System.out.print(g.getCourse().getName()+"\t");
			if (g.getCourse().getName().length()<8) System.out.print("\t");
			System.out.print(g.getCurrentClass().getName()+"\t");
			System.out.print(g.getGrade()+"\t");
			System.out.print(g.getMax()+"\n");
		}
	}

}
