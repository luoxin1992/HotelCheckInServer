package cn.edu.xmu.ultraci.hotelcheckin.server.dto;

import java.io.Serializable;

public class Upgrade implements Serializable {
	private static final long serialVersionUID = -2281405341776197322L;

	private Integer version;
	private Long size;
	private String url;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
