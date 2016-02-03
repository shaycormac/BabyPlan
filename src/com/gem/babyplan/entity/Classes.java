package com.gem.babyplan.entity;

public class Classes {
	private String classNumber ;//班级号，主键
	private String className;//班级名
	
	public String getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(String classId) {
		this.classNumber = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return "Classes [classNumber=" + classNumber + ", className=" + className + "]";
	}
	
}
