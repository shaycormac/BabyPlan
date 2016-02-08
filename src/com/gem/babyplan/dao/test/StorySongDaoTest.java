/**@Author 炳华儿
   创建时间：2016年2月5日 下午10:22:50
 * 
 */
package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.AlbumDao;
import com.gem.babyplan.dao.StorySongDao;
import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.StorySong;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月5日 下午10:22:50 
* @parameter   
* @return 
*/
/**
 * @author Administrator
 *
 */
public class StorySongDaoTest
{
	//测试儿歌表的增删改查
	StorySongDao sDao = new StorySongDao();
	  @Test
		public void testAdd()
		{
			StorySong ss = new StorySong();
			ss.setSsName("小芦苇");
			ss.setSsThumbnail("/servlet/kk.jpg");
			ss.setSsType(0);
			ss.setSsURL("/edward/44.mp3");
			sDao.addStorySong(ss);
			
		}
		
		@Test
		public void testDelete ()
		{
			int ids [] ={2,3};
			sDao.deleteStorySong(ids);
			
		}
		@Test
		public void testUpdate()
		{
			StorySong ss = new StorySong();
			ss.setSsId(2);
			ss.setSsName("葫芦娃");
			ss.setSsThumbnail("/servlet/jj.jpg");
			ss.setSsType(1);
			ss.setSsURL("/edward/32.mp3");
		sDao.updateStorySong(ss);	
			
		}

		
		//查找儿歌对象
		@Test
		public void testGetSong()
		{
			StorySong ss =sDao.getStorySongById(2);
			System.out.println(ss);
			
		}
		//测试模糊查询
		@Test
		public void testGetByFuzzy()
		{
			List<StorySong> list =sDao.getStorySongByFuzzy("芦");
			for (StorySong storySong : list) {
				System.out.println(storySong);
			}
			
		}
		//测试得到所有儿歌故事对象
		@Test
		public void testGetAll()
		{
			
			List<StorySong> list =sDao.getAllStorySong();
			for (StorySong storySong : list)
			{
				System.out.println(storySong);
				
			}
			
			
		}
		
		//测试分页查询
		@Test
		public void testGetPage()
		{
			List<StorySong> list =sDao.getPageStorySong(1, 1);
			for (StorySong storySong : list)
			{
				System.out.println(storySong);
				
			}
		}
		
		//统计学生总数
		@Test
		public void testCount()
		{
			int total =sDao.getStorySongNumber();
			System.out.println(total);
			
			
		}

}
