package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Floor;

/**
 * 楼层表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface IFloorDao extends IBaseDao {

	/**
	 * 新增楼层信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createFloor(Floor model);

	/**
	 * 更新楼层信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateFloor(Floor model);

	/**
	 * 删除楼层信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteFloor(int id);

	/**
	 * 查询所有楼层信息
	 * 
	 * @return 查询结果
	 */
	public List<Floor> retrieveAllFloor();

	/**
	 * 根据ID查询楼层信息
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Floor retrieveFloorById(int id);
}
