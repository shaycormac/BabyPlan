package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Test;

import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Student;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月2日 下午6:37:04 
* @parameter   
* @return 
*/
public class StudentDaoTest 
{
	StudentDao sDao = new StudentDao();
	@Test
	public void testAdd()
	{
		Student s = new Student();
		Classes c = new Classes();
		c.setClassNumber("小班");;
		c.setClassName("葫芦娃班");
		s.setClasses(c);
		s.setStudentNumber("01070224");
		s.setStudentName("张飞");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date studentBirthday;
		try {
			studentBirthday = sdf.parse("1525-08-06");
			s.setStudentBirthday(studentBirthday);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("类型转换错误");
		}
		s.setStudentPhotoURL("/server/53.jpg");
		s.setStudentSex("男");
		sDao.addStudent(s);
		
	}
	
	@Test
	public void testDelete ()
	{
		String [] ids = {"01070222","01070223"};
		sDao.deleteStudent(ids);
		
		
	}
	@Test
	public void testUpdate()
	{
		Student s = new Student();
		Classes c = new Classes();
		c.setClassNumber("中班");;
		c.setClassName("阿童木班");
		s.setStudentNumber("01070221");
		s.setClasses(c);
		s.setStudentName("中华");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date studentBirthday;
		try {
			studentBirthday = sdf.parse("1818-03-28");
			s.setStudentBirthday(studentBirthday);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("类型转换错误");
		}
		s.setStudentPhotoURL("/server/2.png");
		s.setStudentSex("兽");
		sDao.updateStudent(s);	
		
	}
	/*//测试查找学生对象
	@Test
	public void testFindStudent ()
	{
		String name="沫华";
		String classname ="班";
		List<Student> list = sDao.getStudentByClassName(classname, name);
		System.out.println(list);	
	}*/
	//查找学生对象
	@Test
	public void testGetStudent ()
	{
		Student s = sDao.getStudentByNumber("01070220");
		System.out.println(s);
		
	}
	//测试得到所有学生对象
	@Test
	public void testGetAll()
	{
		
		List<Student> list =sDao.getAllStudent();
		for (Student student : list) 
		{
			System.out.println(student);
		}
		
	}
	//测试模糊查询
	@Test
	public void testGetFuzzyName()
	{
		
		List<Student> list =sDao.getStudentByFuzzy("华");
		for (Student student : list) 
		{
			System.out.println(student);
		}
		
	}
	//测试分页查询
	@Test
	public void testGetPage()
	{
		List<Student> list =sDao.getPageStudent(2, 5);
		for (Student student : list) 
		{
			System.out.println(student);
		}	
	}
	
	//统计学生总数
	@Test
	public void testCount()
	{
		int total =sDao.getStudentNumber();
		System.out.println(total);
		
		
	}

}
