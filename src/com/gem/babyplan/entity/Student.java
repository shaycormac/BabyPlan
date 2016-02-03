package com.gem.babyplan.entity;

import java.util.Date;

public class Student {
	private String studentNumber;//学生号，主键
	
	private Classes classes;//班级号，外键
	
	private String studentName;//姓名
	private String studentSex;//性别
	private Date studentBirthday;//生日
	private String studentPhotoURL;//照片URL
	
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentId) {
		this.studentNumber = studentId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public Date getStudentBirthday() {
		return studentBirthday;
	}
	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
	public String getStudentPhotoURL() {
		return studentPhotoURL;
	}
	public void setStudentPhotoURL(String studentPhotoURL) {
		this.studentPhotoURL = studentPhotoURL;
	}
	
	@Override
	public String toString() {
		return "Student [studentNumber=" + studentNumber + ", classes=" + classes + ", studentName=" + studentName
				+ ", studentSex=" + studentSex + ", studentBirthday=" + studentBirthday + ", studentPhotoURL="
				+ studentPhotoURL + "]";
	}	
}
