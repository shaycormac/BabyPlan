package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月5日 下午8:25:41 
* @parameter   
* @return 
*/
public class TestSelfConnectDao 
{
	//测试自连接添加一个没有外键的记录
	public void save(TestSelfConnect tConnect) 
	{
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into selfconnect(name) values(?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, tConnect.getName());
			
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}
	//测试自连接添加一个有外键的记录,
	public void save2(TestSelfConnect tConnect) 
	{
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;
		
		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into selfconnect(name,superId) values(?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, tConnect.getName());
			prep.setInt(2, tConnect.gettConnect().getId());
			
			// 5.执行sql语句
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			DBConnection.release(conn, prep);
		}
	}

}
