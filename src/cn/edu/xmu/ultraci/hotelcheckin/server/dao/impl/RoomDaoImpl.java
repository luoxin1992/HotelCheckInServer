package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IRoomDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Room;

/**
 * 房间表DAO操作类
 * 
 * @author LuoXin
 *
 */
public class RoomDaoImpl extends BaseDaoImpl implements IRoomDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createRoom(Room model) {
		try {
			if (super.executeUpdate(
					"INSERT INTO tbl_room(no, floor, type, status, description) VALUES(?, ?, ?, ?, ?)",
					model.getNo(), model.getFloor(), model.getType(), model.getStatus(),
					model.getDescription()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateRoom(Room model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_room SET no = ?, floor = ?, type = ?, status = ?, description = ? WHERE id = ? AND deleted = 0",
					model.getNo(), model.getFloor(), model.getType(), model.getStatus(),
					model.getDescription(), model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteRoom(int id) {
		try {
			if (super.executeUpdate("UPDATE tbl_room SET deleted = 1 WHERE id = ? AND deleted = 0",
					id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Room> retrieveAllRoom() {
		try {
			return super.queryMultiRow(Room.class, "SELECT * FROM tbl_room WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Room retrieveRoomById(int id) {
		try {
			return super.querySingleRow(Room.class,
					"SELECT * FROM tbl_room WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
