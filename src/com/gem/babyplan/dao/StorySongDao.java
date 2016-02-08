package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.StorySong;
import com.gem.babyplan.exception.StorySongRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月5日 下午9:44:18 
* @parameter   
* @return 
*/
public class StorySongDao 
{
	//儿歌故事的增删改查
	public void addStorySong (StorySong ss)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="insert into storysong(ssType,ssName,ssThumbnail,ssURL) values(?,?,?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, ss.getSsType());
			pStatement.setString(2, ss.getSsName());
			pStatement.setString(3, ss.getSsThumbnail());
			pStatement.setString(4, ss.getSsURL());
			pStatement.executeUpdate();
			/*if (i>0) 
			{
				System.out.println("插入成功");
			}*/
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//批量删除儿歌表，以传入的主键的id数组
	public void deleteStorySong (int [] ids)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="delete from storysong where ssId=?";
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
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//更新一个儿歌表的信息,id不需要修改
	public void updateStorySong (StorySong ss)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="update storysong set ssType=?,ssName=?,ssThumbnail=?,ssURL=? where ssId=?";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, ss.getSsType());
			pStatement.setString(2, ss.getSsName());
			pStatement.setString(3, ss.getSsThumbnail());
			pStatement.setString(4, ss.getSsURL());
			pStatement.setInt(5, ss.getSsId());
			pStatement.executeUpdate();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}

	
	//查找某一个故事儿歌表，按主键id号查询,有则返回对象，没有返回null,成员变量只要给外键属性即可
	public StorySong getStorySongById(int id)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		StorySong ss =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select ssId,ssType,ssName,ssThumbnail,ssURL from storysong where ssId=?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, id);	
			rSet=pStatement.executeQuery();
			if(rSet.next())
			{
				ss= new StorySong();
				ss.setSsId(id);
				ss.setSsName(rSet.getString("ssName"));
				ss.setSsThumbnail(rSet.getString("ssThumbnail"));
				ss.setSsType(rSet.getInt("ssType"));
				ss.setSsURL(rSet.getString("ssURL"));
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
            throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return ss;
		
		
	}
	//返回所有故事儿歌的对象,按类型匪类
	public List<StorySong> getAllStorySong ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<StorySong> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select ssId,ssType,ssName,ssThumbnail,ssURL from storysong order by ssType";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
			StorySong ss =null;
			list =new ArrayList<>();
	
			while(rSet.next())
			{
				ss=new StorySong();
				ss.setSsId(rSet.getInt("ssId"));
				ss.setSsName(rSet.getString("ssName"));
				ss.setSsThumbnail(rSet.getString("ssThumbnail"));
				ss.setSsType(rSet.getInt("ssType"));
				ss.setSsURL(rSet.getString("ssURL"));	
				list.add(ss);	
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	//实现模糊查询，根据儿歌名字查询
	public List<StorySong> getStorySongByFuzzy (String fuzzyName)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<StorySong> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select ssId,ssType,ssName,ssThumbnail,ssURL from storysong where ssName like ? order by ssType";
			pStatement =conn.prepareStatement(sql);
			pStatement.setString(1, "%"+fuzzyName+"%");
			rSet=pStatement.executeQuery();
			StorySong ss =null;
			list =new ArrayList<>();
			while(rSet.next())
			{
				ss=new StorySong();
				ss.setSsId(rSet.getInt("ssId"));
				ss.setSsName(rSet.getString("ssName"));
				ss.setSsThumbnail(rSet.getString("ssThumbnail"));
				ss.setSsType(rSet.getInt("ssType"));
				ss.setSsURL(rSet.getString("ssURL"));	
				list.add(ss);	
			}
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}

	
	
	//实现故事儿歌表的分页查询，按类型分类
	public List<StorySong> getPageStorySong (int curPage,int pageSize)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<StorySong> list =null;
		try {
			conn=DBConnection.getConnection();
			int firstRecoder = (curPage-1)*pageSize;
			String sql ="select ssId,ssType,ssName,ssThumbnail,ssURL from storysong order by ssType limit ?,?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, firstRecoder);
			pStatement.setInt(2, pageSize);
			rSet=pStatement.executeQuery();
			StorySong ss =null;
			list =new ArrayList<>();
	
			while(rSet.next())
			{
				ss=new StorySong();
				ss.setSsId(rSet.getInt("ssId"));
				ss.setSsName(rSet.getString("ssName"));
				ss.setSsThumbnail(rSet.getString("ssThumbnail"));
				ss.setSsType(rSet.getInt("ssType"));
				ss.setSsURL(rSet.getString("ssURL"));	
				list.add(ss);	
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	//统计故事儿歌的总数
	public int getStorySongNumber ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		int total =0;
		
		try {
			conn=DBConnection.getConnection();
			String sql ="select count(*) c from storysong";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
	
			if(rSet.next())
			{
				total=rSet.getInt("c");		
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StorySongRunTimeException("故事儿歌表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return total;
		
	}
	

}
