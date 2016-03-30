package cn.edu.xmu.ultraci.hotelcheckin.server.service;

/**
 * 房务类服务接口<br>
 * 包括：上报散客信息、选房、退房和支付结果查询
 * 
 * @author LuoXin
 *
 */
public interface IRoomService {

	public void doGuest();

	public void doCheckin();

	public void doCheckout();

	public void doPay();
}
