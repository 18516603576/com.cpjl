package com.baidu.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.baidu.ueditor.define.ActionMap;

/**
 * 閰嶇疆绠＄悊鍣�
 * 
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager {
	private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

	private final String rootPath;
	private final String originalPath;
	private final String contextPath;
	private static final String configFileName = "config.json";
	private String parentPath = null;
	private JSONObject jsonConfig = null;
	// 娑傞甫涓婁紶filename瀹氫箟
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 杩滅▼鍥剧墖鎶撳彇filename瀹氫箟
	private final static String REMOTE_FILE_NAME = "remote";

	/*
	 * 閫氳繃涓�涓粰瀹氱殑璺緞鏋勫缓涓�涓厤缃鐞嗗櫒锛�
	 * 璇ョ鐞嗗櫒瑕佹眰鍦板潃璺緞鎵�鍦ㄧ洰褰曚笅蹇呴』瀛樺湪config.properties鏂囦欢
	 */
	private ConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {

		rootPath = rootPath.replace("\\", "/");
		this.contextPath = contextPath;

		if (contextPath.length() > 0) {
			this.rootPath = rootPath.substring(0, rootPath.length() - contextPath.length());
		} else {
			this.rootPath = rootPath;
		}
		this.originalPath = this.rootPath + uri;
		this.initEnv();

	}

	/**
	 * 閰嶇疆绠＄悊鍣ㄦ瀯閫犲伐鍘�
	 * 
	 * @param rootPath
	 *            鏈嶅姟鍣ㄦ牴璺緞
	 * @param contextPath
	 *            鏈嶅姟鍣ㄦ墍鍦ㄩ」鐩矾寰�
	 * @param uri
	 *            褰撳墠璁块棶鐨剈ri
	 * @return 閰嶇疆绠＄悊鍣ㄥ疄渚嬫垨鑰卬ull
	 */
	public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {

		try {
			return new ConfigManager(rootPath, contextPath, uri);
		} catch (Exception e) {
			return null;
		}

	}

	// 楠岃瘉閰嶇疆鏂囦欢鍔犺浇鏄惁姝ｇ‘
	public boolean valid() {
		return this.jsonConfig != null;
	}

	public JSONObject getAllConfig() {

		return this.jsonConfig;

	}

	public Map<String, Object> getConfig(int type) throws JSONException {

		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;

		switch (type) {

		case ActionMap.UPLOAD_FILE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
			conf.put("allowFiles", this.getArray("fileAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
			savePath = this.jsonConfig.getString("filePathFormat");
			break;

		case ActionMap.UPLOAD_IMAGE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
			conf.put("allowFiles", this.getArray("imageAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
			savePath = this.jsonConfig.getString("imagePathFormat");
			break;

		case ActionMap.UPLOAD_VIDEO:
			conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
			conf.put("allowFiles", this.getArray("videoAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
			savePath = this.jsonConfig.getString("videoPathFormat");
			break;

		case ActionMap.UPLOAD_SCRAWL:
			conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
			conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
			conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
			conf.put("isBase64", "true");
			savePath = this.jsonConfig.getString("scrawlPathFormat");
			break;

		case ActionMap.CATCH_IMAGE:
			conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
			conf.put("filter", this.getArray("catcherLocalDomain"));
			conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
			conf.put("allowFiles", this.getArray("catcherAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
			savePath = this.jsonConfig.getString("catcherPathFormat");
			break;

		case ActionMap.LIST_IMAGE:
			conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
			conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
			conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
			break;

		case ActionMap.LIST_FILE:
			conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
			conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
			conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
			break;

		}

		conf.put("savePath", savePath);
		conf.put("rootPath", this.rootPath);

		return conf;

	}

	private void initEnv() throws FileNotFoundException, IOException {

		File file = new File(this.originalPath);

		if (!file.isAbsolute()) {
			file = new File(file.getAbsolutePath());
		}

		this.parentPath = file.getParent();

		String configContent = this.readFile(this.getConfigPath());

		try {
			JSONObject jsonConfig = new JSONObject(configContent);
			this.jsonConfig = jsonConfig;
		} catch (Exception e) {
			this.jsonConfig = null;
		}

	}

	private String getConfigPath() {

		String path = File.separator + ConfigManager.configFileName;

		// String path = this.parentPath + File.separator +
		// ConfigManager.configFileName;
		logger.info("config.json path————" + path);

		return path;
	}

	private String[] getArray(String key) throws JSONException {

		JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
		String[] result = new String[jsonArray.length()];

		for (int i = 0, len = jsonArray.length(); i < len; i++) {
			result[i] = jsonArray.getString(i);
		}

		return result;

	}

	private String readFile(String path) throws IOException {

		StringBuilder builder = new StringBuilder();

		try {
			InputStream is = new ClassPathResource(path).getInputStream();
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");

			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (UnsupportedEncodingException e) {
			// 蹇界暐
		}

		return this.filter(builder.toString());

	}

	// 杩囨护杈撳叆瀛楃涓�, 鍓旈櫎澶氳娉ㄩ噴浠ュ強鏇挎崲鎺夊弽鏂滄潬
	private String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}

}
