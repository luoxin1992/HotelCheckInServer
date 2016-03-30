package cn.edu.xmu.ultraci.hotelcheckin.server.service;

import java.util.Map;

import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Heartbeat;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Init;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Login;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Logout;

/**
 * 系统类服务接口<br>
 * 包括：心跳、初始化、登录登出、短信验证
 * 
 * @author LuoXin
 *
 */
public interface ISystemService {

	public Heartbeat heartbeat(Map<String, String> param);

	public Init init(Map<String, String> param);

	public Login login(Map<String, String> param);

	public Logout logout(Map<String, String> param);

	public void sms(Map<String, String> param);
}
