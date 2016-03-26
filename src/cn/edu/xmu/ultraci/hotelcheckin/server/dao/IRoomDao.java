package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Room;

/**
 * 房间表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface IRoomDao extends IBaseDao {

	/**
	 * 新增房间信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createRoom(Room model);

	/**
	 * 更新房间信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateRoom(Room model);

	/**
	 * 删除房间信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteRoom(int id);

	/**
	 * 查询所有房间信息
	 * 
	 * @return 查询结果
	 */
	public List<Room> retrieveAllRoom();

	/**
	 * 根据ID查询房间信息
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Room retrieveRoomById(int id);
}
