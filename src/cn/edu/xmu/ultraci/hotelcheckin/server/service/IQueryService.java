package cn.edu.xmu.ultraci.hotelcheckin.server.service;

/**
 * 查询类服务接口<br>
 * 包括：查询会员、房型、楼层、房态和房间信息，以及酒店综合信息
 * 
 * @author LuoXin
 *
 */
public interface IQueryService {

	public void doMember();

	public void doType();

	public void doFloor();

	public void doStatus();

	public void doRoom();

	public void doInfo();
}
