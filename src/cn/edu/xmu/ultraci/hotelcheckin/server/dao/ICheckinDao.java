package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Checkin;

/**
 * 住宿表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface ICheckinDao extends IBaseDao {

	/**
	 * 新增住宿信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createCheckin(Checkin model);

	/**
	 * 更新住宿信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateCheckin(Checkin model);

	/**
	 * 删除住宿信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteCheckin(int id);

	/**
	 * 查询所有住宿信息
	 * 
	 * @return
	 */
	public List<Checkin> retrieveAllCheckin();

	/**
	 * 根据ID查询住宿信息
	 * 
	 * @param id 要查询的ID
	 * @return
	 */
	public Checkin retrieveCheckinById(int id);
}
