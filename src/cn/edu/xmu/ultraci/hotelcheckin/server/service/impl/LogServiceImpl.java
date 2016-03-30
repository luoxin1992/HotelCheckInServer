package cn.edu.xmu.ultraci.hotelcheckin.server.service.impl;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ILogDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Log;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.ILogService;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.TimeUtil;

public class LogServiceImpl implements ILogService {

	@Override
	public <T> void logging(Class<T> clazz, String device, T model) {
		// 创建日志PO
		Log log = new Log();
		log.setDevice(device);
		log.setTime(TimeUtil.formatTime(System.currentTimeMillis()));
		switch (clazz.getSimpleName()) {
		case "":
			break;
		}

		// 写入日志表
		ILogDao logDao = (ILogDao) BaseFactory.getInstance(ILogDao.class);
		logDao.createLog(log);
	}

}
