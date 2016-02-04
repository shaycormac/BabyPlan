package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.dao.PhotoDao;
import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.Student;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月4日 下午3:52:10 
* @parameter   
* @return 
*/
public class PhotoDaoTest 
{
	//测试照片的dao层
  PhotoDao pDao = new PhotoDao();
  
   @Test
	public void testAdd()
	{
		AlbumDao aDao = new AlbumDao();
		Album album=aDao.getAlbumByAlbumId(2);
		Photo photo = new Photo();
		photo.setAlbum(album);
		photo.setPhotoDescribe("尼玛");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current=sdf.format(new Date());
		System.out.println(current);
		try {
			
			photo.setPhotoPublishTime(sdf.parse(current));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		photo.setPhotoURL("/servlet/edward/1.jpg");
		pDao.addPhoto(photo);
		
	}
	
	@Test
	public void testDelete ()
	{
		int ids [] ={2,3};
		pDao.deletePhoto(ids);
		
	}
	@Test
	public void testUpdate()
	{
	AlbumDao aDao = new AlbumDao();
	Album album=aDao.getAlbumByAlbumId(2);
	Photo photo = new Photo();
	photo.setPhotoId(1);
	photo.setAlbum(album);
	photo.setPhotoDescribe("Fuck");
	photo.setPhotoURL("/servlet/edward/233.jpg");
	pDao.updatePhoto(photo);	
		
	}

	
	//查找照片对象
	@Test
	public void testPhoto ()
	{
		Photo photo=pDao.getPhotoById(1);
		System.out.println(photo);
		
	}
	//测试得到所有照片对象
	@Test
	public void testGetAll()
	{
		
		List<Photo> list =pDao.getAllPhoto();
		for (Photo photo : list) 
		{
			System.out.println(photo);
		}
		
	}
	
	//测试分页查询
	@Test
	public void testGetPage()
	{
		List<Photo> list =pDao.getPagePhoto(2, 4);
		for (Photo photo : list) 
		{
			System.out.println(photo);
		}
	}
	
	//统计学生总数
	@Test
	public void testCount()
	{
		int total =pDao.getPhotoNumber();
		System.out.println(total);
		
		
	}

}
