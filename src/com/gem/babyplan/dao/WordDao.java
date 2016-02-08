package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.entity.Word;
import com.gem.babyplan.exception.ParentRunTimeException;
import com.gem.babyplan.exception.WordDaoRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午10:40:44 
* @parameter   
* @return 
*/
public class WordDao 
{

	//教师家长留言表的增删改查
	
	//添加一条记录,分两个方法，一种是有父id的。
	//第二个是没有父id的方法
	
		public void addWord(Word w) 
		{
			// 1.连数据库
			Connection conn = null;
			// 3.获得PreparedStatement对象
			PreparedStatement prep = null;
			
			try {
				conn = DBConnection.getConnection();
				// 2.sql语句
				String sql = "insert into word(wordSuperId,parentId,teacherNumber,wordText,wordTime) values(?,?,?,?,?)";
				prep = conn.prepareStatement(sql);
				// 4.设置？的值
				if (w.getWord()==null)
				{
				//说明是空姐点
					prep.setNull(1,Types.INTEGER);
				}else {
					prep.setInt(1, w.getWord().getWordId());
				}
				prep.setInt(2, w.getParent().getParentId());
				prep.setString(3, w.getTeacher().getTeacherNumber());
				prep.setString(4, w.getWordText());
				prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()) );
				
				// 5.执行sql语句
				prep.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} finally {
				// 6.关闭资源
				DBConnection.release(conn, prep);
			}
		}
		
		//删除某一行记录，注意的是需要判断这个记录有没有被其他记录引用，作为外键，如果有引用，需要把上一个记录删除
		public void deleteWord(int [] ids) 
		{
			// 1.连数据库
			Connection conn = null;
			// 3.获得PreparedStatement对象
			PreparedStatement prep = null;
			
			try {
				conn = DBConnection.getConnection();
				// 2.sql语句
				String sql = "delete from word where wordId=?";
				prep = conn.prepareStatement(sql);
				// 4.设置？的值
				for (int id : ids)
				{
					//需要对id进行判断，是否
					prep.setInt(1, id);
					prep.executeUpdate();
					/*if (i>0) 
					{
						System.out.println("删除成功");
					}*/
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} finally {
				// 6.关闭资源
				DBConnection.release(conn, prep);
			}
		}
		
		//更新没有必要，家长，教师一旦留言，要不删除，要不重新插入，因此，不提供更新。
		//查找很重要
		//先写一个根据id找到留言记录的表
		public Word getWordById(int id) 
		{
			// 1.连数据库
			Connection conn = null;
			// 3.获得PreparedStatement对象
			PreparedStatement prep = null;
			ResultSet rSet=null;
			Word w =null;
			
			try {
				conn = DBConnection.getConnection();
				// 2.sql语句
				String sql = "select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word where wordId=?";
				prep = conn.prepareStatement(sql);
				// 4.设置？的值	
				prep.setInt(1, id);
				// 5.执行sql语句
				rSet=prep.executeQuery();
				if (rSet.next()) {
					w = new Word();
					Parent p = new Parent();
					p.setParentId(rSet.getInt("parentId"));
					Teacher t = new Teacher();
					t.setTeacherNumber(rSet.getString("teacherNumber"));
					w.setTeacher(t);
					w.setParent(p);
					w.setWordId(rSet.getInt("wordId"));
					w.setWordText(rSet.getString("wordText"));
					w.setWordTime(rSet.getTimestamp("wordTime"));
					//判断父id是否为null
				/*	if (rSet.getInt("wordSuperId")==0)
					{
						//表明没有父id
						Word father = new Word();
						father.setWordId(0);
						w.setWord(father);
					}else {
						//表明有父id
						Word father = new Word();
						father.setWordId(rSet.getInt("wordSuperId"));
						w.setWord(father);
					}*/
					Word father = new Word();
					father.setWordId(rSet.getInt("wordSuperId"));
					w.setWord(father);
					
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} finally {
				// 6.关闭资源
				DBConnection.release(conn, prep);
			}
			return w;
		}
		//再写一个根据父id找记录,可能是多个，作为删除的方法使用,找到就返回这个记录，找不到就返回null
		public List<Word> getWordBySuperId(int id) 
		{
			// 1.连数据库
			Connection conn = null;
			// 3.获得PreparedStatement对象
			PreparedStatement prep = null;
			ResultSet rSet=null;
			Word w =null;
			List<Word> list =null;
			try 
			{
				conn = DBConnection.getConnection();
				// 2.sql语句
				String sql = "select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word where wordSuperId=? order by wordTime desc";
				prep = conn.prepareStatement(sql);
				// 4.设置？的值	
				prep.setInt(1, id);
				// 5.执行sql语句
				rSet=prep.executeQuery();
				list = new ArrayList<Word>();
				//父亲的id
				Word wFather = null;
				while (rSet.next()) 
				{
					w = new Word();
					wFather = new Word();
					wFather.setWordId(rSet.getInt("wordSuperId"));
					Parent p = new Parent();
					p.setParentId(rSet.getInt("parentId"));
					Teacher t = new Teacher();
					t.setTeacherNumber(rSet.getString("teacherNumber"));
					w.setWord(wFather);
					w.setTeacher(t);
					w.setParent(p);
					w.setWordId(rSet.getInt("wordId"));
					w.setWordText(rSet.getString("wordText"));
					w.setWordTime(rSet.getTimestamp("wordTime"));
					//判断父id是否为null
					/*	if (rSet.getInt("wordSuperId")==0)
					{
						//表明没有父id
						Word father = new Word();
						father.setWordId(0);
						w.setWord(father);
					}else {
						//表明有父id
						Word father = new Word();
						father.setWordId(rSet.getInt("wordSuperId"));
						w.setWord(father);
					}*/
					list.add(w);
					
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} finally 
			{
				// 6.关闭资源
				DBConnection.release(conn, prep,rSet);
			}
			return list;
		}
		
		//再写一个根据父id找记录，找到的返回集合，找不到返回集合长度为0
		public List<Word> getWordByParentId(int id) 
		{
			// 1.连数据库
			Connection conn = null;
			// 3.获得PreparedStatement对象
			PreparedStatement prep = null;
			ResultSet rSet=null;
			Word w =null;
			List<Word> list =null;
			try 
			{
				conn = DBConnection.getConnection();
				// 2.sql语句
				String sql = "select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word where parentId=? order by wordTime desc";
				prep = conn.prepareStatement(sql);
				// 4.设置？的值	
				prep.setInt(1, id);
				// 5.执行sql语句
				rSet=prep.executeQuery();
				list = new ArrayList<Word>();
				//父亲的id
				Word wFather = null;
				while (rSet.next()) 
				{
					w = new Word();
					wFather = new Word();
					wFather.setWordId(rSet.getInt("wordSuperId"));
					Parent p = new Parent();
					p.setParentId(rSet.getInt("parentId"));
					Teacher t = new Teacher();
					t.setTeacherNumber(rSet.getString("teacherNumber"));
					w.setWord(wFather);
					w.setTeacher(t);
					w.setParent(p);
					w.setWordId(rSet.getInt("wordId"));
					w.setWordText(rSet.getString("wordText"));
					w.setWordTime(rSet.getTimestamp("wordTime"));
					//判断父id是否为null
					/*	if (rSet.getInt("wordSuperId")==0)
					{
						//表明没有父id
						Word father = new Word();
						father.setWordId(0);
						w.setWord(father);
					}else {
						//表明有父id
						Word father = new Word();
						father.setWordId(rSet.getInt("wordSuperId"));
						w.setWord(father);
					}*/
					list.add(w);
					
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} finally 
			{
				// 6.关闭资源
				DBConnection.release(conn, prep,rSet);
			}
			return list;
		}
		
		//再写一个根据教师主键找记录，找到的返回集合，找不到返回集合长度为0,按照更新时间查找
		public List<Word> getWordByTeacherNum(String num) 
		{
			// 1.连数据库
			Connection conn = null;
			// 3.获得PreparedStatement对象
			PreparedStatement prep = null;
			ResultSet rSet=null;
			Word w =null;
			List<Word> list =null;
			try 
			{
				conn = DBConnection.getConnection();
				// 2.sql语句
				String sql = "select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word where teacherNumber=? order by wordTime desc";
				prep = conn.prepareStatement(sql);
				// 4.设置？的值	
				prep.setString(1, num);
				// 5.执行sql语句
				rSet=prep.executeQuery();
				list = new ArrayList<Word>();
				//父亲的id
				Word wFather = null;
				while (rSet.next()) 
				{
					w = new Word();
					wFather = new Word();
					wFather.setWordId(rSet.getInt("wordSuperId"));
					Parent p = new Parent();
					p.setParentId(rSet.getInt("parentId"));
					Teacher t = new Teacher();
					t.setTeacherNumber(rSet.getString("teacherNumber"));
					w.setWord(wFather);
					w.setTeacher(t);
					w.setParent(p);
					w.setWordId(rSet.getInt("wordId"));
					w.setWordText(rSet.getString("wordText"));
					w.setWordTime(rSet.getTimestamp("wordTime"));
					
					list.add(w);
					
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} finally 
			{
				// 6.关闭资源
				DBConnection.release(conn, prep,rSet);
			}
			return list;
		}
		//返回所有的对象
		public List<Word> getAllWord() 
		{
			// 1.连数据库
			Connection conn = null;
			// 3.获得PreparedStatement对象
			PreparedStatement prep = null;
			ResultSet rSet=null;
			Word w =null;
			List<Word> list =null;
			try 
			{
				conn = DBConnection.getConnection();
				// 2.sql语句
				String sql = "select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word order by wordTime desc";
				prep = conn.prepareStatement(sql);
					
				
				// 5.执行sql语句
				rSet=prep.executeQuery();
				list = new ArrayList<Word>();
				//父亲的id
				Word wFather = null;
				while (rSet.next()) 
				{
					w = new Word();
					wFather = new Word();
					wFather.setWordId(rSet.getInt("wordSuperId"));
					Parent p = new Parent();
					p.setParentId(rSet.getInt("parentId"));
					Teacher t = new Teacher();
					t.setTeacherNumber(rSet.getString("teacherNumber"));
					w.setWord(wFather);
					w.setTeacher(t);
					w.setParent(p);
					w.setWordId(rSet.getInt("wordId"));
					w.setWordText(rSet.getString("wordText"));
					w.setWordTime(rSet.getTimestamp("wordTime"));
					
					list.add(w);
					
				}
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} catch (IOException e) {
				e.printStackTrace();
				throw new WordDaoRunTimeException("留言表dao层出错");
			} finally 
			{
				// 6.关闭资源
				DBConnection.release(conn, prep,rSet);
			}
			return list;
		}
		
		//根据家长的id和教师的num一起查找
		//再写一个根据教师主键找记录，找到的返回集合，找不到返回集合长度为0,按照更新时间查找
				public List<Word> getWordByTeacherParent(String teacherNum,int parentId) 
				{
					// 1.连数据库
					Connection conn = null;
					// 3.获得PreparedStatement对象
					PreparedStatement prep = null;
					ResultSet rSet=null;
					Word w =null;
					List<Word> list =null;
					try 
					{
						conn = DBConnection.getConnection();
						// 2.sql语句
						String sql = "select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word where teacherNumber=? and parentId=? order by wordTime desc";
						prep = conn.prepareStatement(sql);
						// 4.设置？的值	
						prep.setString(1, teacherNum);
						prep.setInt(2, parentId);
						// 5.执行sql语句
						rSet=prep.executeQuery();
						list = new ArrayList<Word>();
						//父亲的id
						Word wFather = null;
						while (rSet.next()) 
						{
							w = new Word();
							wFather = new Word();
							wFather.setWordId(rSet.getInt("wordSuperId"));
							Parent p = new Parent();
							p.setParentId(rSet.getInt("parentId"));
							Teacher t = new Teacher();
							t.setTeacherNumber(rSet.getString("teacherNumber"));
							w.setWord(wFather);
							w.setTeacher(t);
							w.setParent(p);
							w.setWordId(rSet.getInt("wordId"));
							w.setWordText(rSet.getString("wordText"));
							w.setWordTime(rSet.getTimestamp("wordTime"));
							
							list.add(w);
							
						}
					}
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (SQLException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (IOException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} finally 
					{
						// 6.关闭资源
						DBConnection.release(conn, prep,rSet);
					}
					return list;
				}
				
				//再写个分页查询
				//实现家长分页查询
				public List<Word> getPageWord (int curPage,int pageSize)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					List<Word> list =null;
					Word w = null;
					try {
						conn=DBConnection.getConnection();
						int firstRecoder = (curPage-1)*pageSize;
						String sql ="select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word order by wordTime desc limit ?,?";
						pStatement =conn.prepareStatement(sql);
						pStatement.setInt(1, firstRecoder);
						pStatement.setInt(2, pageSize);
						rSet=pStatement.executeQuery();
						list = new ArrayList<Word>();
						//父亲的id
						Word wFather = null;
						while (rSet.next()) 
						{
							w = new Word();
							wFather = new Word();
							wFather.setWordId(rSet.getInt("wordSuperId"));
							Parent p = new Parent();
							p.setParentId(rSet.getInt("parentId"));
							Teacher t = new Teacher();
							t.setTeacherNumber(rSet.getString("teacherNumber"));
							w.setWord(wFather);
							w.setTeacher(t);
							w.setParent(p);
							w.setWordId(rSet.getInt("wordId"));
							w.setWordText(rSet.getString("wordText"));
							w.setWordTime(rSet.getTimestamp("wordTime"));
							
							list.add(w);
							
						}
					}
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (SQLException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (IOException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} finally 
					{
						// 6.关闭资源
						DBConnection.release(conn, pStatement,rSet);
					}
					return list;
				}
				
		
				//再写个分页查询
				//实现通过家长id分页查询
				public List<Word> getPageWord (int curPage,int pageSize,int parentId)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					List<Word> list =null;
					Word w = null;
					try {
						conn=DBConnection.getConnection();
						int firstRecoder = (curPage-1)*pageSize;
						String sql ="select wordId,wordSuperId,parentId,teacherNumber,wordText,wordTime from word where parentId=? order by wordTime desc limit ?,?";
						pStatement =conn.prepareStatement(sql);
						pStatement.setInt(1, parentId);
						pStatement.setInt(2, firstRecoder);
						pStatement.setInt(3, pageSize);
						rSet=pStatement.executeQuery();
						list = new ArrayList<Word>();
						//父亲的id
						Word wFather = null;
						while (rSet.next()) 
						{
							w = new Word();
							wFather = new Word();
							wFather.setWordId(rSet.getInt("wordSuperId"));
							Parent p = new Parent();
							p.setParentId(rSet.getInt("parentId"));
							Teacher t = new Teacher();
							t.setTeacherNumber(rSet.getString("teacherNumber"));
							w.setWord(wFather);
							w.setTeacher(t);
							w.setParent(p);
							w.setWordId(rSet.getInt("wordId"));
							w.setWordText(rSet.getString("wordText"));
							w.setWordTime(rSet.getTimestamp("wordTime"));
							
							list.add(w);
							
						}
					}
					catch (ClassNotFoundException e)
					{
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (SQLException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (IOException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} finally 
					{
						// 6.关闭资源
						DBConnection.release(conn, pStatement,rSet);
					}
					return list;
				}
				//统计总个数
				
				public int getWordNumber ()
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					int total =0;
					
					try {
						conn=DBConnection.getConnection();
						String sql ="select count(*) c from word";
						pStatement =conn.prepareStatement(sql);
						rSet=pStatement.executeQuery();
				
						if(rSet.next())
						{
							total=rSet.getInt("c");		
						}
						
						
					}catch (ClassNotFoundException e)
					{
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (SQLException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (IOException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} finally 
					{
						// 6.关闭资源
						DBConnection.release(conn, pStatement,rSet);
					}
					return total;
					
				}
			//统计根据家长id的留言个数，有意义？	
				public int getWordNumberByParentId (int id)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					int total =0;
					
					try {
						conn=DBConnection.getConnection();
						String sql ="select count(*) c from word where parentId=?";
						pStatement =conn.prepareStatement(sql);
						pStatement.setInt(1, id);
						rSet=pStatement.executeQuery();
				
						if(rSet.next())
						{
							total=rSet.getInt("c");		
						}
						
						
					}catch (ClassNotFoundException e)
					{
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (SQLException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} catch (IOException e) {
						e.printStackTrace();
						throw new WordDaoRunTimeException("留言表dao层出错");
					} finally 
					{
						// 6.关闭资源
						DBConnection.release(conn, pStatement,rSet);
					}
					return total;
					
				}
				
				
		
		
}
