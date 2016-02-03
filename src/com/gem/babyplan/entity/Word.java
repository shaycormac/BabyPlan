package com.gem.babyplan.entity;

import java.util.Date;

public class Word {
	private int wordId;//留言id，主键
	
	private Word word;//父id，自连接
	
	private Parent parent;//家长
	private Teacher teacher;//老师
	
	private String wordText;//留言内容
	private Date wordTime;//留言时间
	
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
	public Word getWord() {
		return word;
	}
	public void setWord(Word word) {
		this.word = word;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getWordText() {
		return wordText;
	}
	public void setWordText(String wordText) {
		this.wordText = wordText;
	}
	public Date getWordTime() {
		return wordTime;
	}
	public void setWordTime(Date wordTime) {
		this.wordTime = wordTime;
	}
	
	@Override
	public String toString() {
		return "Word [wordId=" + wordId + ", word=" + word + ", parent=" + parent + ", teacher=" + teacher
				+ ", wordText=" + wordText + ", wordTime=" + wordTime + "]";
	}
	
	
}
