package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.exception.PhotoRunTimeException;
import com.gem.babyplan.exception.StudentRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月4日 上午10:08:03 
* @parameter   
* @return 
*/
public class PhotoDao 
{
	//相片表的增删改查
	//添加一张相片
	public void addPhoto (Photo photo)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="insert into photo(photoId,albumId,photoURL,photoPublishTime,photoDescribe) values(?,?,?,?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, photo.getPhotoId());
			pStatement.setInt(2, photo.getAlbum().getAlbumId());
			pStatement.setString(3, photo.getPhotoURL());
			pStatement.setDate(4, new Date(photo.getPhotoPublishTime().getTime()));
			pStatement.setString(5, photo.getPhotoDescribe());
			pStatement.executeUpdate();
			/*if (i>0) 
			{
				System.out.println("插入成功");
			}*/
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//批量删除照片，以传入的照片的id数组
	public void deletePhoto (int [] ids)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="delete from photo where photoId=?";
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
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//更新一个照片的信息,照片发布时间已经发布，不需要修改,相片id不需要修改
	public void updatePhoto (Photo p)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="update photo set albumId=?,photoURL=?,photoDescribe=? where photoId=?";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, p.getAlbum().getAlbumId());
			pStatement.setString(2, p.getPhotoURL());
			pStatement.setString(3, p.getPhotoDescribe());
			pStatement.setInt(4, p.getPhotoId());
			pStatement.executeUpdate();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}

	
	//查找某一个照片，按主键id号查询,有则返回照片对象，没有返回null,成员变量只要给外键属性即可
	public Photo getPhotoById(int id)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		Photo p =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select photoId,albumId,photoURL,photoPublishTime,photoDescribe from photo where photoId=?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, id);	
			rSet=pStatement.executeQuery();
			if(rSet.next())
			{
				p= new Photo();
				Album album = new Album();
				album.setAlbumId(rSet.getInt("albumId"));
				p.setAlbum(album);
				p.setPhotoDescribe(rSet.getString("photoDescribe"));
				p.setPhotoId(rSet.getInt("photoId"));
				p.setPhotoPublishTime(rSet.getDate("photoPublishTime"));
				p.setPhotoURL(rSet.getString("photoURL"));
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return p;
		
		
	}
	//返回所有照片的对象
	public List<Photo> getAllPhoto ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Photo> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select photoId,albumId,photoURL,photoPublishTime,photoDescribe from photo";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
			Photo p =null;
			list =new ArrayList<>();
			Album album=null;
			while(rSet.next())
			{
				p= new Photo();
				album = new Album();
				album.setAlbumId(rSet.getInt("albumId"));
				p.setAlbum(album);
				p.setPhotoDescribe(rSet.getString("photoDescribe"));
				p.setPhotoId(rSet.getInt("photoId"));
				p.setPhotoPublishTime(rSet.getDate("photoPublishTime"));
				p.setPhotoURL(rSet.getString("photoURL"));
				list.add(p);	
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	
	
	//实现照片分页查询
	public List<Photo> getPagePhoto (int curPage,int pageSize)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Photo> list =null;
		try {
			conn=DBConnection.getConnection();
			int firstRecoder = (curPage-1)*pageSize;
			String sql ="select photoId,albumId,photoURL,photoPublishTime,photoDescribe from photo limit ?,?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, firstRecoder);
			pStatement.setInt(2, pageSize);
			rSet=pStatement.executeQuery();
			Photo p =null;
			list =new ArrayList<>();
			Album album=null;
			while(rSet.next())
			{
				p= new Photo();
				album = new Album();
				album.setAlbumId(rSet.getInt("albumId"));
				p.setAlbum(album);
				p.setPhotoDescribe(rSet.getString("photoDescribe"));
				p.setPhotoId(rSet.getInt("photoId"));
				p.setPhotoPublishTime(rSet.getDate("photoPublishTime"));
				p.setPhotoURL(rSet.getString("photoURL"));
				list.add(p);	
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	//统计照片的总数
	public int getPhotoNumber ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		int total =0;
		
		try {
			conn=DBConnection.getConnection();
			String sql ="select count(*) c from photo";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
	
			if(rSet.next())
			{
				total=rSet.getInt("c");		
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new PhotoRunTimeException("相片表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return total;
		
	}
	

}
