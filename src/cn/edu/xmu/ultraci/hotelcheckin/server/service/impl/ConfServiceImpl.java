package cn.edu.xmu.ultraci.hotelcheckin.server.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.edu.xmu.ultraci.hotelcheckin.server.constant.LogTemplate;
import cn.edu.xmu.ultraci.hotelcheckin.server.factory.BaseFactory;
import cn.edu.xmu.ultraci.hotelcheckin.server.service.IConfService;

public class ConfServiceImpl implements IConfService {

	private static Logger logger = LogManager.getLogger();
	private static Properties prop = new Properties();

	static {
		try (FileReader fr = new FileReader(
				BaseFactory.class.getClassLoader().getResource("config.properties").getPath())) {
			prop.load(fr);
		} catch (IOException e) {
			logger.error(String.format(LogTemplate.IO_EXCP_PROP, "config.properties"), e);
		}
	}

	@Override
	public String getConf(String key) {
		return prop.getProperty(key);
	}

	@Override
	public void setConf(String key, String value) {
		prop.setProperty(key, value);
	}

}
