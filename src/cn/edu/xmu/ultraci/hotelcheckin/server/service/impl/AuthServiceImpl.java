package cn.edu.xmu.ultraci.hotelcheckin.server.service.impl;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IAuthService;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IConfService;

public class AuthServiceImpl implements IAuthService {

	private static Logger logger = LogManager.getLogger();

	@Override
	public boolean doAuth(String random, String signature) {
		return true;
//		// 检查参数有效性
//		IConfService confServ = ((IConfService) BaseFactory.getInstance(IConfService.class));
//		String token = confServ.getConf("token");
//		if (StringUtils.isBlank(random)) {
//			logger.warn("invalid authentication parameter 'random'.");
//			return false;
//		}
//		if (StringUtils.isBlank(signature)) {
//			logger.warn("invalid authentication parameter 'signature'.");
//			return false;
//		}
//		if (StringUtils.isBlank(token)) {
//			logger.warn("authentication parameter 'token' not set.");
//			return false;
//		}
//		// 排序和合并
//		String[] array = new String[] { token, random };
//		Arrays.sort(array);
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < array.length; i++) {
//			sb.append(array[i]);
//		}
//		// 与加密签名相比较
//		return DigestUtils.sha1Hex(sb.toString()).equals(signature);
	}

}
