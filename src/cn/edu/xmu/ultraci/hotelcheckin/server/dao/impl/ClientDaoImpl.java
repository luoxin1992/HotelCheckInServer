package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IClientDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Client;

/**
 * 客户端表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class ClientDaoImpl extends BaseDaoImpl implements IClientDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createClient(Client model) {
		try {
			if (super.executeUpdate(
					"INSERT INTO tbl_client(device, online, heartbeat) VALUES(?, ?, ?)",
					model.getDevice(), model.getOnline(), model.getHeartbeat()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateClient(Client model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_client SET device = ?, online = ?, heartbeat = ? WHERE id = ? AND deleted = 0",
					model.getDevice(), model.getOnline(), model.getHeartbeat(),
					model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteClient(int id) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_client SET deleted = 1 WHERE id = ? AND deleted = 0", id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Client> retrieveAllClient() {
		try {
			return super.queryMultiRow(Client.class, "SELECT * FROM tbl_client WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Client retrieveClientById(int id) {
		try {
			return super.querySingleRow(Client.class,
					"SELECT * FROM tbl_client WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
