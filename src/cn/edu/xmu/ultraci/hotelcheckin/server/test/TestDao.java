package cn.edu.xmu.ultraci.hotelcheckin.server.test;

import java.math.BigInteger;
import java.sql.SQLException;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ICheckinDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.impl.BaseDaoImpl;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.CheckinPO;

public class TestDao {

	public static void test1() {
		CheckinPO model1 = new CheckinPO();
		model1.setRoom(1);
		model1.setMember(1);
		model1.setStay(1);
		model1.setCheckin("2016-03-25");
		model1.setCheckout("2016-03-27");

		CheckinPO model2 = new CheckinPO();
		model2.setRoom(2);
		model2.setGuest(2);
		model2.setStay(0);
		model2.setCheckin("2016-03-21");
		model2.setCheckout("2016-03-22");

		CheckinPO model3 = new CheckinPO();
		model3.setId(3);
		model3.setRoom(1);
		model3.setMember(1);
		model3.setStay(1);
		model3.setCheckin("2016-03-25");
		model3.setCheckout("2016-03-28");

		ICheckinDao dao = (ICheckinDao) BaseFactory.getInstance(ICheckinDao.class);
		// 插入2行
		System.out.println(dao.createCheckin(model1));
		System.out.println(dao.createCheckin(model2));
		// 查询全部
		System.out.println(dao.retrieveAllCheckin());
		// 修改1行删除1行
		System.out.println(dao.updateCheckin(model3));
		System.out.println(dao.deleteCheckin(1));
		// 分别查询
		System.out.println(dao.retrieveCheckinById(1));
		System.out.println(dao.retrieveCheckinById(2));
	}

	public static void main(String[] args) throws SQLException {
		BaseDaoImpl dao = new BaseDaoImpl();
		BigInteger result = dao.executeInsert(
				"INSERT INTO tbl_log (time, device, content) VALUES (?, ?, ?);", "2000-01-01",
				"123456", "这是日志");
		System.out.println(result);
	}
}
