package cn.edu.xmu.ultraci.hotelcheckin.server.test;

import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ICheckinDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Checkin;

public class TestDao {

	public static void main(String[] args) {
		Checkin model1 = new Checkin();
		model1.setRoom(1);
		model1.setMember(1);
		model1.setStay(1);
		model1.setCheckin("2016-03-25");
		model1.setCheckout("2016-03-27");

		Checkin model2 = new Checkin();
		model2.setRoom(2);
		model2.setGuest(2);
		model2.setStay(0);
		model2.setCheckin("2016-03-21");
		model2.setCheckout("2016-03-22");

		Checkin model3 = new Checkin();
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
}
