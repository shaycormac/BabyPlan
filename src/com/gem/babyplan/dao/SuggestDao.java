package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.PublicVideo;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.entity.Suggest;
import com.gem.babyplan.exception.PublicVideoDaoRunTimeException;
import com.gem.babyplan.exception.StudentRunTimeException;
import com.gem.babyplan.exception.SuggestDaoRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午10:03:46 
* @parameter   
* @return 
*/
public class SuggestDao
{
//建议表，作为留给园长查看的留言建议，提供插入，删除，查找即可，修改没什么意义
	
	public void addSuggest (Suggest s)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="insert into suggest(suggestText,suggestTime) values(?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setString(1, s.getSuggestText());
			pStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pStatement.executeUpdate();
			/*if (i>0) 
			{
				System.out.println("插入成功");
			}*/
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//批量删除建议表，以传入的主键的id数组
	public void deleteSuggest (int [] ids)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="delete from suggest where suggestId=?";
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
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	
	
	
	//查找某一条建议，按主键id号查询,有则返回对象，没有返回null,成员变量只要给外键属性即可
	public Suggest getSuggestById(int id)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		Suggest s =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select suggestId,suggestText,suggestTime from suggest where suggestId=?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, id);	
			rSet=pStatement.executeQuery();
			if(rSet.next())
			{
				s=new Suggest();
				s.setSuggestId(rSet.getInt("suggestId"));
				s.setSuggestText(rSet.getString("suggestText"));
				s.setSuggestTime(rSet.getTimestamp("suggestTime"));
				
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return s;
		
		
	}
	//根据留言的内容关键字提供模糊查询
	public List<Suggest> getSuggestByFuzzy (String fuzzyName)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Suggest> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select * from suggest where suggestText like ? order by suggestTime desc";
			pStatement =conn.prepareStatement(sql);
			pStatement.setString(1, "%"+fuzzyName+"%");
			rSet=pStatement.executeQuery();
			Suggest s=null;
			list =new ArrayList<>();
	
			while(rSet.next())
			{
				s=new Suggest();
				s.setSuggestId(rSet.getInt("suggestId"));
				s.setSuggestText(rSet.getString("suggestText"));
				s.setSuggestTime(rSet.getTimestamp("suggestTime"));
				
				list.add(s);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	//返回所有留言，按照留言时间排序。
	public List<Suggest> getAllSuggest ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Suggest> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select suggestId,suggestText,suggestTime from suggest order by suggestTime desc";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
			Suggest s=null;
			list =new ArrayList<>();
	
			while(rSet.next())
			{
				s=new Suggest();
				s.setSuggestId(rSet.getInt("suggestId"));
				s.setSuggestText(rSet.getString("suggestText"));
				s.setSuggestTime(rSet.getTimestamp("suggestTime"));
				
				list.add(s);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	
	
	
	//实现留言的分页查询
	public List<Suggest> getPageSuggest (int curPage,int pageSize)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Suggest> list =null;
		try {
			conn=DBConnection.getConnection();
			int firstRecoder = (curPage-1)*pageSize;
			String sql ="select suggestId,suggestText,suggestTime from suggest order by suggestTime desc limit ?,?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, firstRecoder);
			pStatement.setInt(2, pageSize);
			rSet=pStatement.executeQuery();
			Suggest s=null;
			list =new ArrayList<>();
	
			while(rSet.next())
			{
				s=new Suggest();
				s.setSuggestId(rSet.getInt("suggestId"));
				s.setSuggestText(rSet.getString("suggestText"));
				s.setSuggestTime(rSet.getTimestamp("suggestTime"));
				
				list.add(s);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	
	//统计留言的总数
	public int getSuggestNumber ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		int total =0;
		
		try {
			conn=DBConnection.getConnection();
			String sql ="select count(*) c from suggest";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
	
			if(rSet.next())
			{
				total=rSet.getInt("c");		
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new SuggestDaoRunTimeException("建议表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return total;
		
	}
}
