package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.dao.StationDao;
import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.Station;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午9:36:15 
* @parameter   
* @return 
*/
public class StationDaoTest 
{
	StationDao sDao = new StationDao();
	
	//测试动画片集数
	@Test
	public void testAdd()
	{
		Cartoon c = new Cartoon();
		c.setCartoonId(1);
		c.setCartoonName("黑猫警长");
		c.setcThumbnail("/servlet/blackmao.jpg");
		Station s = new Station();
		s.setCatroon(c);
		s.setStationURL("/donghua/heimao/3.mp4");
		s.setWhichStation("第四集 班长殉职");
		sDao.addStation(s);
		
	}
	
	@Test
	public void testDelete ()
	{
		int ids [] ={8,9,10};
		sDao.deleteStation(ids);
		
	}
	@Test
	public void testUpdate()
	{
		Cartoon c = new Cartoon();
		c.setCartoonId(2);
		c.setCartoonName("海尔兄弟");
		c.setcThumbnail("/servlet/blackmao.jpg");
		Station s = new Station();
		s.setCatroon(c);
		s.setStationId(7);
		s.setStationURL("/donghua/haier/4.mp4");
		s.setWhichStation("第四集 欢迎来到");
		sDao.updateStation(s);
		
	}

	
	//查找哪一集对象
	@Test
	public void testStation ()
	{
		Station s =sDao.getStationById(1);
		System.out.println(s);
		
	}
	//测试得到所有集数对象，并按照cartoonId排序
	@Test
	public void testGetAll()
	{
		
		List<Station> list =sDao.getAllStation();
		for (Station station : list) {
			System.out.println(station);
			
		}
		
	}
	//测试得到指定哪一部动画片所有集数对象，并按照cartoonId排序
	@Test
	public void testGetAllOfName()
	{
		
		List<Station> list =sDao.getAllStationOfCartoon(2);
		for (Station station : list) {
			System.out.println(station);
			
		}
		
	}
	
	//测试分页查询
	@Test
	public void testGetPage()
	{
		List<Station> list =sDao.getPageStation(1, 2);
		for (Station station : list) {
			System.out.println(station);
			
		}
	}
	
	//统计所有集数总数
	@Test
	public void testCount()
	{
		int total =sDao.getStationNumber();
		System.out.println(total);
		
	}
	
	//统计所有集数总数.以指定的动画片id为主
		@Test
		public void testCountById()
		{
			int total =sDao.getStationNumber(1);
			System.out.println(total);
			
		}


}
