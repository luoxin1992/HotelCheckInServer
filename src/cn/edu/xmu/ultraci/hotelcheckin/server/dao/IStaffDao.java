package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Staff;

/**
 * 员工表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface IStaffDao extends IBaseDao {

	/**
	 * 新增员工信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createStaff(Staff model);

	/**
	 * 更新员工信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateStaff(Staff model);

	/**
	 * 删除员工信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteStaff(int id);

	/**
	 * 查询所有员工信息
	 * 
	 * @return 查询结果
	 */
	public List<Staff> retrieveAllStaff();

	/**
	 * 根据ID查询员工信息
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Staff retrieveStaffById(int id);
}
