package cn.edu.xmu.ultraci.hotelcheckin.server.dto;

import java.io.Serializable;

/**
 * 对”登出“请求的响应
 * 
 * @author LuoXin
 *
 */
public class Logout extends Base implements Serializable {

	private static final long serialVersionUID = 4341211856729970178L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
