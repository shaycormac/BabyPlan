package com.gem.babyplan.dao.test;

import java.util.List;

import org.junit.Test;

import com.gem.babyplan.dao.ParentDao;
import com.gem.babyplan.dao.TeacherDao;
import com.gem.babyplan.dao.WordDao;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.entity.Word;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午10:50:59 
* @parameter   
* @return 
*/
public class WordDaoTest 
{
	//测试家长教师留言表的增删改查
	WordDao wDao = new WordDao();
	
	//有父节点的
	@Test
	public void testAddWord ()
	{
		//假设教师袄磊和家长圣地的互相留言
		//以前有留言记录
		Parent parent = new ParentDao().getParentByParentId(4);
		Teacher teacher = new TeacherDao().getTeacherByTeacherNum("erdan");
		//这是父亲
		Word word = new Word();
		word.setWordId(7);
	
		//假设是第二次留言，老师留言给父母的
		//这才是本次留言
		Word word2 = new Word();
		word2.setParent(parent);
		word2.setTeacher(teacher);
		word2.setWord(word);
		word2.setWordText("不客气，这是应该的");
		wDao.addWord(word2);
	}
	//测试查找对像,根据主键id
	@Test
	public void testFindWord()
	{
		Word word =wDao.getWordById(1);
		System.out.println(word);
		
	}
	//测试查找对像,根据superId
	@Test
	public void testFindBySuperWord()
	{
		List<Word> list = wDao.getWordBySuperId(1);
		for (Word word : list)
		{
			System.out.println(word);
			
		}
	}
//测试查找返回的集合，根据家长的id
	@Test
	public void testFindByParentId ()
	{
		List<Word> list = wDao.getWordByParentId(1);
		for (Word word : list)
		{
			System.out.println(word);
			
		}
		
	}
//测试查找返回的集合，根据家长的id
	@Test
	public void testFindByTeacherNum ()
	{
		List<Word> list = wDao.getWordByTeacherNum("erdan");
		for (Word word : list)
		{
			System.out.println(word);
			
		}
		
	}
//测试查找返回的集合，返回所有的对象
	@Test
	public void testGetAll ()
	{
		List<Word> list = wDao.getAllWord();
		for (Word word : list)
		{
			System.out.println(word);
			
		}
		
	}
//测试查找返回的集合，根据家长的id和教师的主键
	@Test
	public void testGetBy ()
	{
		List<Word> list = wDao.getWordByTeacherParent("aolei", 2);
		for (Word word : list)
		{
			System.out.println(word);
			
		}
		
	}
	
	//通过家长id分页查询
	@Test
	public void testGetpageByParent ()
	{
		List<Word> list = wDao.getPageWord(2, 3,1);
		for (Word word : list)
		{
			System.out.println(word);
			
		}

	}
	//统计总数
	@Test
	public void testGetNum ()
	{
		int total =wDao.getWordNumber();
		System.out.println(total);
		
	}
	//统计总数,根据留言的父母id
	@Test
	public void testGetNumByPId ()
	{
		int total =wDao.getWordNumberByParentId(1);
		System.out.println(total);
		
	}
}
