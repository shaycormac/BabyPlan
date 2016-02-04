package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.utils.DBConnection;

public class ClassesDao {
	// 插入
	public void save(Classes classes) {
		// 1.连数据库
		Connection conn = null;
		// 3.获得PreparedStatement对象
		PreparedStatement prep = null;

		try {
			conn = DBConnection.getConnection();
			// 2.sql语句
			String sql = "insert into classes(classNumber,className)" + "values(?,?)";
			prep = conn.prepareStatement(sql);
			// 4.设置？的值
			prep.setString(1, classes.getClassNumber());
			prep.setString(2, classes.getClassName());
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

	// 删除,批量删除
	public void delete(String[] classNumber) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from classes where classNumber = ?";
			for (int i = 0; i < classNumber.length; i++) {
				prep = conn.prepareStatement(sql);
				prep.setString(1, classNumber[i]);
				prep.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 修改
	public void update(Classes classes) {
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "update classes set className=? where classNumber = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, classes.getClassName());
			prep.setString(2, classes.getClassNumber());
			prep.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBConnection.release(conn, prep);
		}
	}

	// 查询所有班级
	public List<Classes> selectAll() {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		List<Classes> list = new ArrayList<Classes>();
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from classes";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			while (rs.next()) {
				Classes classes = new Classes();
				classes.setClassNumber(rs.getString("classNumber"));
				classes.setClassName(rs.getString("className"));
				list.add(classes);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return list;
	}

	// 根据主键班级号得到班级信息
	public Classes getClassesByClassNumber(String classNumber) {
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		Classes classes = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from classes where classNumber=?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, classNumber);
			rs = prep.executeQuery();
			classes = new Classes();
			if (rs.next()) {
				classes.setClassNumber(rs.getString("classNumber"));
				classes.setClassName(rs.getString("className"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBConnection.release(conn, prep, rs);
		}
		return classes;
	}
}
