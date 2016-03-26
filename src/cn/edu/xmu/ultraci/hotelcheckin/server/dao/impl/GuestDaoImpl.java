package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IGuestDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Guest;

/**
 * 散客表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class GuestDaoImpl extends BaseDaoImpl implements IGuestDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createGuest(Guest model) {
		try {
			if (super.executeUpdate("INSERT INTO tbl_guest(mobile, idcard, time) VALUES(?, ?, ?)",
					model.getMobile(), model.getIdcard(), model.getTime()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateGuest(Guest model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_guest SET mobile = ?, idcard = ?, time = ? WHERE id = ? AND deleted = 0",
					model.getMobile(), model.getIdcard(), model.getTime(), model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteGuest(int id) {
		try {
			if (super.executeUpdate("UPDATE tbl_guest SET deleted = 1 WHERE id = ? AND deleted = 0",
					id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Guest> retrieveAllGuest() {
		try {
			return super.queryMultiRow(Guest.class, "SELECT * FROM tbl_guest WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Guest retrieveGuestById(int id) {
		try {
			return super.querySingleRow(Guest.class,
					"SELECT * FROM tbl_guest WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
