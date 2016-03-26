package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ICheckinDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Checkin;

/**
 * 住宿表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class CheckinDaoImpl extends BaseDaoImpl implements ICheckinDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createCheckin(Checkin model) {
		try {
			if (super.executeUpdate(
					"INSERT INTO tbl_checkin(room, member, guest, stay, checkin, checkout) VALUES(?, ?, ?, ?, ?, ?)",
					model.getRoom(), model.getMember(), model.getGuest(), model.getStay(),
					model.getCheckin(), model.getCheckout()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateCheckin(Checkin model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_checkin SET room = ?, member = ?, guest = ?, stay = ?, checkin = ?, checkout = ? WHERE id = ? AND deleted = 0",
					model.getRoom(), model.getMember(), model.getGuest(), model.getStay(),
					model.getCheckin(), model.getCheckout(), model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteCheckin(int id) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_checkin SET deleted = 1 WHERE id = ? AND deleted = 0", id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Checkin> retrieveAllCheckin() {
		try {
			return super.queryMultiRow(Checkin.class, "SELECT * FROM tbl_checkin WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Checkin retrieveCheckinById(int id) {
		try {
			return super.querySingleRow(Checkin.class,
					"SELECT * FROM tbl_checkin WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
