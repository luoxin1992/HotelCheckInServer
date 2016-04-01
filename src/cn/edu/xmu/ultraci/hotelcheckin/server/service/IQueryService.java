package cn.edu.xmu.ultraci.hotelcheckin.server.service;

import java.util.Map;

import cn.edu.xmu.ultraci.hotelcheckin.server.dto.FloorDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.InfoDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.MemberDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.RoomDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.StatusDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.TypeDTO;

/**
 * 查询类服务接口<br>
 * 包括：查询会员、房型、楼层、房态和房间信息，以及酒店综合信息
 * 
 * @author LuoXin
 *
 */
public interface IQueryService {

	/**
	 * 查询会员信息
	 * 
	 * @param params 参数集
	 * @return 查询结果
	 */
	public MemberDTO queryMember(Map<String, String> params);

	/**
	 * 查询房型信息
	 * 
	 * @param params 参数集
	 * @return 查询结果
	 */
	public TypeDTO queryType(Map<String, String> params);

	/**
	 * 查询楼层信息
	 * 
	 * @param params 参数集
	 * @return 查询结果
	 */
	public FloorDTO queryFloor(Map<String, String> params);

	/**
	 * 查询房态信息
	 * 
	 * @param params 参数集
	 * @return 查询结果
	 */
	public StatusDTO queryStatus(Map<String, String> params);

	/**
	 * 查询房间信息
	 * 
	 * @param params 参数集
	 * @return 查询结果
	 */
	public RoomDTO queryRoom(Map<String, String> params);

	/**
	 * 查询其他信息
	 * 
	 * @param params 参数集
	 * @return 查询结果
	 */
	public InfoDTO queryInfo(Map<String, String> params);
}
