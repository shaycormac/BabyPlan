package com.gem.babyplan.entity;

public class Course_Class {
	private int courseClassId;//科目表id
	private Classes classes;//班级号，联合主键
	private Course course;//课程id，联合主键
	private String weekDay;//星期几
	private String dayNumber;//第几节
	
	public int getCourseClassId() {
		return courseClassId;
	}
	public void setCourseClassId(int courseClassId) {
		this.courseClassId = courseClassId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getDayNumber() {
		return dayNumber;
	}
	public void setDayNumber(String dayNumber) {
		this.dayNumber = dayNumber;
	}
	
	@Override
	public String toString() {
		return "Course_Class [courseClassId=" + courseClassId + ", classes=" + classes + ", course=" + course
				+ ", weekDay=" + weekDay + ", dayNumber=" + dayNumber + "]";
	}	
}
