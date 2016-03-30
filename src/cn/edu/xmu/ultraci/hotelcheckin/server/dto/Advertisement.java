package cn.edu.xmu.ultraci.hotelcheckin.server.dto;

import java.io.Serializable;

public class Advertisement implements Serializable {
	private static final long serialVersionUID = 598973986516570093L;

	private String url;
	private String md5;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}