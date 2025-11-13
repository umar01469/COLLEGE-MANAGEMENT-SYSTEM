package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {
	
	private int ID;
	private String name;
	private Class c;
	private String description;
	private int limit;
	private ArrayList<Student> students;
	private ArrayList<Integer> studentsIDs;
	private Employee prof;
	private Department dept;
	
	public Course() {
		students = new ArrayList<>();
		studentsIDs = new ArrayList<>();
	}
	
	public Course(int ID, Database database) {
		students = new ArrayList<>();
		studentsIDs = new ArrayList<>();
		setID(ID);
		String select1 = "SELECT * FROM `courses` WHERE `ID` = "+ID+" ;";
		String select2 = "SELECT * FROM `course "+ID+"`;";
		try {
			ResultSet rs1 = database.getStatement().executeQuery(select1);
			rs1.next();
			setName(rs1.getString("Name"));
			int classID = rs1.getInt("Class");
			setDescription(rs1.getString("Description"));
			setLimit(rs1.getInt("Lim"));
			int profID = rs1.getInt("Prof");
			int deptID = rs1.getInt("Department");
			
			setClass(new Class(classID, database));
			setProf(new Employee(profID, database));
			setDepartment(new Department(deptID, database));
			
			ResultSet rs2 = database.getStatement().executeQuery(select2);
			while (rs2.next()) {
				studentsIDs.add(rs2.getInt("Student"));
			}
			for (Integer i : studentsIDs) {
				students.add(new Student(i, database));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Course(int ID) {
		this.ID = ID;
		students = new ArrayList<>();
		studentsIDs = new ArrayList<>();
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Class getCurrentClass() {
		return c;
	}
	
	public void setClass(Class c) {
		this.c = c;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public ArrayList<Integer> getStudentsIDs() {
		return studentsIDs;
	}
	
	public Employee getProf() {
		return prof;
	}
	
	public void setProf(Employee prof) {
		this.prof = prof;
	}
	
	public Department getDepartment() {
		return dept;
	}
	
	public void setDepartment(Department dept) {
		this.dept = dept;
	}
	
	public void print() {
		System.out.println("ID:\t\t"+getID());
		System.out.println("Name:\t\t"+getName());
		System.out.println("Class:\t\t"+getCurrentClass().getName());
		System.out.println("Description:\t"+getDescription());
		System.out.println("Limit:\t\t"+getLimit());
		System.out.println("Prof:\t\tDr. "+getProf().getFirstName()+" "+getProf().getLastName());
		System.out.println("Department:\t"+getDepartment().getName());
		System.out.println("____________________________________\n");
	}
	
	public void create(Database database) {
		String insert = "INSERT INTO `courses`(`ID`, `Name`, `Class`, `Description`,"
				+ " `Lim`, `Prof`, `Department`) VALUES ('"+getID()+"','"+getName()+
				"','"+getCurrentClass().getID()+"','"+getDescription()+"','"+
				getLimit()+"','"+getProf().getID()+"','"+getDepartment().getID()
				+"');";
		String create = "CREATE TABLE `Course "+getID()+"` (Student int);";
		try {
			database.getStatement().execute(insert);
			database.getStatement().execute(create);
			System.out.println("Course created successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Database database) {
		String update = "UPDATE `courses` SET `Name`='"+getName()+"',"
				+ "`Class`='"+getCurrentClass().getID()+"',"
						+ "`Description`='"+getDescription()+"',`Lim`='"+getLimit()+"',"
								+ "`Prof`='"+getProf().getID()+"',"
										+ "`Department`='"+getDepartment().getID()+"' "
												+ "WHERE `ID` = "+getID()+" ;";
		try {
			database.getStatement().execute(update);
			System.out.println("Course updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Database database) {
		String delete = "DELETE FROM `courses` WHERE `ID` = "+getID()+" ;";
		String drop = "DROP TABLE `Course "+getID()+"`;";
		try {
			database.getStatement().execute(delete);
			database.getStatement().execute(drop);
			System.out.println("Course deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
