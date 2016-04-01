package cn.edu.xmu.ultraci.hotelcheckin.server.service.impl;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.constant.ErrorCode;
import cn.edu.xmu.ultraci.hotelcheckin.server.constant.LogTemplate;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ICheckinDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IGuestDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IRoomDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.BaseDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.CheckinDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.CheckoutDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.GuestDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.CheckinPO;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.GuestPO;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.RoomPO;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IConfService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IRoomService;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.StringUtil;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.TimeUtil;

public class RoomServiceImpl implements IRoomService {

	private static Logger logger = LogManager.getLogger();

	@Override
	public GuestDTO newGuest(Map<String, String> params) {
		String device = params.get("device");
		String mobile = params.get("mobile");
		String idcard = params.get("idcard");
		if (!StringUtil.isBlank(device) && !StringUtil.isBlank(mobile)
				&& !StringUtil.isBlank(idcard)) {
			IConfService confServ = (IConfService) BaseFactory.getInstance(IConfService.class);
			String path = confServ.getConf("root") + "\\upload\\idcard\\" + idcard;
			if (new File(path).exists()) {
				GuestPO guest = new GuestPO();
				guest.setMobile(mobile);
				guest.setIdcard(path);
				IGuestDao guestDao = (IGuestDao) BaseFactory.getInstance(IGuestDao.class);
				long guestId = guestDao.createGuest(guest);

				GuestDTO retModel = new GuestDTO();
				retModel.setId(guestId);
				logger.info(String.format(LogTemplate.NEW_GUEST_OK, device, guestId));
				return retModel;
			} else {
				logger.warn(String.format(LogTemplate.NEW_GUEST_FILE_NOT_FOUND, device, path));
				return (GuestDTO) new BaseDTO(ErrorCode.NEW_GUEST_FILE_NOT_FOUND);
			}
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (GuestDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public CheckinDTO checkIn(Map<String, String> params) {
		String device = params.get("device");
		String customer = params.get("customer");
		String room = params.get("room");
		String time = params.get("time");
		if (!StringUtil.isBlank(device) && !StringUtil.isBlank(customer)
				&& !StringUtil.isBlank(room) && !StringUtil.isBlank(time)
				&& customer.matches("[MG]\\d+")) {
			CheckinPO checkin = new CheckinPO();
			if (customer.startsWith("M")) {
				checkin.setMember(Integer.parseInt(customer.substring(1)));
			} else {
				checkin.setGuest(Integer.parseInt(customer.substring(1)));
			}
			checkin.setRoom(Integer.parseInt(room));
			checkin.setCheckin(TimeUtil.formatTime(System.currentTimeMillis()));
			checkin.setCheckout(time);

			ICheckinDao checkinDao = (ICheckinDao) BaseFactory.getInstance(ICheckinDao.class);
			checkinDao.createCheckin(checkin);
			logger.info(String.format(LogTemplate.CHECK_IN_OK, device, room));
			return new CheckinDTO();
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (CheckinDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public CheckoutDTO checkOut(Map<String, String> params) {
		String device = params.get("device");
		String cardid = params.get("cardid");
		if (!StringUtil.isBlank(device) && !StringUtil.isBlank(cardid)) {
			// 查询房间信息
			IRoomDao roomDao = (IRoomDao) BaseFactory.getInstance(IRoomDao.class);
			RoomPO room = roomDao.retrieveRoomByCardId(cardid);
			if (room != null) {
				// 查询入住情况
				ICheckinDao checkinDao = (ICheckinDao) BaseFactory.getInstance(ICheckinDao.class);
				CheckinPO checkin = checkinDao.retrieveCheckinByRoom(room.getId());
				if (checkin != null) {
					// 执行退房
					if (!TimeUtil.isDateAfter(checkin.getCheckout())) {
						checkin.setStay(0);
						checkin.setCheckout(TimeUtil.formatTime(System.currentTimeMillis()));
						checkinDao.updateCheckin(checkin);
						logger.info(String.format(LogTemplate.CHECK_OUT_OK, device, cardid));
						return new CheckoutDTO();
					} else {
						// 超时需补交房款
						logger.warn(String.format(LogTemplate.CHECK_OUT_NEED_PAY, device, cardid));
						return (CheckoutDTO) new BaseDTO(ErrorCode.CHECK_OUT_NEED_PAY);
					}
				} else {
					logger.warn(String.format(LogTemplate.CHECK_OUT_NO_CHECK_IN, device, cardid));
					return (CheckoutDTO) new BaseDTO(ErrorCode.CHECK_OUT_NO_CHECK_IN);
				}
			} else {
				logger.warn(String.format(LogTemplate.CHECK_OUT_NO_SUCH_CARD, device, cardid));
				return (CheckoutDTO) new BaseDTO(ErrorCode.CHECK_OUT_NO_SUCH_CARD);
			}
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (CheckoutDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public void pay(Map<String, String> params) {
		// TODO Auto-generated method stub
	}

}
