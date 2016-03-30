package cn.edu.xmu.ultraci.hotelcheckin.server.dto;

import java.io.Serializable;

/**
 * 基本响应结构
 * 
 * @author LuoXin
 *
 */
public class Base implements Serializable {
	private static final long serialVersionUID = -8112172034792307819L;

	private int result;
	private long timestamp;

	public Base() {
		this.setTimestamp(System.currentTimeMillis());
	}

	public Base(int result) {
		this.setResult(result);
		this.setTimestamp(System.currentTimeMillis());
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
