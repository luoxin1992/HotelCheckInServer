package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IStatusDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Status;

/**
 * 房态表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class StatusDaoImpl extends BaseDaoImpl implements IStatusDao {
	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createStatus(Status model) {
		try {
			if (super.executeUpdate("INSERT INTO tbl_status(name, description) VALUES(?, ?)",
					model.getName(), model.getDescription()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateStatus(Status model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_status SET name = ?, description = ? WHERE id = ? AND deleted = 0",
					model.getName(), model.getDescription(), model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteStatus(int id) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_status SET deleted = 1 WHERE id = ? AND deleted = 0", id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Status> retrieveAllStatus() {
		try {
			return super.queryMultiRow(Status.class, "SELECT * FROM tbl_status WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Status retrieveStatusById(int id) {
		try {
			return super.querySingleRow(Status.class,
					"SELECT * FROM tbl_status WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
