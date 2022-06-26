package utils;

import java.util.logging.Logger;

// singleton pattern: chỉ tạo 1 instance logger
// Để quản lý tập trung việc log vào file, đảm bảo config chung, tránh dụng độ...
// subteam1 + subteam2
// 9.5.2022
public class Utils {

	// COUPING-COHESION subteam 2: 2 biến DATE_FORMATTER và LOGGER không được sử dụng trong class -> co-incidental cohesion
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

}