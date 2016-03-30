package cn.edu.xmu.ultraci.hotelcheckin.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.constant.ErrorCode;
import cn.edu.xmu.ultraci.hotelcheckin.server.constant.LogTemplate;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IClientDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.ILogDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dao.IStaffDao;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Advertisement;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Base;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Heartbeat;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Init;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Login;
import cn.edu.xmu.ultraci.hotelcheckin.server.dto.Logout;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Client;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Log;
import cn.edu.xmu.ultraci.hotelcheckin.server.po.Staff;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IConfService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.ISystemService;
import cn.edu.xmu.ultraci.hotelcheckin.server.util.TimeUtil;

public class SystemServiceImpl implements ISystemService {

	private static Logger logger = LogManager.getLogger();

	@Override
	public Heartbeat heartbeat(Map<String, String> param) {
		String device = param.get("device");
		if (!StringUtils.isBlank(device)) {
			IClientDao clientDao = (IClientDao) BaseFactory.getInstance(IClientDao.class);
			Client client = clientDao.retrieveClientByDevice(device);
			if (client == null) {
				// 如果客户端不存在，则添加
				client = new Client();
				client.setDevice(device);
				client.setHeartbeat(TimeUtil.formatTime(System.currentTimeMillis()));
				clientDao.createClient(client);
			} else {
				// 否则只更新心跳时间
				client.setHeartbeat(TimeUtil.formatTime(System.currentTimeMillis()));
				clientDao.updateClient(client);
			}
			return new Heartbeat();
		} else {
			return (Heartbeat) new Base(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public Init init(Map<String, String> param) {
		String device = param.get("device");
		if (!StringUtils.isBlank(device)) {
			Init init = new Init();
			// 读配置文件
			IConfService confServ = (IConfService) BaseFactory.getInstance(IConfService.class);
			// 读站点根目录
			String root = confServ.getConf("root");
			// 取客户端升级
			init.getUpgrade().setVersion(Integer.parseInt(confServ.getConf("version")));
			init.getUpgrade().setUrl(confServ.getConf("download"));
			init.getUpgrade().setSize(new File(root + confServ.getConf("download")).length());
			// 取客户端公告
			init.setNotice(confServ.getConf("notice"));
			// 取客户端广告
			int adCount = Integer.parseInt(confServ.getConf("adcount"));
			List<Advertisement> adList = init.getAd();
			for (int i = 1; i <= adCount; i++) {
				String adFileName = confServ.getConf("ad" + i);
				String adFilePath = root + adFileName;
				String adFileMD5 = null;
				try {
					adFileMD5 = DigestUtils
							.md5Hex(IOUtils.toByteArray(new FileInputStream(adFileName)));
				} catch (IOException e) {
					logger.error("error while calculating MD5 of advertisement file.", e);
				}
				Advertisement ad = new Advertisement();
				ad.setUrl(adFilePath);
				ad.setMd5(adFileMD5);
				adList.add(ad);
			}
			// 记录日志
			Log log = new Log();
			log.setTime(TimeUtil.formatTime(System.currentTimeMillis()));
			log.setDevice(device);
			log.setContent(LogTemplate.INIT);
			ILogDao logDao = (ILogDao) BaseFactory.getInstance(ILogDao.class);
			logDao.createLog(log);
			return init;
		} else {
			return (Init) new Base(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public Login login(Map<String, String> param) {
		String device = param.get("device");
		String cardId = param.get("cardid");
		if (!StringUtils.isBlank(device) && StringUtils.isBlank(cardId)) {
			IStaffDao staffDao = (IStaffDao) BaseFactory.getInstance(IStaffDao.class);
			Staff staff = staffDao.retrieveStaffByCardId(cardId);
			if (staff != null) {
				if (staff.getPrivilege() == 1) {
					Login login = new Login();
					login.setName(staff.getName());
					login.setResult(ErrorCode.OK);
					return login;
				} else {
					return (Login) new Base(ErrorCode.LOGIN_OUT_FAIL_NO_PREVILEGE);
				}
			} else {
				return (Login) new Base(ErrorCode.LOGIN_OUT_FAIL_NO_SUCH_CARD);
			}
		} else {
			return (Login) new Base(ErrorCode.INVALID_REQ);
		}
	}

	public Logout logout(Map<String, String> param) {
		String device = param.get("device");
		String cardId = param.get("cardid");
		if (!StringUtils.isBlank(device) && StringUtils.isBlank(cardId)) {
			IStaffDao staffDao = (IStaffDao) BaseFactory.getInstance(IStaffDao.class);
			Staff staff = staffDao.retrieveStaffByCardId(cardId);
			if (staff != null) {
				if (staff.getPrivilege() == 1) {
					Logout logout = new Logout();
					logout.setName(staff.getName());
					logout.setResult(ErrorCode.OK);
					return logout;
				} else {
					return (Logout) new Base(ErrorCode.LOGIN_OUT_FAIL_NO_PREVILEGE);
				}
			} else {
				return (Logout) new Base(ErrorCode.LOGIN_OUT_FAIL_NO_SUCH_CARD);
			}
		} else {
			return (Logout) new Base(ErrorCode.INVALID_REQ);
		}
	}

	@Override
	public void sms(Map<String, String> param) {
		// TODO Auto-generated method stub

	}

}
