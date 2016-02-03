package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.exception.ParentRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月2日 下午4:43:59 
* @parameter   
* @return 
*/
public class ParentDao 
{
	//实现家长表的增删改查，分页查询
	
	//添加一个家长
	public void addParent (Parent p)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="insert into parent(studentNumber,parentName,parentNickName,parentHeader,parentPwd,parentSex,parentTelePhone,address,backgroundPhoto) values(?,?,?,?,?,?,?,?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setString(1, p.getStudent().getStudentNumber());
			pStatement.setString(2, p.getParentName());
			pStatement.setString(3, p.getParentNickName());
			pStatement.setString(4, p.getParentHeader());
			pStatement.setString(5, p.getParentPwd());
			pStatement.setString(6, p.getParentSex());
			pStatement.setString(7, p.getParentTelePhone());
			pStatement.setString(8, p.getAddress());
			pStatement.setString(9, p.getBackgroundPhoto());
			pStatement.executeUpdate();
			/*if (i>0) 
			{
				System.out.println("插入成功");
			}*/
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new ParentRunTimeException("家长表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ParentRunTimeException("家长表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ParentRunTimeException("家长表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	
	//批量删除家长，以传入的id数组为准
			public void deleteParent (int[] ids)
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				try {
					conn=DBConnection.getConnection();
					String sql ="delete from parent where parentId=?";
					pStatement=conn.prepareStatement(sql);
					for (int id : ids)
					{
						pStatement.setInt(1, id);
						pStatement.executeUpdate();
						/*if (i>0) 
						{
							System.out.println("删除成功");
						}*/
					}
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement);
				}
			}
			
			
			//更新一个家长的信息,主键正增长的id就不更新了。
			public void updateParent (Parent p)
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				try {
					conn=DBConnection.getConnection();
					String sql ="update parent set studentNumber=?,parentName=?,parentNickName=?,parentHeader=?,parentPwd=?,parentSex=?,parentTelePhone=?,address=?,backgroundPhoto=? where parentId=?";
					pStatement=conn.prepareStatement(sql);
					pStatement.setString(1, p.getStudent().getStudentNumber());
					pStatement.setString(2, p.getParentName());
					pStatement.setString(3, p.getParentNickName());
					pStatement.setString(4, p.getParentHeader());
					pStatement.setString(5, p.getParentPwd());
					pStatement.setString(6, p.getParentSex());
					pStatement.setString(7, p.getParentTelePhone());
					pStatement.setString(8, p.getAddress());
					pStatement.setString(9, p.getBackgroundPhoto());
					pStatement.setInt(10, p.getParentId());
					pStatement.executeUpdate();
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("学生表dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("学生表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("学生表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement);
				}
				
			}
	
	
			//查找某个家长，根据唯一标识的手机号查询
			public Parent getParentByTelephone (String telephone)
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				ResultSet rSet =null;
				Parent p =null;
				
				try {
					conn=DBConnection.getConnection();
					String sql ="select studentNumber,parentId,parentName,parentNickName,parentHeader,parentPwd,parentSex,parentTelePhone,address,backgroundPhoto from parent where parentTelePhone=?";
					pStatement =conn.prepareStatement(sql);
					pStatement.setString(1, telephone);
					rSet=pStatement.executeQuery();
					
					if(rSet.next())
					{
						p = new Parent();
						p.setAddress(rSet.getString("address"));
						p.setBackgroundPhoto(rSet.getString("backgroundPhoto"));
						p.setParentHeader(rSet.getString("parentHeader"));
						p.setParentId(rSet.getInt("parentId"));
						p.setParentName(rSet.getString("parentName"));
						p.setParentNickName(rSet.getString("parentNickName"));
						p.setParentPwd(rSet.getString("parentPwd"));
						p.setParentSex(rSet.getString("parentSex"));
						p.setParentTelePhone(rSet.getString("parentTelePhone"));
						Student s = new Student();
						s.setStudentNumber(rSet.getString("studentNumber"));
						p.setStudent(s);
						
					}
					
					
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement,rSet);
				}
				return p;
				
			}
			
			//查找某个家长，根据家长id
			public Parent getParentByParentId (int parentId)
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				ResultSet rSet =null;
				Parent p =null;
				
				try {
					conn=DBConnection.getConnection();
					String sql ="select studentNumber,parentId,parentName,parentNickName,parentHeader,parentPwd,parentSex,parentTelePhone,address,backgroundPhoto from parent where parentId=?";
					pStatement =conn.prepareStatement(sql);
					pStatement.setInt(1, parentId);
					rSet=pStatement.executeQuery();
					
					if(rSet.next())
					{
						p = new Parent();
						p.setAddress(rSet.getString("address"));
						p.setBackgroundPhoto(rSet.getString("backgroundPhoto"));
						p.setParentHeader(rSet.getString("parentHeader"));
						p.setParentId(rSet.getInt("parentId"));
						p.setParentName(rSet.getString("parentName"));
						p.setParentNickName(rSet.getString("parentNickName"));
						p.setParentPwd(rSet.getString("parentPwd"));
						p.setParentSex(rSet.getString("parentSex"));
						p.setParentTelePhone(rSet.getString("parentTelePhone"));
						Student s = new Student();
						s.setStudentNumber(rSet.getString("studentNumber"));
						p.setStudent(s);
						
					}
					
					
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement,rSet);
				}
				return p;		
			}
			
			//查找所有家长
			public List<Parent> getAllParent ()
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				ResultSet rSet =null;
				List<Parent> list =null;
				try {
					conn=DBConnection.getConnection();
					String sql ="select studentNumber,parentId,parentName,parentNickName,parentHeader,parentPwd,parentSex,parentTelePhone,address,backgroundPhoto from parent";
					pStatement =conn.prepareStatement(sql);
					rSet=pStatement.executeQuery();
					Parent p =null;
					list =new ArrayList<>();
					Student s=null;
					while(rSet.next())
					{
						p=new Parent();
						s=new Student();
						s.setStudentNumber(rSet.getString("studentNumber"));
						p.setStudent(s);
						p.setAddress(rSet.getString("address"));
						p.setBackgroundPhoto(rSet.getString("backgroundPhoto"));
						p.setParentHeader(rSet.getString("parentHeader"));
						p.setParentId(rSet.getInt("parentId"));
						p.setParentName(rSet.getString("parentName"));
						p.setParentNickName(rSet.getString("parentNickName"));
						p.setParentPwd(rSet.getString("parentPwd"));
						p.setParentSex(rSet.getString("parentSex"));
						p.setParentTelePhone(rSet.getString("parentTelePhone"));	
						list.add(p);	
					}
					
					
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement,rSet);
				}
				return list;
				
			}
			
			//模糊查找家长,找不到，返回一个长度为零的list
			public List<Parent> getParentByFuzzy (String fuzzyName)
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				ResultSet rSet =null;
				List<Parent> list =null;
				try {
					conn=DBConnection.getConnection();
					String sql ="select studentNumber,parentId,parentName,parentNickName,parentHeader,parentPwd,parentSex,parentTelePhone,address,backgroundPhoto from parent where parentName like ?";
					pStatement =conn.prepareStatement(sql);
					pStatement.setString(1, "%"+fuzzyName+"%");
					rSet=pStatement.executeQuery();
					Parent p =null;
					list =new ArrayList<>();
					Student s=null;
					while(rSet.next())
					{
						p=new Parent();
						s=new Student();
						s.setStudentNumber(rSet.getString("studentNumber"));
						p.setStudent(s);
						p.setAddress(rSet.getString("address"));
						p.setBackgroundPhoto(rSet.getString("backgroundPhoto"));
						p.setParentHeader(rSet.getString("parentHeader"));
						p.setParentId(rSet.getInt("parentId"));
						p.setParentName(rSet.getString("parentName"));
						p.setParentNickName(rSet.getString("parentNickName"));
						p.setParentPwd(rSet.getString("parentPwd"));
						p.setParentSex(rSet.getString("parentSex"));
						p.setParentTelePhone(rSet.getString("parentTelePhone"));	
						list.add(p);	
					}
					
					
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement,rSet);
				}
				return list;
				
			}
			
			//实现家长分页查询
			public List<Parent> getPageParent (int curPage,int pageSize)
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				ResultSet rSet =null;
				List<Parent> list =null;
				try {
					conn=DBConnection.getConnection();
					int firstRecoder = (curPage-1)*pageSize;
					String sql ="select studentNumber,parentId,parentName,parentNickName,parentHeader,parentPwd,parentSex,parentTelePhone,address,backgroundPhoto from parent limit ?,?";
					pStatement =conn.prepareStatement(sql);
					pStatement.setInt(1, firstRecoder);
					pStatement.setInt(2, pageSize);
					rSet=pStatement.executeQuery();
					
					Parent p =null;
					list =new ArrayList<>();
					Student s=null;
					while(rSet.next())
					{
						p=new Parent();
						s=new Student();
						s.setStudentNumber(rSet.getString("studentNumber"));
						p.setStudent(s);
						p.setAddress(rSet.getString("address"));
						p.setBackgroundPhoto(rSet.getString("backgroundPhoto"));
						p.setParentHeader(rSet.getString("parentHeader"));
						p.setParentId(rSet.getInt("parentId"));
						p.setParentName(rSet.getString("parentName"));
						p.setParentNickName(rSet.getString("parentNickName"));
						p.setParentPwd(rSet.getString("parentPwd"));
						p.setParentSex(rSet.getString("parentSex"));
						p.setParentTelePhone(rSet.getString("parentTelePhone"));	
						list.add(p);	
					}
					
					
					
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement,rSet);
				}
				return list;
				
			}
	
	
	
	
			//统计家长的总人数
			public int getParentNumber ()
			{
				Connection conn=null;
				PreparedStatement pStatement=null;
				ResultSet rSet =null;
				int total =0;
				
				try {
					conn=DBConnection.getConnection();
					String sql ="select count(*) c from parent";
					pStatement =conn.prepareStatement(sql);
					rSet=pStatement.executeQuery();
			
					if(rSet.next())
					{
						total=rSet.getInt("c");		
					}
					
					
				} catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (SQLException e) 
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				} catch (IOException e)
				{
					e.printStackTrace();
					throw new ParentRunTimeException("家长表dao层出错");
				}
				finally
				{
					DBConnection.release(conn, pStatement,rSet);
				}
				return total;
				
			}
			
	
	

		
	

}
