package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IStaffDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Staff;

/**
 * 员工表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class StaffDaoImpl extends BaseDaoImpl implements IStaffDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createStaff(Staff model) {
		try {
			if (super.executeUpdate(
					"INSERT INTO tbl_staff(no, name, voiceprint, privilege, time) VALUES(?, ?, ?, ?, ?)",
					model.getNo(), model.getName(), model.getVoiceprint(), model.getPrivilege(),
					model.getTime()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateStaff(Staff model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_staff SET no = ?, name = ?, voiceprint = ?, privilege = ?, time = ? WHERE id = ? AND deleted = 0",
					model.getNo(), model.getName(), model.getVoiceprint(), model.getPrivilege(),
					model.getTime(), model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteStaff(int id) {
		try {
			if (super.executeUpdate("UPDATE tbl_staff SET deleted = 1 WHERE id = ? AND deleted = 0",
					id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Staff> retrieveAllStaff() {
		try {
			return super.queryMultiRow(Staff.class, "SELECT * FROM tbl_staff WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Staff retrieveStaffById(int id) {
		try {
			return super.querySingleRow(Staff.class,
					"SELECT * FROM tbl_staff WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Staff retrieveStaffByCardId(String cardId) {
		try {
			return super.querySingleRow(Staff.class,
					"SELECT * FROM tbl_staff WHERE no = ? AND deleted = 0", cardId);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
