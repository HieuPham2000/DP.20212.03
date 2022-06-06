package entity.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.sql.Connection;
import utils.*;

// singleton pattern: chỉ tạo 1 instance connection, tiết kiệm tài nguyên
// subteam1 + subteam2
// 9.5.2022
public class AIMSDB {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;
    // Lý do dùng Singleton Design Pattern ở clas AIMSDB là để tiết kiệm tài nguyên
    // Refactor getConnection method using lazy initialization with double check locking
    public static Connection getConnection() {
        if (connect == null) {
            synchronized (AIMSDB.class) {
                if (connect == null) {
                    try {
                        Class.forName("org.sqlite.JDBC");
                        String url = "jdbc:sqlite:src/main/resources/assets/db/aims.db";
                        connect = DriverManager.getConnection(url);
                        LOGGER.info("Connect database successfully");
                    } catch (Exception e) {
                         e.printStackTrace();
                    }
                }
            }
        }
        return connect;
    }


    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}
