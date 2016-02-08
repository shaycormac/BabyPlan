/**@Author 炳华儿
   创建时间：2016年2月5日 下午8:31:21
 * 
 */
package com.gem.babyplan.dao.test;

import org.junit.Test;

import com.gem.babyplan.dao.TestSelfConnect;
import com.gem.babyplan.dao.TestSelfConnectDao;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月5日 下午8:31:21 
* @parameter   
* @return 
*/
/**
 * @author Administrator
 *
 */
public class TestSelfConnectDaoTest 
{
	//测试添加一个没有父id的记录
	TestSelfConnectDao tDao = new TestSelfConnectDao();
	@Test
	public void testSave()
	{
		//创建一个具体实体对象，由于本次测试是没有父id，所以父id的值不给
		TestSelfConnect tConnect = new TestSelfConnect();
		tConnect.setName("二爷");
		tDao.save(tConnect);
		
	}
	//测试添加一个有父id的记录，譬如添加一个家族成员二叔
	@Test
	public void testSave2()
	{
		//创建一个具体实体对象，由于本次测试有父id，所以父id的值是爷爷，要给的
		//这个是爷爷的对象，只要他的id即可，作为二叔的外键
		TestSelfConnect tConnect = new TestSelfConnect();
		tConnect.setId(1);
		//这是二叔对象
		TestSelfConnect tConnect2 = new TestSelfConnect();
		tConnect2.setName("二叔");
		tConnect2.settConnect(tConnect);
		tDao.save2(tConnect2);
	}

}
