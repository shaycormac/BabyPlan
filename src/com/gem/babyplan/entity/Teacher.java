package com.gem.babyplan.entity;

import java.util.Date;

public class Teacher {
	private String teacherNumber;//老师号，主键
	
	private Classes classes;//班级号，外键
	
	private String teacherName;//姓名
	private String teacherHeader;//头像URL
	private String teacherPwd;//密码
	private int power;//权限：0园长；1老师
	private String teacherSex;//性别
	private Date teacherBirthday;//生日
	private String teacherTelePhone;//联系方式
	private String graduateSchool;//毕业院校
	private String degree;//学历
	private String specialty;//专业
	private String evaluate;//专业技能评价
	private String reward;//在校奖励
	private String rewardShow;//证书展示
	
	public String getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(String teacherId) {
		this.teacherNumber = teacherId;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherHeader() {
		return teacherHeader;
	}
	public void setTeacherHeader(String teacherHeader) {
		this.teacherHeader = teacherHeader;
	}
	public String getTeacherPwd() {
		return teacherPwd;
	}
	public void setTeacherPwd(String teacherPwd) {
		this.teacherPwd = teacherPwd;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getTeacherSex() {
		return teacherSex;
	}
	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}
	public Date getTeacherBirthday() {
		return teacherBirthday;
	}
	public void setTeacherBirthday(Date teacherBirthday) {
		this.teacherBirthday = teacherBirthday;
	}
	public String getTeacherTelePhone() {
		return teacherTelePhone;
	}
	public void setTeacherTelePhone(String teacherTelePhone) {
		this.teacherTelePhone = teacherTelePhone;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getRewardShow() {
		return rewardShow;
	}
	public void setRewardShow(String rewardShow) {
		this.rewardShow = rewardShow;
	}
	
	@Override
	public String toString() {
		return "Teacher [teacherNumber=" + teacherNumber + ", classes=" + classes + ", teacherName=" + teacherName
				+ ", teacherHeader=" + teacherHeader + ", teacherPwd=" + teacherPwd + ", power=" + power
				+ ", teacherSex=" + teacherSex + ", teacherBirthday=" + teacherBirthday + ", teacherTelePhone="
				+ teacherTelePhone + ", graduateSchool=" + graduateSchool + ", degree=" + degree + ", specialty="
				+ specialty + ", evaluate=" + evaluate + ", reward=" + reward + ", rewardShow=" + rewardShow + "]";
	}

}
