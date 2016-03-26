package cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IMemberDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Member;

/**
 * 会员表DAO实现类
 * 
 * @author LuoXin
 *
 */
public class MemberDaoImpl extends BaseDaoImpl implements IMemberDao {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean createMember(Member model) {
		try {
			if (super.executeUpdate(
					"INSERT INTO tbl_member(no, name, idcard, mobile, time) VALUES(?, ?, ?, ?, ?)",
					model.getNo(), model.getName(), model.getIdcard(), model.getMobile(),
					model.getTime()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean updateMember(Member model) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_member SET no = ?, name = ?, idcard = ?, mobile = ?, time = ? WHERE id = ? AND deleted = 0",
					model.getNo(), model.getName(), model.getIdcard(), model.getMobile(),
					model.getTime(), model.getId()) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public boolean deleteMember(int id) {
		try {
			if (super.executeUpdate(
					"UPDATE tbl_member SET deleted = 1 WHERE id = ? AND deleted = 0", id) > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return false;
	}

	@Override
	public List<Member> retrieveAllMember() {
		try {
			return super.queryMultiRow(Member.class, "SELECT * FROM tbl_member WHERE deleted = 0",
					(Object[]) null);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

	@Override
	public Member retrieveMemberById(int id) {
		try {
			return super.querySingleRow(Member.class,
					"SELECT * FROM tbl_member WHERE id = ? AND deleted = 0", id);
		} catch (SQLException e) {
			logger.error("error while doing database operation.", e);
		}
		return null;
	}

}
