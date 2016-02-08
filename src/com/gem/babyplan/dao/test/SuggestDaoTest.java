/**@Author 炳华儿
   创建时间：2016年2月6日 下午10:19:46
 * 
 */
package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.SuggestDao;
import com.gem.babyplan.entity.PublicVideo;
import com.gem.babyplan.entity.Suggest;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午10:19:46 
* @parameter   
* @return 
*/
/**
 * @author Administrator
 *
 */
public class SuggestDaoTest 
{
	//测试留言表的增删查，修改已经没有必要存在
	SuggestDao sDao = new SuggestDao();
	 @Test
		public void testAdd()
		{
			Suggest s = new Suggest();
			s.setSuggestText("我好像爱上了那个家长了");
			sDao.addSuggest(s);
			
		}
		
		@Test
		public void testDelete ()
		{
			int ids [] ={2};
			sDao.deleteSuggest(ids);
			
		}
		
		
		//查找某一条留言
		@Test
		public void testSuggest ()
		{
			Suggest s =sDao.getSuggestById(1);
			System.out.println(s);
			
		}
		//测试得到所有留言内容，按照更新时间
		@Test
		public void testGetAll()
		{
			
			List<Suggest> list =sDao.getAllSuggest();
			for (Suggest suggest : list) {
				System.out.println(suggest);
			}
			
		}
		//测试根据留言内容模糊查询
		@Test
		public void getFuzzy()
		{
			List<Suggest> list = sDao.getSuggestByFuzzy("长");
			for (Suggest suggest : list) {
				System.out.println(suggest);
				
			}
		}
		
		//测试分页查询
		@Test
		public void testGetPage()
		{

			List<Suggest> list =sDao.getPageSuggest(2, 2);
			for (Suggest suggest : list) {
				System.out.println(suggest);
			}
		}
		
		//统计留言总数
		@Test
		public void testCount()
		{
			int total =sDao.getSuggestNumber();
			System.out.println(total);
					
		}


}
