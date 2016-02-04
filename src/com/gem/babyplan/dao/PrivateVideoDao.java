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
import com.gem.babyplan.entity.PrivateVideo;
import com.gem.babyplan.exception.PrivateVideoDaoRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月4日 下午4:17:14 
* @parameter   
* @return 
*/
public class PrivateVideoDao 
{
	//班级视频的增删改查
		//添加一个班级视频
		public void addPrivateVideo (PrivateVideo pv)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			try {
				//建立联结
				conn=DBConnection.getConnection();
				//准备查询语句
				String sql ="insert into privatevideo(privateId,classNumber,privateAddress,privateTime,privateVideoURL,privateDescribe,privateThumbnail) values(?,?,?,?,?,?,?)";
				
				pStatement=conn.prepareStatement(sql);
				//放入占位符对应的参数
				pStatement.setInt(1, pv.getPrivateId());
				pStatement.setString(2, pv.getClasses().getClassNumber());
				pStatement.setInt(3, pv.getPrivateAddress());
				pStatement.setDate(4, new Date(pv.getPrivateTime().getTime()));
				pStatement.setString(5, pv.getPrivateVideoURL());
				pStatement.setString(6, pv.getPrivateDescribe());
				pStatement.setString(7, pv.getPrivateThumbnail());
				//执行数据库的操作
				pStatement.executeUpdate();
				/*if (i>0) 
				{
					System.out.println("插入成功");
				}*/
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			}
			//将宝贵的连接资源确保关闭
			finally
			{
				DBConnection.release(conn, pStatement);
			}
			
		}
		//批量删除班级视频，以传入的视频的id数组为准
		public void deletePrivateVideo (int [] ids)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			try {
				//建立联结
				conn=DBConnection.getConnection();
				//准备sql语句
				String sql ="delete from privatevideo where privateId=?";
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
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			}
			//将宝贵的连接资源确保关闭
			finally
			{
				DBConnection.release(conn, pStatement);
			}
			
		}
		//更新一个班级视频的信息,发布时间已经发布，不需要修改,id不需要修改
		public void updatePrivateVideo (PrivateVideo pVideo)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			try {
				conn=DBConnection.getConnection();
				String sql ="update privatevideo set classNumber=?,privateAddress=?,privateVideoURL=?,privateDescribe=?,privateThumbnail=? where privateId=?";
				pStatement=conn.prepareStatement(sql);
				pStatement.setString(1, pVideo.getClasses().getClassNumber());
				pStatement.setInt(2, pVideo.getPrivateAddress());
				pStatement.setString(3, pVideo.getPrivateVideoURL());
				pStatement.setString(4, pVideo.getPrivateDescribe());
				pStatement.setString(5, pVideo.getPrivateThumbnail());
				pStatement.setInt(6, pVideo.getPrivateId());
				pStatement.executeUpdate();
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			}
			//将宝贵的连接资源确保关闭
			finally
			{
				DBConnection.release(conn, pStatement);
			}
			
		}

		
		//查找某一个视频，按主键id号查询,有则返回对象，没有返回null,成员变量只要给外键属性即可
		public PrivateVideo getVideoById(int id)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			PrivateVideo pVideo=null;
			try {
				conn=DBConnection.getConnection();
				String sql ="select privateId,classNumber,privateAddress,privateTime,privateVideoURL,privateDescribe,privateThumbnail from privatevideo where privateId=?";
				pStatement =conn.prepareStatement(sql);
				pStatement.setInt(1, id);	
				rSet=pStatement.executeQuery();
				if(rSet.next())
				{
					pVideo = new PrivateVideo();
					Classes c = new Classes();
					c.setClassNumber(rSet.getString("classNumber"));
					pVideo.setClasses(c);
					pVideo.setPrivateAddress(rSet.getInt("privateAddress"));
					pVideo.setPrivateDescribe(rSet.getString("privateDescribe"));
					pVideo.setPrivateId(rSet.getInt("privateId"));
					pVideo.setPrivateThumbnail(rSet.getString("privateThumbnail"));
					pVideo.setPrivateTime(rSet.getDate("privateTime"));
					pVideo.setPrivateVideoURL(rSet.getString("privateVideoURL"));
				}
				
				
			}catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			}
			//将宝贵的连接资源确保关闭
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return pVideo;
			
			
		}
		//返回所有班级的视频，按照班级号排名
		public List<PrivateVideo> getAllPrivateVideo ()
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			List<PrivateVideo> list =null;
			try {
				conn=DBConnection.getConnection();
				String sql ="select * from privatevideo order by classNumber";
				pStatement =conn.prepareStatement(sql);
				rSet=pStatement.executeQuery();
				PrivateVideo pVideo =null;
				list =new ArrayList<>();
				Classes c=null;
				while(rSet.next())
				{
					pVideo= new PrivateVideo();
					c=new Classes();
					c.setClassNumber(rSet.getString("classNumber"));
					pVideo.setClasses(c);
					pVideo.setPrivateAddress(rSet.getInt("privateAddress"));
					pVideo.setPrivateDescribe(rSet.getString("privateDescribe"));
					pVideo.setPrivateId(rSet.getInt("privateId"));
					pVideo.setPrivateThumbnail(rSet.getString("privateThumbnail"));
					pVideo.setPrivateTime(rSet.getDate("privateTime"));
					pVideo.setPrivateVideoURL(rSet.getString("privateVideoURL"));
					list.add(pVideo);	
				}
				
				
			}catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			}
			//将宝贵的连接资源确保关闭
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return list;
			
		}
		
		
		//实现班级视频分页查询，按班级号
		public List<PrivateVideo> getPagePrivateVideo (int curPage,int pageSize)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			List<PrivateVideo> list =null;
			try {
				conn=DBConnection.getConnection();
				int firstRecoder = (curPage-1)*pageSize;
				String sql ="select * from privatevideo order by classNumber limit ?,?";
				pStatement =conn.prepareStatement(sql);
				pStatement.setInt(1, firstRecoder);
				pStatement.setInt(2, pageSize);
				rSet=pStatement.executeQuery();
				PrivateVideo pVideo =null;
				list =new ArrayList<>();
				Classes c=null;
				while(rSet.next())
				{
					pVideo= new PrivateVideo();
					c=new Classes();
					c.setClassNumber(rSet.getString("classNumber"));
					pVideo.setClasses(c);
					pVideo.setPrivateAddress(rSet.getInt("privateAddress"));
					pVideo.setPrivateDescribe(rSet.getString("privateDescribe"));
					pVideo.setPrivateId(rSet.getInt("privateId"));
					pVideo.setPrivateThumbnail(rSet.getString("privateThumbnail"));
					pVideo.setPrivateTime(rSet.getDate("privateTime"));
					pVideo.setPrivateVideoURL(rSet.getString("privateVideoURL"));
					list.add(pVideo);	
				}
				
				
			}catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			}
			//将宝贵的连接资源确保关闭
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return list;
			
		}
		
		//统计班级视频的总数
		public int getPrivatVideoNumber()
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			int total =0;
			
			try {
				conn=DBConnection.getConnection();
				String sql ="select count(*) c from privateVideo";
				pStatement =conn.prepareStatement(sql);
				rSet=pStatement.executeQuery();
				if(rSet.next())
				{
					total=rSet.getInt("c");		
				}
				
				
			}catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new PrivateVideoDaoRunTimeException("班级视频表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return total;
			
		}
	

}
