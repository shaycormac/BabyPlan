package com.gem.babyplan.entity;

public class Station {
	private int stationId;//集数id，主键
	
	private Cartoon catroon;//动画片
	
	private String whichStation;//哪一集
	private String stationURL;//地址
	
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public Cartoon getCatroon() {
		return catroon;
	}
	public void setCatroon(Cartoon catroon) {
		this.catroon = catroon;
	}
	public String getWhichStation() {
		return whichStation;
	}
	public void setWhichStation(String whichStation) {
		this.whichStation = whichStation;
	}
	public String getStationURL() {
		return stationURL;
	}
	public void setStationURL(String stationURL) {
		this.stationURL = stationURL;
	}
	
	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", catroon=" + catroon + ", whichStation=" + whichStation
				+ ", stationURL=" + stationURL + "]";
	}
	
	
	
}
