package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Client;

/**
 * 客户端表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface IClientDao extends IBaseDao {

	/**
	 * 新增客户端信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createClient(Client model);

	/**
	 * 更新客户端信息
	 * 
	 * @param model 要更新的内容
	 * @return 操作结果
	 */
	public boolean updateClient(Client model);

	/**
	 * 删除客户端信息
	 * 
	 * @param id 要删除的ID
	 * @return 操作结果
	 */
	public boolean deleteClient(int id);

	/**
	 * 查询所有客户端信息
	 * 
	 * @return 查询结果
	 */
	public List<Client> retrieveAllClient();

	/**
	 * 根据ID查询客户端信息
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Client retrieveClientById(int id);
}
