package com.gem.babyplan.dao;
/**
* @author 炳华儿 E-mail: 574583006@qq.com
* @date  创建时间：2016年2月5日 下午8:12:30 
* @parameter   
* @return 
*/
public class TestSelfConnect
{
	private int id;
	private String name;
	private TestSelfConnect tConnect;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TestSelfConnect gettConnect() {
		return tConnect;
	}
	public void settConnect(TestSelfConnect tConnect) {
		this.tConnect = tConnect;
	}
	@Override
	public String toString() {
		return "TestSelfConnect [id=" + id + ", name=" + name + ", tConnect=" + tConnect + "]";
	}
	

}
