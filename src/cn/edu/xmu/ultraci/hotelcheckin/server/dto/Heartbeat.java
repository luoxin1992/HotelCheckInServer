package cn.edu.xmu.ultraci.hotelcheckin.server.dto;

import java.io.Serializable;

import cn.edu.xmu.ultraci.hotelcheckin.server.constant.ErrorCode;

/**
 * 对“心跳”请求的响应
 * 
 * @author LuoXin
 *
 */
public class Heartbeat extends Base implements Serializable {

	private static final long serialVersionUID = 4903666038453261527L;

	public Heartbeat() {
		super(ErrorCode.OK);
	}

	@Override
	public String toString() {
		return "Heartbeat [getResult()=" + getResult() + ", getTimestamp()=" + getTimestamp() + "]";
	}

}
