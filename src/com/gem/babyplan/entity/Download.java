package com.gem.babyplan.entity;

public class Download {
	private int downloadId;//下载id，主键
	
	private PublicVideo publicVideo;//公共视频
	private PrivateVideo privateVideo;//班级视频

	public int getDownloadId() {
		return downloadId;
	}

	public void setDownloadId(int downloadId) {
		this.downloadId = downloadId;
	}

	public PublicVideo getPublicVideo() {
		return publicVideo;
	}

	public void setPublicVideo(PublicVideo publicVideo) {
		this.publicVideo = publicVideo;
	}

	public PrivateVideo getPrivateVideo() {
		return privateVideo;
	}

	public void setPrivateVideo(PrivateVideo privateVideo) {
		this.privateVideo = privateVideo;
	}

	@Override
	public String toString() {
		return "Download [downloadId=" + downloadId + ", publicVideo=" + publicVideo + ", privateVideo=" + privateVideo
				+ "]";
	}

}
