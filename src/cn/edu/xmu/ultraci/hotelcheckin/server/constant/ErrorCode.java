package cn.edu.xmu.ultraci.hotelcheckin.server.constant;

public class ErrorCode {
	//鉴权失败
	public static final int AUTH_FAIL = -1;
	//成功
	public static final int OK = 0;
	//非法请求
	public static final int INVALID_REQ = 40001;
	//登录登出
	public static final int LOGIN_OUT_FAIL_NO_SUCH_CARD = 40101;
	public static final int LOGIN_OUT_FAIL_NO_PREMISSION = 40102;
	//查询
	public static final int QUERY_MEMBER_FAIL_NO_SUCH_CARD = 40201;
}
