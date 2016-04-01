package cn.edu.xmu.ultraci.hotelcheckin.server.constant;

public class LogTemplate {
	// 鉴权失败、无效请求
	public static final String AUTH_FAIL = "request from %s authenticate failure.";
	public static final String INVALID_REQ = "request with content %s process failure.";
	// 心跳、初始化
	public static final String HEARTBEAT = "receive heartbeat from %s.";
	public static final String INIT = "client %s initialize success.";
	// 登录登出
	public static final String LOGIN_OK = "client %s login success by card %s.";
	public static final String LOGIN_NO_SUCH_CARD = "client %s login failure by card %s, permission denied.";
	public static final String LOGIN_NO_PREMISSION = "client %s login failure by card %s, card unregistered.";
	public static final String LOGOUT_OK = "client %s logout success by card %s.";
	public static final String LOGOUT_NO_SUCH_CARD = "client %s logout failure by card %s, permission denied.";
	public static final String LOGOUT_NO_PREMISSION = "client %s logout failure by card %s, card unregistered.";
	// 查询
	public static final String QUERY_MEMBER_OK = "client %s query member info of card %s success.";
	public static final String QUERY_MEMBER_FAIL_NO_SUCH_CARD = "client %s query member info of card %s failure, card unregistered.";
	public static final String QUERY_TYPE_OK = "client %s query type info success, total %d types.";
	public static final String QUERY_FLOOR_OK = "client %s query floor info success, total %d floors.";
	public static final String STATUS = "";
	public static final String ROOM = "";
	public static final String INFO = "";
	// 异常
	public static final String SQL_EXCP = "error while doing database operation.";
	public static final String IO_EXCP_MD5 = "error while calculating MD5.";
	// 其他

}
