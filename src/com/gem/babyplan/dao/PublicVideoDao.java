package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.PublicVideo;
import com.gem.babyplan.exception.PublicVideoDaoRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午7:33:19 
* @parameter   
* @return 
*/
public class PublicVideoDao 
{
	//公共视频的增删改查
	
	public void addPublicVideo (PublicVideo pv)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="insert into publicvideo(publicAddress,publicTime,publicVideoURL,publicDescribe,publicThumbnail) values(?,?,?,?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, pv.getPublicAddress());
			pStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pStatement.setString(3, pv.getPublicVideoURL());
			pStatement.setString(4, pv.getPublicDescribe());
			pStatement.setString(5, pv.getPublicThumbnail());
			pStatement.executeUpdate();
			/*if (i>0) 
			{
				System.out.println("插入成功");
			}*/
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//批量删除公共视频表，以传入的主键的id数组
	public void deletePublicVideo (int [] ids)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="delete from publicvideo where publicId=?";
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
		}  catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	
	//更新一个公共视频的信息,id不需要修改,发布时间不能修改
	public void updatePublicVideo (PublicVideo pv)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="update publicvideo set publicAddress=?,publicVideoURL=?,publicDescribe=?,publicThumbnail=? where publicId=?";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, pv.getPublicAddress());
			pStatement.setString(2, pv.getPublicVideoURL());
			pStatement.setString(3, pv.getPublicDescribe());
			pStatement.setString(4, pv.getPublicThumbnail());
			pStatement.setInt(5, pv.getPublicId());
			pStatement.executeUpdate();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}

	
	//查找某一个公共视频，按主键id号查询,有则返回对象，没有返回null,成员变量只要给外键属性即可
	public PublicVideo getPublicVideoById(int id)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		PublicVideo pv =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select publicId,publicAddress,publicTime,publicVideoURL,publicDescribe,publicThumbnail from publicvideo where publicId=?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, id);	
			rSet=pStatement.executeQuery();
			if(rSet.next())
			{
				pv= new PublicVideo();
				pv.setPublicAddress(rSet.getInt("publicAddress"));
				pv.setPublicDescribe(rSet.getString("publicDescribe"));
				pv.setPublicId(rSet.getInt("publicId"));
				pv.setPublicThumbnail(rSet.getString("publicThumbnail"));
				pv.setPublicTime(rSet.getTimestamp("publicTime"));
				pv.setPublicVideoURL(rSet.getString("publicVideoURL"));
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return pv;
		
		
	}
	//返回所有公共视频的对象。
	public List<PublicVideo> getAllPublicVideo ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<PublicVideo> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select publicId,publicAddress,publicTime,publicVideoURL,publicDescribe,publicThumbnail from publicvideo";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
			PublicVideo pv =null;
			list =new ArrayList<>();
	
			while(rSet.next())
			{
				pv= new PublicVideo();
				pv.setPublicAddress(rSet.getInt("publicAddress"));
				pv.setPublicDescribe(rSet.getString("publicDescribe"));
				pv.setPublicId(rSet.getInt("publicId"));
				pv.setPublicThumbnail(rSet.getString("publicThumbnail"));
				pv.setPublicTime(rSet.getTimestamp("publicTime"));
				pv.setPublicVideoURL(rSet.getString("publicVideoURL"));
				
				list.add(pv);	
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	
	
	
	//实现公共视频的分页查询
	public List<PublicVideo> getPagePublicVideo (int curPage,int pageSize)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<PublicVideo> list =null;
		try {
			conn=DBConnection.getConnection();
			int firstRecoder = (curPage-1)*pageSize;
			String sql ="select publicId,publicAddress,publicTime,publicVideoURL,publicDescribe,publicThumbnail from publicvideo limit ?,?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, firstRecoder);
			pStatement.setInt(2, pageSize);
			rSet=pStatement.executeQuery();
			PublicVideo pv =null;
			list =new ArrayList<>();
	
			while(rSet.next())
			{
				pv= new PublicVideo();
				pv.setPublicAddress(rSet.getInt("publicAddress"));
				pv.setPublicDescribe(rSet.getString("publicDescribe"));
				pv.setPublicId(rSet.getInt("publicId"));
				pv.setPublicThumbnail(rSet.getString("publicThumbnail"));
				pv.setPublicTime(rSet.getTimestamp("publicTime"));
				pv.setPublicVideoURL(rSet.getString("publicVideoURL"));
				
				list.add(pv);	
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	//统计班级视频的总数
	public int getPublicVideoNumber ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		int total =0;
		
		try {
			conn=DBConnection.getConnection();
			String sql ="select count(*) c from publicvideo";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
	
			if(rSet.next())
			{
				total=rSet.getInt("c");		
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PublicVideoDaoRunTimeException("公共视频dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return total;
		
	}

}
