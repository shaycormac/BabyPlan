package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Album;
import com.gem.babyplan.entity.Cartoon;
import com.gem.babyplan.entity.Photo;
import com.gem.babyplan.entity.Station;
import com.gem.babyplan.exception.PhotoRunTimeException;
import com.gem.babyplan.exception.StationDaoRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月6日 下午9:03:41 
* @parameter   
* @return 
*/
public class StationDao 
{
	//动画片集数表，按照动画片名排序，并且要有模糊查询
	
	public void addStation (Station s)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="insert into station(cartoonId,whichStation,stationURL) values(?,?,?)";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, s.getCatroon().getCartoonId());
			pStatement.setString(2, s.getWhichStation());
			pStatement.setString(3, s.getStationURL());
			pStatement.executeUpdate();
			/*if (i>0) 
			{
				System.out.println("插入成功");
			}*/
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//批量删除集数，以传入的id数组
	public void deleteStation (int [] ids)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="delete from station where stationId=?";
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
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}
	//更新一个集数的信息,id不需要修改
	public void updateStation (Station s)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="update station set cartoonId=?,whichStation=?,stationURL=? where stationId=?";
			pStatement=conn.prepareStatement(sql);
			pStatement.setInt(1, s.getCatroon().getCartoonId());
			pStatement.setString(2, s.getWhichStation());
			pStatement.setString(3, s.getStationURL());
			pStatement.setInt(4, s.getStationId());
			pStatement.executeUpdate();
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement);
		}
		
	}

	
	//查找某一集，按主键id号查询,有则返回对象，没有返回null,成员变量只要给外键属性即可
	public Station getStationById(int id)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		Station s=null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select stationId,cartoonId,whichStation,stationURL from station where stationId=?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, id);	
			rSet=pStatement.executeQuery();
			if(rSet.next())
			{
				s=new Station();
				Cartoon c = new Cartoon();
				c.setCartoonId(rSet.getInt("cartoonId"));
				s.setCatroon(c);
				s.setStationId(rSet.getInt("stationId"));
				s.setStationURL(rSet.getString("stationURL"));
				s.setWhichStation(rSet.getString("whichStation"));
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return s;
		
		
	}
	//返回所有集数的对象
	public List<Station> getAllStation ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Station> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select stationId,cartoonId,whichStation,stationURL from station order by cartoonId";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
			Station s =null;
			list =new ArrayList<>();
			Cartoon c=null;
			while(rSet.next())
			{
				s=new Station();
				c=new Cartoon();
				c.setCartoonId(rSet.getInt("cartoonId"));
				s.setCatroon(c);
				s.setStationId(rSet.getInt("stationId"));
				s.setStationURL(rSet.getString("stationURL"));
				s.setWhichStation(rSet.getString("whichStation"));
				list.add(s);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	//返回每一部动画片集数的对象，以传入动画片的id为主
	public List<Station> getAllStationOfCartoon (int id)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Station> list =null;
		try {
			conn=DBConnection.getConnection();
			String sql ="select stationId,cartoonId,whichStation,stationURL from station where cartoonId=? order by cartoonId";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			Station s =null;
			list =new ArrayList<>();
			Cartoon c=null;
			while(rSet.next())
			{
				s=new Station();
				c=new Cartoon();
				c.setCartoonId(rSet.getInt("cartoonId"));
				s.setCatroon(c);
				s.setStationId(rSet.getInt("stationId"));
				s.setStationURL(rSet.getString("stationURL"));
				s.setWhichStation(rSet.getString("whichStation"));
				list.add(s);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	
	
	//实现集数分页查询，按照卡通编号显示
	public List<Station> getPageStation (int curPage,int pageSize)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		List<Station> list =null;
		try {
			conn=DBConnection.getConnection();
			int firstRecoder = (curPage-1)*pageSize;
			String sql ="select stationId,cartoonId,whichStation,stationURL from station order by cartoonId limit ?,?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, firstRecoder);
			pStatement.setInt(2, pageSize);
			rSet=pStatement.executeQuery();
			Station s =null;
			list =new ArrayList<>();
			Cartoon c=null;
			while(rSet.next())
			{
				s=new Station();
				c=new Cartoon();
				c.setCartoonId(rSet.getInt("cartoonId"));
				s.setCatroon(c);
				s.setStationId(rSet.getInt("stationId"));
				s.setStationURL(rSet.getString("stationURL"));
				s.setWhichStation(rSet.getString("whichStation"));
				list.add(s);	
			}
			
			
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return list;
		
	}
	//统计集数的总数
	public int getStationNumber ()
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		int total =0;
		
		try {
			conn=DBConnection.getConnection();
			String sql ="select count(*) c from station";
			pStatement =conn.prepareStatement(sql);
			rSet=pStatement.executeQuery();
	
			if(rSet.next())
			{
				total=rSet.getInt("c");		
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return total;
		
	}
	//统计每部动画片的集数的总数,要传进一个动画片的外键
	public int getStationNumber (int id)
	{
		Connection conn=null;
		PreparedStatement pStatement=null;
		ResultSet rSet =null;
		int total =0;
		
		try {
			conn=DBConnection.getConnection();
			String sql ="select count(*) c from station where cartoonId=?";
			pStatement =conn.prepareStatement(sql);
			pStatement.setInt(1, id);
			rSet=pStatement.executeQuery();
			
			if(rSet.next())
			{
				total=rSet.getInt("c");		
			}
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new StationDaoRunTimeException("集数表dao层出错");
		}
		finally
		{
			DBConnection.release(conn, pStatement,rSet);
		}
		return total;
		
	}
	

}
