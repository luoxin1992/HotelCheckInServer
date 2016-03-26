package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IFloorDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Floor;

/**
 * 楼层表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class FloorDaoImpl extends BaseDaoImpl implements IFloorDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createFloor(Floor model) {
		try {
			if (super.executeUpdate("INSERT INTO tbl_floor(name, description) VALUES(?, ?)",
					model.getName(), model.getDescription()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateFloor(Floor model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_floor SET name = ?, description = ? WHERE id = ? AND deleted = 0",
					model.getName(), model.getDescription(), model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteFloor(int id) {
		try {
			if (super.executeUpdate("UPDATE tbl_floor SET deleted = 1 WHERE id = ? AND deleted = 0",
					id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Floor> retrieveAllFloor() {
		try {
			return super.queryMultiRow(Floor.class, "SELECT * FROM tbl_floor WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Floor retrieveFloorById(int id) {
		try {
			return super.querySingleRow(Floor.class,
					"SELECT * FROM tbl_floor WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
