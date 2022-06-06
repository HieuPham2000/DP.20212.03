package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

// singleton pattern: chỉ tạo 1 instance logger
// Để quản lý tập trung việc log vào file, đảm bảo config chung, tránh dụng độ...
// subteam1 + subteam2
// 9.5.2022
public class Utils {

	// COUPING-COHESION subteam 2: 2 biến DATE_FORMATTER và LOGGER không được sử dụng trong class -> co-incidental cohesion
	public static DateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

}