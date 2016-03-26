package cn.edu.xmu.ultraci.hotelcheckin.server.dao;

import java.util.List;

import cn.edu.xmu.ultraci.hotelcheckin.server.po.Log;

/**
 * 日志表DAO操作接口
 * 
 * @author LuoXin
 *
 */
public interface ILogDao extends IBaseDao {

	/**
	 * 新增日志信息
	 * 
	 * @param model 要新增的内容
	 * @return 操作结果
	 */
	public boolean createLog(Log model);

	/**
	 * 查询所有日志信息
	 * 
	 * @return 查询结果
	 */
	public List<Log> retrieveAllLog();

	/**
	 * 根据ID查询日志
	 * 
	 * @param id 要查询的ID
	 * @return 查询结果
	 */
	public Log retrieveLogById(int id);

}
