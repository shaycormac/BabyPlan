/**@Author 炳华儿
   创建时间：2016年2月4日 下午6:07:24
 * 
 */
package com.gem.babyplan.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.dao.ClassesDao;
import com.gem.babyplan.dao.PrivateVideoDao;
import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.PrivateVideo;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月4日 下午6:07:24 
* @parameter   
* @return 
*/
/**
 * @author Administrator
 *
 */
public class PrivateVideoDaoTest 
{
 //测试班级视频的增删改查
	PrivateVideoDao pv = new PrivateVideoDao();
	
	 @Test
		public void testAdd()
		{
			Classes c =new ClassesDao().getClassesByClassNumber("大班");
			PrivateVideo p = new PrivateVideo();
			p.setClasses(c);
			p.setPrivateAddress(1);
			p.setPrivateDescribe("狗狗");
			p.setPrivateThumbnail("/Altair/2.jpg");
			p.setPrivateVideoURL("/Altair/2.mp4");
			pv.addPrivateVideo(p);
			
		}
		
		@Test
		public void testDelete ()
		{
			int ids [] ={2,3};
			pv.deletePrivateVideo(ids);
			
		}
		@Test
		public void testUpdate()
		{
			Classes c =new ClassesDao().getClassesByClassNumber("大班");
			PrivateVideo p = new PrivateVideo();
			p.setClasses(c);
			p.setPrivateId(1);
			p.setPrivateAddress(2);
			p.setPrivateDescribe("萌萌瞬");
			p.setPrivateThumbnail("/Altair/4.jpg");
			p.setPrivateTime(new Date());
			p.setPrivateVideoURL("/Altair/2.mp4");
		    pv.updatePrivateVideo(p);	
			
		}

		
		//查找对象
		@Test
		public void testVideo ()
		{
			PrivateVideo pVideo=pv.getVideoById(2);
			System.out.println(pVideo);
			
		}
		//测试得到所有照片对象
		@Test
		public void testGetAll()
		{
			
			List<PrivateVideo> list =pv.getAllPrivateVideo();
			for (PrivateVideo privateVideo : list)
			{
				System.out.println(privateVideo);
			}
			
		}
		
		//测试分页查询
		@Test
		public void testGetPage()
		{
			List<PrivateVideo> list =pv.getPagePrivateVideo(2, 1);
			for (PrivateVideo privateVideo : list)
			{
				System.out.println(privateVideo);
			}
		}
		
		//统计总数
		@Test
		public void testCount()
		{
			int total =pv.getPrivatVideoNumber();
			System.out.println(total);
			
			
		}
}
