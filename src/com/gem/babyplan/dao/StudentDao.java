package com.gem.babyplan.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gem.babyplan.entity.Classes;
import com.gem.babyplan.entity.Parent;
import com.gem.babyplan.entity.Student;
import com.gem.babyplan.exception.ParentRunTimeException;
import com.gem.babyplan.exception.StudentRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月2日 下午5:00:49 
* @parameter   
* @return 
*/
public class StudentDao 
{

	//实现学生表的增删改查，分页查询
	
		//添加一个学生
		public void addStudent (Student s)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			try {
				conn=DBConnection.getConnection();
				String sql ="insert into student(studentNumber,studentName,studentSex,studentBirthday,studentPhotoURL,classNumber) values(?,?,?,?,?,?)";
				pStatement=conn.prepareStatement(sql);
				pStatement.setString(1, s.getStudentNumber());
				pStatement.setString(2, s.getStudentName());
				pStatement.setString(3, s.getStudentSex());
				pStatement.setDate(4, new Date(s.getStudentBirthday().getTime()));
				pStatement.setString(5, s.getStudentPhotoURL());
				pStatement.setString(6, s.getClasses().getClassNumber());
				pStatement.executeUpdate();
				/*if (i>0) 
				{
					System.out.println("插入成功");
				}*/
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement);
			}
			
		}
		//批量删除学生，以传入的主键字符串数组
		public void deleteStudent (String [] nums)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			try {
				conn=DBConnection.getConnection();
				String sql ="delete from student where studentNumber=?";
				pStatement=conn.prepareStatement(sql);
				for (String num : nums)
				{
					pStatement.setString(1, num);
					pStatement.executeUpdate();
					/*if (i>0) 
					{
						System.out.println("删除成功");
					}*/
				}
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement);
			}
			
		}
		//更新一个学生的信息
		public void updateStudent (Student s)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			try {
				conn=DBConnection.getConnection();
				String sql ="update student set classNumber=?,studentName=?,studentSex=?,studentBirthday=?,studentPhotoURL=? where studentNumber=?";
				pStatement=conn.prepareStatement(sql);
				pStatement.setString(1, s.getClasses().getClassNumber());
				pStatement.setString(2, s.getStudentName());
				pStatement.setString(3, s.getStudentSex());
				pStatement.setDate(4, new Date(s.getStudentBirthday().getTime()));
				pStatement.setString(5, s.getStudentPhotoURL());
				pStatement.setString(6, s.getStudentNumber());
				pStatement.executeUpdate();
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement);
			}
			
		}
		//?
		//查找某一个学生，使用班级名字和学生姓名一起查询，可能是多个值，存在学生重名，注意，如果没有，返回一个空的list,即list.size()==0;
		/*public List<Student> getStudentByClassName (String className,String studentName)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			List<Student> list =null;
			try {
				conn=DBConnection.getConnection();
				String sql ="select s.studentId,s.studentName,s.classId,s.studentSex,s.studentBirthday,s.studentPhotoURL from student s,classes c where s.studentName=? and c.className=?";
				pStatement =conn.prepareStatement(sql);
				pStatement.setString(1, studentName);
				pStatement.setString(2, className);
				rSet=pStatement.executeQuery();
				Student s =null;
			
				
				list =new ArrayList<>();
				while(rSet.next())
				{
					Classes classes = new Classes();
					classes.setClassId(rSet.getInt("s.classId"));
					classes.setClassName(className);
					s=new Student();
					s.setStudentId(rSet.getInt("s.studentId"));
					s.setStudentName(rSet.getString("s.studentName"));
					s.setClasses(classes);
					s.setStudentSex(rSet.getString("s.studentSex"));
					s.setStudentBirthday(rSet.getDate("s.studentBirthday"));
					s.setStudentPhotoURL(rSet.getString("s.studentPhotoURL"));
					list.add(s);	
				}
				
				
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
			return list;
			
		}*/
		
		//查找某一个学生，按学号查询,有则返回学生对象，没有返回null,成员变量只要给外键属性即可
		public Student getStudentByNumber(String stuNumber)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			Student s =null;
			try {
				conn=DBConnection.getConnection();
				String sql ="select studentNumber,studentName,classNumber,studentSex,studentBirthday,studentPhotoURL from student where studentNumber=?";
				pStatement =conn.prepareStatement(sql);
				pStatement.setString(1, stuNumber);
				
				rSet=pStatement.executeQuery();
	
				if(rSet.next())
				{
					Classes classes = new Classes();
					//存这个属性即可，其它不用存
					classes.setClassNumber(rSet.getString("classNumber"));
					//classes.setClassName(rSet.getString("c.className"));
					s=new Student();
					s.setStudentNumber(rSet.getString("studentNumber"));
					s.setStudentName(rSet.getString("studentName"));
					s.setClasses(classes);
					s.setStudentSex(rSet.getString("studentSex"));
					s.setStudentBirthday(rSet.getDate("studentBirthday"));
					s.setStudentPhotoURL(rSet.getString("studentPhotoURL"));	
				}
				
				
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return s;
			
			
		}
		//返回所有学生的对象
		public List<Student> getAllStudent ()
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			List<Student> list =null;
			try {
				conn=DBConnection.getConnection();
				String sql ="select studentNumber,studentName,classNumber,studentSex,studentBirthday,studentPhotoURL from student order by classNumber";
				pStatement =conn.prepareStatement(sql);
				rSet=pStatement.executeQuery();
				Student s =null;
				list =new ArrayList<>();
				while(rSet.next())
				{
					Classes classes = new Classes();
					classes.setClassNumber(rSet.getString("classNumber"));
				//	classes.setClassName(rSet.getString("c.className"));
					s=new Student();
					s.setStudentNumber(rSet.getString("studentNumber"));
					s.setStudentName(rSet.getString("studentName"));
					s.setClasses(classes);
					s.setStudentSex(rSet.getString("studentSex"));
					s.setStudentBirthday(rSet.getDate("studentBirthday"));
					s.setStudentPhotoURL(rSet.getString("studentPhotoURL"));
					list.add(s);	
				}
				
				
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return list;
			
		}
		
		//模糊查询
				public List<Student> getStudentByFuzzy (String fuzzyName)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					List<Student> list =null;
					try {
						conn=DBConnection.getConnection();
						String sql ="select studentNumber,studentName,classNumber,studentSex,studentBirthday,studentPhotoURL from student where studentName like ? order by classNumber";
						pStatement =conn.prepareStatement(sql);
						pStatement.setString(1, "%"+fuzzyName+"%");
						rSet=pStatement.executeQuery();
						Student s =null;
						list =new ArrayList<>();
						while(rSet.next())
						{
							Classes classes = new Classes();
							classes.setClassNumber(rSet.getString("classNumber"));
						//	classes.setClassName(rSet.getString("c.className"));
							s=new Student();
							s.setStudentNumber(rSet.getString("studentNumber"));
							s.setStudentName(rSet.getString("studentName"));
							s.setClasses(classes);
							s.setStudentSex(rSet.getString("studentSex"));
							s.setStudentBirthday(rSet.getDate("studentBirthday"));
							s.setStudentPhotoURL(rSet.getString("studentPhotoURL"));
							list.add(s);	
						}
						
						
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new StudentRunTimeException("学生表dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new StudentRunTimeException("学生表dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new StudentRunTimeException("学生表dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement,rSet);
					}
					return list;
					
				}
		
		
		
		//实现学生分页查询
		public List<Student> getPageStudent (int curPage,int pageSize)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			List<Student> list =null;
			try {
				conn=DBConnection.getConnection();
				int firstRecoder = (curPage-1)*pageSize;
				String sql ="select studentNumber,studentName,classNumber,studentSex,studentBirthday,studentPhotoURL from student limit ?,? order by classNumber";
				pStatement =conn.prepareStatement(sql);
				pStatement.setInt(1, firstRecoder);
				pStatement.setInt(2, pageSize);
				rSet=pStatement.executeQuery();
				Student s =null;
				list =new ArrayList<>();
				while(rSet.next())
				{
					Classes classes = new Classes();
					classes.setClassNumber(rSet.getString("classNumber"));
				//	classes.setClassName(rSet.getString("c.className"));
					s=new Student();
					s.setStudentNumber(rSet.getString("studentNumber"));
					s.setStudentName(rSet.getString("studentName"));
					s.setClasses(classes);
					s.setStudentSex(rSet.getString("studentSex"));
					s.setStudentBirthday(rSet.getDate("studentBirthday"));
					s.setStudentPhotoURL(rSet.getString("studentPhotoURL"));
					list.add(s);	
				}
				
				
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return list;
			
		}
		//统计学生的总人数
		public int getStudentNumber ()
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			ResultSet rSet =null;
			int total =0;
			
			try {
				conn=DBConnection.getConnection();
				String sql ="select count(*) c from student";
				pStatement =conn.prepareStatement(sql);
				rSet=pStatement.executeQuery();
		
				if(rSet.next())
				{
					total=rSet.getInt("c");		
				}
				
				
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new StudentRunTimeException("学生表dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement,rSet);
			}
			return total;
			
		}
		
		
		
}
