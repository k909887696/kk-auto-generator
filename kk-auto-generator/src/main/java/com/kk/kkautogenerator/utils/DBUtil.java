package com.kk.kkautogenerator.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtil {
    private static final Logger log = LoggerFactory.getLogger(DBUtil.class);


    private DatabaseType databaseType = null;

    private String username;
    private String password;
    private String url;
    public static final String mysqldriver = "com.mysql.cj.jdbc.Driver";
    public static final String oracledriver = "oracle.jdbc.OracleDriver";
    public static final String sql2005driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String sql2000driver = "net.sourceforge.jtds.jdbc.Driver";

    public DBUtil(DatabaseType databaseType, String username, String password, String url) {
        this.databaseType = databaseType;
        this.username = username;
        this.password = password;
        this.url = url;
        forName();
    }

    private void forName() {
        try {
            if (null == this.databaseType) {
                throw new RuntimeException("没有指定数据库类型");
            }
            if (this.databaseType == DatabaseType.MYSQL) {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            } else if (this.databaseType == DatabaseType.ORACLE) {
                Class.forName("oracle.jdbc.OracleDriver").newInstance();
            } else if (this.databaseType == DatabaseType.SQLSERVER2000) {
                Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            } else if (this.databaseType == DatabaseType.SQLSERVER) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("加载数据库驱动失败");
        }
    }


    public Connection testConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

        return conn;
    }

    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(this.url, this.username, this.password);

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean connIsOk(Connection conn) throws SQLException {
        if (null != conn && !conn.isClosed()) {
            return true;
        }
        return false;
    }

    public void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public enum DatabaseType {
        MYSQL, ORACLE, SQLSERVER2000, SQLSERVER;
    }
}


