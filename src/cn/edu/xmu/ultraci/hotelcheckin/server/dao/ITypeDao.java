package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Type;

/**
 * 房型表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface ITypeDao extends IBaseDao {

	/**
	 * 新增房型信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createType(Type model);

	/**
	 * 更新房型信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateType(Type model);

	/**
	 * 删除房型信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteType(int id);

	/**
	 * 查询所有房型信息
	 * 
	 * @return 查询结果
	 */
	public List<Type> retrieveAllType();

	/**
	 * 根据ID查询房型信息
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Type retrieveTypeById(int id);

}
