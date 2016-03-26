package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ITypeDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Type;

/**
 * 房型表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class TypeDaoImpl extends BaseDaoImpl implements ITypeDao {

	private Logger logger = LogManager.getLogger();

	@Override
	public boolean createType(Type model) {
		try {
			if (super.executeUpdate(
					"INSERT INTO tbl_type(name, deposit, price, description) VALUES(?, ?, ?, ?)",
					model.getName(), model.getDeposit(), model.getPrice(),
					model.getDescription()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateType(Type model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_type SET name = ?, deposit = ?, price = ?, description = ? WHERE id = ? AND deleted = 0",
					model.getName(), model.getDeposit(), model.getPrice(), model.getDescription(),
					model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteType(int id) {
		try {
			if (super.executeUpdate("UPDATE tbl_type SET deleted = 1 WHERE id = ? AND deleted = 0",
					id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Type> retrieveAllType() {
		try {
			return super.queryMultiRow(Type.class, "SELECT * FROM tbl_type WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Type retrieveTypeById(int id) {
		try {
			return super.querySingleRow(Type.class,
					"SELECT * FROM tbl_type WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
