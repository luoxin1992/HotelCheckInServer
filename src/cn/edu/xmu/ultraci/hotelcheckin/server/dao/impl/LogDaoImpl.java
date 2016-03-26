package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ILogDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Log;

/**
 * 日志表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class LogDaoImpl extends BaseDaoImpl implements ILogDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createLog(Log model) {
		try {
			if (super.executeUpdate("INSERT INTO tbl_log(time, type, content) VALUES(?, ?, ?)",
					model.getTime(), model.getType(), model.getContent()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Log> retrieveAllLog() {
		try {
			return super.queryMultiRow(Log.class, "SELECT * FROM tbl_log WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Log retrieveLogById(int id) {
		try {
			return super.querySingleRow(Log.class,
					"SELECT * FROM tbl_log WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
