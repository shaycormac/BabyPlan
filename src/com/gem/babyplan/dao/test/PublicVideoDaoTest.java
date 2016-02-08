package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.dao.PublicVideoDao;
import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.PublicVideo;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午8:22:52 
* @parameter   
* @return 
*/
public class PublicVideoDaoTest
{
	PublicVideoDao pDao = new PublicVideoDao();
	
	  @Test
		public void testAdd()
		{
			PublicVideo pv = new PublicVideo();
			pv.setPublicAddress(1);
			pv.setPublicDescribe("夏普朋友在吃饭");
			pv.setPublicThumbnail("/SERVLET/HAH/WW.JPG");
			pv.setPublicVideoURL("/servlet/banji.mp4");
			pDao.addPublicVideo(pv);
			
		}
		
		@Test
		public void testDelete ()
		{
			int ids [] ={2};
			pDao.deletePublicVideo(ids);
			
		}
		@Test
		public void testUpdate()
		{
		PublicVideo pv = new PublicVideo();
		pv.setPublicId(1);
		pv.setPublicAddress(2);
		pv.setPublicDescribe("呵呵");
		pv.setPublicThumbnail("/SERVLET/HAH/WW.JPG");
		pv.setPublicVideoURL("/servlet/banji.mp4");
		pDao.updatePublicVideo(pv);
		
			
		}

		
		//查找公共视频对象
		@Test
		public void testPublicVideo ()
		{
			PublicVideo pv = pDao.getPublicVideoById(3);
			System.out.println(pv);
			
		}
		//测试得到所有视频对象
		@Test
		public void testGetAll()
		{
			
			List<PublicVideo> list =pDao.getAllPublicVideo();
			for (PublicVideo publicVideo : list) {
				System.out.println(publicVideo);
			}
			
		}
		
		//测试分页查询
		@Test
		public void testGetPage()
		{
			List<PublicVideo> list =pDao.getPagePublicVideo(1, 1);
			for (PublicVideo publicVideo : list) {
				System.out.println(publicVideo);
			}
		}
		
		//统计视频总数
		@Test
		public void testCount()
		{
			int total =pDao.getPublicVideoNumber();
			System.out.println(total);
					
		}

}
