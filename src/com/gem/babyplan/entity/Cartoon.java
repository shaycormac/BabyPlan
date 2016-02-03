package com.gem.babyplan.entity;

public class Cartoon {
	private int cartoonId;//动画id，主键
	private String cartoonName;//名字
	private String cThumbnail;//缩略图
	
	public int getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(int cartoonId) {
		this.cartoonId = cartoonId;
	}
	public String getCartoonName() {
		return cartoonName;
	}
	public void setCartoonName(String cartoonName) {
		this.cartoonName = cartoonName;
	}
	public String getcThumbnail() {
		return cThumbnail;
	}
	public void setcThumbnail(String cThumbnail) {
		this.cThumbnail = cThumbnail;
	}
	
	@Override
	public String toString() {
		return "Cartoon [cartoonId=" + cartoonId + ", cartoonName=" + cartoonName + ", cThumbnail=" + cThumbnail + "]";
	}
	
	
}
