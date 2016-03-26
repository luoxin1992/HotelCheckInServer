package cn.edu.xmu.ultraci.hotelcheckin.server.factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 简单工厂实现<br>
 * 根据配置文件找到接口类和实现类的对应关系<br>
 * 以单例方式返回给定接口的实现类实例
 * 
 * @author LuoXin
 *
 */
public class BaseFactory {

	private static Logger logger;
	private static Properties prop;
	private static Map<String, Object> map;

	static {
		logger = LogManager.getLogger();
		prop = new Properties();
		map = new HashMap<String, Object>();

		try (FileReader fr = new FileReader(
				BaseFactory.class.getClassLoader().getResource("factory.properties").getPath())) {
			prop.load(fr);
		} catch (IOException e) {
			logger.error("error while loading factory configuration file.", e);
		}
	}

	public static <T> Object getInstance(Class<T> clazz) {
		String implName = prop.getProperty(clazz.getSimpleName());
		if (!StringUtils.isBlank(implName)) {
			if (map.get(implName) == null) {
				try {
					Object implInst = Class.forName(implName).newInstance();
					map.put(implName, implInst);
					logger.info("instantiation a object for class " + implName);
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					logger.error("error while instancing a object for class " + implName, e);
				}
			}
			return map.get(implName);
		} else {
			logger.warn("implementation class of " + clazz.getSimpleName()
					+ " not found in factory.properties");
		}
		return null;
	}
}
