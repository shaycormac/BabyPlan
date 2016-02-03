package com.gem.babyplan.entity;

import java.util.Date;

public class Click {
	private Dynamic dynamic;//动态
	private Parent parent;//家长
	
	private Date clickTime;//时间

	public Dynamic getDynamic() {
		return dynamic;
	}

	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}

	@Override
	public String toString() {
		return "Click [dynamic=" + dynamic + ", parent=" + parent + ", clickTime=" + clickTime + "]";
	}
	
	
}
