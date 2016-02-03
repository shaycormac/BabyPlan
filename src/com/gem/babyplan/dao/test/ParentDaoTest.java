package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.StudentDao;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月3日 上午12:34:38 
* @parameter   
* @return 
*/
public class ParentDaoTest 
{
	ParentDao pDao = new ParentDao();
	
	//测试添加家长
	@Test
	public void testAddParent()
	{
	//先写个学生，亲娘来
		//从数据库中找学生
		StudentDao sDao = new StudentDao();
		Student s =sDao.getStudentByNumber("01070225");
		//添加一个新的家长，醉了
		Parent p = new Parent();
		p.setAddress("江苏省徐州市");
		p.setBackgroundPhoto("/serblet/6.jpg");
		p.setParentHeader("/serblet/6.jpg");
		p.setParentName("方方");
		p.setParentNickName("ezioauditore");
		p.setParentPwd("123456");
		p.setParentSex("男");
		p.setParentTelePhone("15334943341");
		p.setStudent(s);
		pDao.addParent(p);
		
	}
	//删除家长
	@Test
	public void testDelete ()
	{
	 int [] ids ={2,3};
	 pDao.deleteParent(ids);
		
	}
	
	//测试更新家长资料
	@Test
	public void testUpdate()
	{
		//先得到学生
		//从数据库中找学生
				StudentDao sDao = new StudentDao();
				Student s =sDao.getStudentByNumber("01070220");
		//家长除了id不改，其他都改了。
				Parent p = new Parent();
				p.setAddress("山西省朔州市");
				p.setBackgroundPhoto("/serblxx/3.jpg");
				p.setParentHeader("/serblet/8.jpg");
				p.setParentName("圣母");
				p.setParentNickName("edward");
				p.setParentPwd("56789");
				p.setParentSex("妖");
				p.setParentTelePhone("18634943369");
				p.setStudent(s);
				p.setParentId(1);
				pDao.updateParent(p);			
	}
	//测试返回家长信息
	@Test
	public void  testGetParent ()
	{
		Parent p =pDao.getParentByTelephone("18634943369");
		System.out.println(p);
			
	}
	
	//测试返回家长信息,根据id
		@Test
		public void  testGetParentById ()
		{
			Parent p =pDao.getParentByParentId(1);
			System.out.println(p);
				
		}
		
//返回所有家长
		@Test
		public void testGetAllParent()
		{
			List<Parent> list = pDao.getAllParent();
			for (Parent parent : list)
			{
				System.out.println(parent);
				
			}
			
			
		}
		//测试模糊查询
		@Test
		public void testGetParentByFuzzy()
		{
			List<Parent> list = pDao.getParentByFuzzy("方");
			for (Parent parent : list)
			{
				System.out.println(parent);
				
			}
			
			
		}
		
		
		//测试分页查询
		@Test
		public void testPagedPare()
		{
			List<Parent> list = pDao.getPageParent(1, 2);
			for (Parent parent : list)
			{
				System.out.println(parent);
				
			}
			
			
		}
		
		//统计总人数
		@Test
		public void getTotal()
		{
			int total =pDao.getParentNumber();
			System.out.println(total);
			
		}

}
