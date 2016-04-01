package cn.edu.xmu.ultraci.hotelcheckin.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.constant.ErrorCode;
import cn.edu.xmu.ultraci.hotelcheckin.server.constant.LogTemplate;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IClientDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IStaffDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.BaseDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.HeartbeatDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.InitDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.LoginDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.LogoutDTO;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.ClientPO;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.StaffPO;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IConfService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.ISystemService;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.StringUtil;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.TimeUtil;

public class SystemServiceImpl implements ISystemService {

	private static Logger logger = LogManager.getLogger();

	@Override
	public HeartbeatDTO heartbeat(Map<String, String> params) {
		String device = params.get("device");
		if (!StringUtil.isBlank(device)) {
			IClientDao clientDao = (IClientDao) BaseFactory.getInstance(IClientDao.class);
			ClientPO client = clientDao.retrieveClientByDevice(device);
			if (client == null) {
				// 如果客户端不存在，则添加
				client = new ClientPO();
				client.setDevice(device);
				client.setHeartbeat(TimeUtil.formatTime(System.currentTimeMillis()));
				clientDao.createClient(client);
			} else {
				// 否则只更新心跳时间
				client.setHeartbeat(TimeUtil.formatTime(System.currentTimeMillis()));
				clientDao.updateClient(client);
			}
			logger.info(String.format(LogTemplate.HEARTBEAT, device));
			return new HeartbeatDTO();
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (HeartbeatDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public InitDTO init(Map<String, String> params) {
		String device = params.get("device");
		if (!StringUtil.isBlank(device)) {
			InitDTO init = new InitDTO();
			// 读配置文件
			IConfService confServ = (IConfService) BaseFactory.getInstance(IConfService.class);
			// 读站点根目录
			String root = confServ.getConf("root");
			// 读客户端升级
			init.getUpgrade().setVersion(Integer.parseInt(confServ.getConf("version")));
			init.getUpgrade().setUrl(confServ.getConf("download"));
			init.getUpgrade().setSize(new File(root + confServ.getConf("download")).length());
			// 计算客户端升级包MD5
			String upgradePath = root + confServ.getConf("download");
			String upgradeMd5 = null;
			try {
				upgradeMd5 = DigestUtils
						.md5Hex(IOUtils.toByteArray(new FileInputStream(upgradePath)));
			} catch (IOException e) {
				logger.error(LogTemplate.IO_EXCP_MD5, e);
			}
			init.getUpgrade().setMd5(upgradeMd5);
			// 读客户端公告
			init.setNotice(confServ.getConf("notice"));
			// 读客户端广告
			int adCount = Integer.parseInt(confServ.getConf("adcount"));
			for (int i = 1; i <= adCount; i++) {
				String adUrl = confServ.getConf("ad" + i);
				String adMd5 = null;
				String adPath = root + adUrl;
				try {
					adMd5 = DigestUtils.md5Hex(IOUtils.toByteArray(new FileInputStream(adPath)));
				} catch (IOException e) {
					logger.error(LogTemplate.IO_EXCP_MD5, e);
				}
				init.addAd(adUrl, adMd5);
			}
			logger.info(String.format(LogTemplate.INIT, device));
			return init;
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (InitDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public LoginDTO login(Map<String, String> params) {
		String device = params.get("device");
		String cardid = params.get("cardid");
		if (!StringUtil.isBlank(device) && StringUtil.isBlank(cardid)) {
			IStaffDao staffDao = (IStaffDao) BaseFactory.getInstance(IStaffDao.class);
			StaffPO staff = staffDao.retrieveStaffByCardId(cardid);
			if (staff != null) {
				if (staff.getPrivilege() == 1) {
					LoginDTO model = new LoginDTO();
					model.setName(staff.getName());

					logger.info(String.format(LogTemplate.LOGIN_OK, device, cardid));
					return model;
				} else {
					logger.warn(String.format(LogTemplate.LOGIN_NO_PREMISSION, device, cardid));
					return (LoginDTO) new BaseDTO(ErrorCode.LOGIN_OUT_NO_PREMISSION);
				}
			} else {
				logger.warn(String.format(LogTemplate.LOGIN_NO_SUCH_CARD, device, cardid));
				return (LoginDTO) new BaseDTO(ErrorCode.LOGIN_OUT_NO_SUCH_CARD);
			}
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (LoginDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	public LogoutDTO logout(Map<String, String> params) {
		String device = params.get("device");
		String cardid = params.get("cardid");
		if (!StringUtil.isBlank(device) && !StringUtil.isBlank(cardid)) {
			IStaffDao staffDao = (IStaffDao) BaseFactory.getInstance(IStaffDao.class);
			StaffPO staff = staffDao.retrieveStaffByCardId(cardid);
			if (staff != null) {
				if (staff.getPrivilege() == 1) {
					LogoutDTO logout = new LogoutDTO();
					logout.setName(staff.getName());

					logger.info(String.format(LogTemplate.LOGOUT_OK, device, cardid));
					return logout;
				} else {
					logger.warn(String.format(LogTemplate.LOGOUT_NO_PREMISSION, device, cardid));
					return (LogoutDTO) new BaseDTO(ErrorCode.LOGIN_OUT_NO_PREMISSION);
				}
			} else {
				logger.warn(String.format(LogTemplate.LOGOUT_NO_SUCH_CARD, device, cardid));
				return (LogoutDTO) new BaseDTO(ErrorCode.LOGIN_OUT_NO_SUCH_CARD);
			}
		} else {
			logger.warn(String.format(LogTemplate.INVALID_REQ, params));
			return (LogoutDTO) new BaseDTO(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public void voiceprint(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sms(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

}
