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
import com.gem.babyplan.entity.Teacher;
import com.gem.babyplan.exception.ParentRunTimeException;
import com.gem.babyplan.exception.TeacherRunTimeException;
import com.gem.babyplan.utils.DBConnection;

/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月3日 下午8:46:17 
* @parameter   
* @return 
*/
public class TeacherDao 
{
	//实现教师表的增删改查，里面有园长。小心
	
		//添加一个教师
		public void addTeacher (Teacher t)
		{
			Connection conn=null;
			PreparedStatement pStatement=null;
			try {
				conn=DBConnection.getConnection();
				String sql ="insert into teacher(teacherNumber,classNumber,teacherName,teacherHeader,teacherPwd,power,teacherSex,teacherBirthday,teacherTelePhone,graduateSchool,degree,specialty,evaluate,reward,rewardShow) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pStatement=conn.prepareStatement(sql);
				pStatement.setString(1, t.getTeacherNumber());
				pStatement.setString(2, t.getClasses().getClassNumber());
				pStatement.setString(3, t.getTeacherName());
				pStatement.setString(4, t.getTeacherHeader());
				pStatement.setString(5, t.getTeacherPwd());
				pStatement.setInt(6, t.getPower());
				pStatement.setString(7, t.getTeacherSex());
				pStatement.setDate(8, new Date(t.getTeacherBirthday().getTime()));
				pStatement.setString(9, t.getTeacherTelePhone());
				pStatement.setString(10, t.getGraduateSchool());
				pStatement.setString(11, t.getDegree());
				pStatement.setString(12, t.getSpecialty());
				pStatement.setString(13, t.getEvaluate());
				pStatement.setString(14, t.getReward());
				pStatement.setString(15, t.getRewardShow());
				pStatement.executeUpdate();
				/*if (i>0) 
				{
					System.out.println("插入成功");
				}*/
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				throw new TeacherRunTimeException("教师表Dao层出错");
			} catch (SQLException e) 
			{
				e.printStackTrace();
				throw new TeacherRunTimeException("教师表Dao层出错");
			} catch (IOException e)
			{
				e.printStackTrace();
				throw new TeacherRunTimeException("教师表Dao层出错");
			}
			finally
			{
				DBConnection.release(conn, pStatement);
			}
			
		}
		
		//批量删除教师，以传入的ids字符串组为准
				public void deleteParent (String[] ids)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					try {
						conn=DBConnection.getConnection();
						String sql ="delete from teacher where teacherNumber=?";
						pStatement=conn.prepareStatement(sql);
						for (String id : ids)
						{
							pStatement.setString(1, id);
							pStatement.executeUpdate();
							/*if (i>0) 
							{
								System.out.println("删除成功");
							}*/
						}
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement);
					}
				}
				
				
				//更新一个教师的信息,主键不更新
				public void updateTeacher (Teacher t)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					try {
						conn=DBConnection.getConnection();
						String sql ="update teacher set classNumber=?,teacherName=?,teacherHeader=?,teacherPwd=?,power=?,teacherSex=?,teacherBirthday=?,teacherTelePhone=?,graduateSchool=?,degree=?,specialty=?,evaluate=?,reward=?,rewardShow=? where teacherNumber=?";
						pStatement=conn.prepareStatement(sql);
						pStatement.setString(1, t.getClasses().getClassNumber());
						pStatement.setString(2, t.getTeacherName());
						pStatement.setString(3, t.getTeacherHeader());
						pStatement.setString(4, t.getTeacherPwd());
						pStatement.setInt(5, t.getPower());
						pStatement.setString(6, t.getTeacherSex());
						pStatement.setDate(7, new Date(t.getTeacherBirthday().getTime()));
						pStatement.setString(8, t.getTeacherTelePhone());
						pStatement.setString(9, t.getGraduateSchool());
						pStatement.setString(10, t.getDegree());
						pStatement.setString(11, t.getSpecialty());
						pStatement.setString(12, t.getEvaluate());
						pStatement.setString(13, t.getReward());
						pStatement.setString(14, t.getRewardShow());
						pStatement.setString(15, t.getTeacherNumber());
						pStatement.executeUpdate();
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement);
					}
					
				}
		
		
			
				//查找某个教师，根据教师的主键
				public Teacher getTeacherByTeacherNum (String num)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					Teacher t =null;
					
					try {
						conn=DBConnection.getConnection();
						String sql ="select teacherNumber,classNumber,teacherName,teacherHeader,teacherPwd,power,teacherSex,teacherBirthday,teacherTelePhone,graduateSchool,degree,specialty,evaluate,reward,rewardShow from teacher where teacherNumber=?";
						pStatement =conn.prepareStatement(sql);
						pStatement.setString(1, num);
						rSet=pStatement.executeQuery();
						
						if(rSet.next())
						{
							t=new Teacher();
							Classes c = new Classes();
						   c.setClassNumber(rSet.getString("classNumber"));
						   t.setClasses(c);
						   t.setDegree(rSet.getString("degree"));
						   t.setEvaluate(rSet.getString("evaluate"));
						   t.setGraduateSchool(rSet.getString("graduateSchool"));
						   t.setPower(rSet.getInt("power"));
						   t.setReward(rSet.getString("reward"));
						   t.setRewardShow(rSet.getString("rewardShow"));
						   t.setSpecialty(rSet.getString("specialty"));
						   t.setTeacherBirthday(rSet.getDate("teacherBirthday"));
						   t.setTeacherHeader(rSet.getString("teacherHeader"));
						   t.setTeacherName(rSet.getString("teacherName"));
						   t.setTeacherNumber(rSet.getString("teacherNumber"));
						   t.setTeacherPwd(rSet.getString("teacherPwd"));
						   t.setTeacherSex(rSet.getString("teacherSex"));
						   t.setTeacherTelePhone(rSet.getString("teacherTelePhone"));
							
						}
						
						
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement,rSet);
					}
					return t;		
				}
				
				//查找所有老师
				public List<Teacher> getAllTeacher ()
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					List<Teacher> list =null;
					try {
						conn=DBConnection.getConnection();
						String sql ="select teacherNumber,classNumber,teacherName,teacherHeader,teacherPwd,power,teacherSex,teacherBirthday,teacherTelePhone,graduateSchool,degree,specialty,evaluate,reward,rewardShow from teacher";
						pStatement =conn.prepareStatement(sql);
						rSet=pStatement.executeQuery();
						Teacher t =null;
						list =new ArrayList<>();
						Classes c=null;
						while(rSet.next())
						{
							t=new Teacher();
							c=new Classes();
							 c.setClassNumber(rSet.getString("classNumber"));
							   t.setClasses(c);
							   t.setDegree(rSet.getString("degree"));
							   t.setEvaluate(rSet.getString("evaluate"));
							   t.setGraduateSchool(rSet.getString("graduateSchool"));
							   t.setPower(rSet.getInt("power"));
							   t.setReward(rSet.getString("reward"));
							   t.setRewardShow(rSet.getString("rewardShow"));
							   t.setSpecialty(rSet.getString("specialty"));
							   t.setTeacherBirthday(rSet.getDate("teacherBirthday"));
							   t.setTeacherHeader(rSet.getString("teacherHeader"));
							   t.setTeacherName(rSet.getString("teacherName"));
							   t.setTeacherNumber(rSet.getString("teacherNumber"));
							   t.setTeacherPwd(rSet.getString("teacherPwd"));
							   t.setTeacherSex(rSet.getString("teacherSex"));
							   t.setTeacherTelePhone(rSet.getString("teacherTelePhone"));
							list.add(t);	
						}
						
						
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement,rSet);
					}
					return list;
					
				}
				
				//模糊查找教师,找不到，返回一个长度为零的list
				public List<Teacher> getTeacherByFuzzy (String fuzzyName)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					List<Teacher> list =null;
					try {
						conn=DBConnection.getConnection();
						String sql ="select * from teacher where teacherName like ?";
						pStatement =conn.prepareStatement(sql);
						pStatement.setString(1, "%"+fuzzyName+"%");
						rSet=pStatement.executeQuery();
						
						Teacher t =null;
						list =new ArrayList<>();
						Classes c=null;
						while(rSet.next())
						{
							t=new Teacher();
							c=new Classes();
							 c.setClassNumber(rSet.getString("classNumber"));
							   t.setClasses(c);
							   t.setDegree(rSet.getString("degree"));
							   t.setEvaluate(rSet.getString("evaluate"));
							   t.setGraduateSchool(rSet.getString("graduateSchool"));
							   t.setPower(rSet.getInt("power"));
							   t.setReward(rSet.getString("reward"));
							   t.setRewardShow(rSet.getString("rewardShow"));
							   t.setSpecialty(rSet.getString("specialty"));
							   t.setTeacherBirthday(rSet.getDate("teacherBirthday"));
							   t.setTeacherHeader(rSet.getString("teacherHeader"));
							   t.setTeacherName(rSet.getString("teacherName"));
							   t.setTeacherNumber(rSet.getString("teacherNumber"));
							   t.setTeacherPwd(rSet.getString("teacherPwd"));
							   t.setTeacherSex(rSet.getString("teacherSex"));
							   t.setTeacherTelePhone(rSet.getString("teacherTelePhone"));
							list.add(t);	
						}
					
						
						
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement);
					}
					return list;
					
				}
				
				//实现教师分页查询
				public List<Teacher> getPageTeacher (int curPage,int pageSize)
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					List<Teacher> list =null;
					try {
						conn=DBConnection.getConnection();
						int firstRecoder = (curPage-1)*pageSize;
						String sql ="select * from teacher limit ?,?";
						pStatement =conn.prepareStatement(sql);
						pStatement.setInt(1, firstRecoder);
						pStatement.setInt(2, pageSize);
						rSet=pStatement.executeQuery();
						
						Teacher t =null;
						list =new ArrayList<>();
						Classes c=null;
						while(rSet.next())
						{
							t=new Teacher();
							c=new Classes();
							 c.setClassNumber(rSet.getString("classNumber"));
							   t.setClasses(c);
							   t.setDegree(rSet.getString("degree"));
							   t.setEvaluate(rSet.getString("evaluate"));
							   t.setGraduateSchool(rSet.getString("graduateSchool"));
							   t.setPower(rSet.getInt("power"));
							   t.setReward(rSet.getString("reward"));
							   t.setRewardShow(rSet.getString("rewardShow"));
							   t.setSpecialty(rSet.getString("specialty"));
							   t.setTeacherBirthday(rSet.getDate("teacherBirthday"));
							   t.setTeacherHeader(rSet.getString("teacherHeader"));
							   t.setTeacherName(rSet.getString("teacherName"));
							   t.setTeacherNumber(rSet.getString("teacherNumber"));
							   t.setTeacherPwd(rSet.getString("teacherPwd"));
							   t.setTeacherSex(rSet.getString("teacherSex"));
							   t.setTeacherTelePhone(rSet.getString("teacherTelePhone"));
							list.add(t);	
						}
						
					
						
						
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement,rSet);
					}
					return list;
					
				}
		
		
		
		
				//统计教师的总人数
				public int getTeacherNumber ()
				{
					Connection conn=null;
					PreparedStatement pStatement=null;
					ResultSet rSet =null;
					int total =0;
					
					try {
						conn=DBConnection.getConnection();
						String sql ="select count(*) c from teacher";
						pStatement =conn.prepareStatement(sql);
						rSet=pStatement.executeQuery();
				
						if(rSet.next())
						{
							total=rSet.getInt("c");		
						}
						
						
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (SQLException e) 
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					} catch (IOException e)
					{
						e.printStackTrace();
						throw new TeacherRunTimeException("教师表Dao层出错");
					}
					finally
					{
						DBConnection.release(conn, pStatement,rSet);
					}
					return total;
					
				}
				
		
		

}
