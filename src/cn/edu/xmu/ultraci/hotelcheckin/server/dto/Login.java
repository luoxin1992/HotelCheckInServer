package cn.edu.xmu.ultraci.hotelcheckin.server.dto;

import java.io.Serializable;

/**
 * 对”登录“请求的响应
 * 
 * @author LuoXin
 *
 */
public class Login extends Base implements Serializable {

	private static final long serialVersionUID = 2019169072862004103L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
