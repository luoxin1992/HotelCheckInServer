package cn.edu.xmu.ultraci.hotelcheckin.server.service;

/**
 * 日志记录服务
 * 
 * @author LuoXin
 *
 */
public interface ILogService {
	public <T> void logging(Class<T> clazz, String device, T model);
}
