package com.cpjl.managecenter.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Desc:properties文件获取工具类 Created by hafiz.zhang on 2016/9/15.
 */
public class PropertiesUtil {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Properties props;

	// static {
	// loadProps();
	// }

	synchronized static private void loadProps() {

		Resource resource = new ClassPathResource("my.properties");

		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			props = new Properties();
			e.printStackTrace();
		}

		logger.info("config.properties文件读取完成.......");
		// props = new Properties();
		// InputStream in = null;
		// try {
		// in =
		// PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
		// // in = PropertyUtil.class.getResourceAsStream("/jdbc.properties");
		// props.load(in);
		// } catch (FileNotFoundException e) {
		// logger.error("config.properties文件未找到");
		// } catch (IOException e) {
		// logger.error("读取config.properties文件异常");
		// } finally {
		// try {
		// if (null != in) {
		// in.close();
		// }
		// } catch (IOException e) {
		// logger.error("jdbc.properties文件流关闭出现异常");
		// }
		// }
		// logger.info("加载properties文件内容完成...........");
		// logger.info("properties文件内容：" + props);
	}

	public static String getProperty(String key) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if (null == props) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}
}