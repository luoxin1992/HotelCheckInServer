package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Status;

/**
 * 房态表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface IStatusDao {
	/**
	 * 新增房态信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createStatus(Status model);

	/**
	 * 更新房态信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateStatus(Status model);

	/**
	 * 删除房态信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteStatus(int id);

	/**
	 * 查询所有房态信息
	 * 
	 * @return 查询结果
	 */
	public List<Status> retrieveAllStatus();

	/**
	 * 根据ID查询房态信息
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Status retrieveStatusById(int id);
}
