package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.dao.TeacherDao;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.entity.Teacher;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月3日 下午10:29:41 
* @parameter   
* @return 
*/
public class TeacherDaoTest 
{
	TeacherDao tDao = new TeacherDao();
	
	//测试添加教师
		@Test
		public void testAddTeacher()
		{
		//先写个班级
			Classes c=new Classes();
			c.setClassName("阿童木班");
			c.setClassNumber("中班");
			Teacher t = new Teacher();
			   t.setClasses(c);
			   t.setDegree("硕士");
			   t.setEvaluate("技能一般");
			   t.setGraduateSchool("南京大学");
			   t.setPower(1);
			   t.setReward("自我安慰奖");
			   t.setRewardShow("/Edward/2.jpg");
			   t.setSpecialty("机电");
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date teacherBirthday;
				try {
					teacherBirthday = sdf.parse("1987-12-15");
					t.setTeacherBirthday(teacherBirthday);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new RuntimeException("类型转换错误");
				}
			   t.setTeacherHeader("/Edward/222.jpg");
			   t.setTeacherName("爱德华");
			   t.setTeacherNumber("aolei");
			   t.setTeacherPwd("123456");
			   t.setTeacherSex("男");
			   t.setTeacherTelePhone("13663693907");	
			   tDao.addTeacher(t);
		}
		//删除教师
		@Test
		public void testDelete ()
		{
		 String[] idString={"aolei"};
		 tDao.deleteParent(idString);
		 
			
		}
		
		//测试更新教师资料
		@Test
		public void testUpdate()
		{
			//得到班级
			Classes c=new Classes();
			c.setClassName("阿童木班");
			c.setClassNumber("中班");
			//教师除了主键不改，其他都改了。
			Teacher t = new Teacher();
			   t.setClasses(c);
			   t.setDegree("高中");
			   t.setEvaluate("技能一般");
			   t.setGraduateSchool("南京大学");
			   t.setPower(1);
			   t.setReward("自我安慰奖");
			   t.setRewardShow("/Edward/2.jpg");
			   t.setSpecialty("机电");
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date teacherBirthday;
				try {
					teacherBirthday = sdf.parse("1856-12-15");
					t.setTeacherBirthday(teacherBirthday);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new RuntimeException("类型转换错误");
				}
			   t.setTeacherHeader("/Edward/222.jpg");
			   t.setTeacherName("爱德华");
			   t.setTeacherNumber("aolei");
			   t.setTeacherPwd("123456");
			   t.setTeacherSex("男");
			   t.setTeacherTelePhone("13663693908");	
			   tDao.updateTeacher(t);
		}
		//测试返回教师信息
		@Test
		public void  testGetTeacher ()
		{
			Teacher teacher = tDao.getTeacherByTeacherNum("xiaosan");
			System.out.println(teacher);
				
		}
		
		
			
	//返回所有教师
			@Test
			public void testGetAllTeacher()
			{
				List<Teacher> list = tDao.getAllTeacher();
				for (Teacher teacher : list)
				{
					System.out.println(teacher);
				}
				
				
			}
			//测试模糊查询
			@Test
			public void testGetTeacherByFuzzy()
			{
				List<Teacher> list = tDao.getTeacherByFuzzy("井");
				for (Teacher teacher : list)
				{
					System.out.println(teacher);
				}
				
				
			}
			
			
			//测试分页查询
			@Test
			public void testPagedTeac()
			{
				List<Teacher> list = tDao.getPageTeacher(1, 2);
				for (Teacher teacher : list)
				{
					System.out.println(teacher);
				}
				
				
			}
			
			//统计总人数
			@Test
			public void getTotal()
			{
				int total =tDao.getTeacherNumber();
				System.out.println(total);
				
			}


}
