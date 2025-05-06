package com.kk.kkautogenerator.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.alibaba.druid.util.StringUtils;
import com.kk.common.exception.BusinessException;
import com.kk.kkautogenerator.model.vo.DbDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource
        extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    private boolean debug = false;

    private Map<Object, Object> dynamicTargetDataSources;

    private Object dynamicDefaultTargetDataSource;

    protected Object determineCurrentLookupKey() {
        String datasource = DBContextHolder.getDataSource();
        if (this.debug) {
            if (StringUtils.isEmpty(datasource)) {
                Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
                if (dynamicTargetDataSources2.containsKey(datasource)) {
                    log.info("---当前数据源：" + datasource + "---");
                } else {
                    throw new BusinessException("不存在的数据源：" + datasource);
                }
            } else {
                log.info("---当前数据源：默认数据源---");
            }
        }
        return datasource;
    }


    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.dynamicTargetDataSources = targetDataSources;
    }

    public boolean createDataSource(String key, String driveClass, String url, String username, String password, String databasetype) {
        try {
           /* try {
                Class.forName(driveClass);
                if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(driveClass)){
                    DriverManager.getConnection(url);
                }else {
                    DriverManager.getConnection(url, username, password);
                }
            } catch (Exception e) {
                return false;
            }*/

            DruidDataSource druidDataSource = new DruidDataSource();

            druidDataSource.setName(key);
            druidDataSource.setDriverClassName(driveClass);
            druidDataSource.setUrl(url);
            druidDataSource.setUsername(username);
            druidDataSource.setPassword(password);
            druidDataSource.setInitialSize(50);
            druidDataSource.setMaxActive(200);
            druidDataSource.setMaxWait(60000L);
            druidDataSource.setMinIdle(40);
            String validationQuery = "select 1 from dual";
            if ("mysql".equalsIgnoreCase(databasetype)) {
                driveClass = "com.mysql.cj.jdbc.Driver";
                validationQuery = "select 1";
            } else if ("oracle".equalsIgnoreCase(databasetype)) {
                driveClass = "oracle.jdbc.OracleDriver";
                druidDataSource.setPoolPreparedStatements(true);
                druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(50);
                int sqlQueryTimeout = 6000;
                druidDataSource.setConnectionProperties("oracle.net.CONNECT_TIMEOUT=6000;oracle.jdbc.ReadTimeout=" + sqlQueryTimeout);
            } else if ("sqlserver2000".equalsIgnoreCase(databasetype)) {
                driveClass = "net.sourceforge.jtds.jdbc.Driver";
                validationQuery = "select 1";
            } else if ("sqlserver".equalsIgnoreCase(databasetype)) {
                driveClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                validationQuery = "select 1";
            }

            druidDataSource.setDriverClassName(driveClass);
            druidDataSource.setTestOnBorrow(true);
            druidDataSource.setTestWhileIdle(true);
            druidDataSource.setValidationQuery(validationQuery);
            druidDataSource.setFilters("stat");
            druidDataSource.setTimeBetweenEvictionRunsMillis(60000L);
            druidDataSource.setMinEvictableIdleTimeMillis(180000L);
            druidDataSource.setKeepAlive(true);

            druidDataSource.setRemoveAbandoned(true);
            druidDataSource.setRemoveAbandonedTimeout(3600);
            druidDataSource.setLogAbandoned(true);

            DruidDataSource druidDataSource1 = druidDataSource;
            druidDataSource.init();
            Map<Object, Object> dynamicTargetDataSources_temp = this.dynamicTargetDataSources;
            dynamicTargetDataSources_temp.put(key, druidDataSource1);
            setTargetDataSources(dynamicTargetDataSources_temp);
            afterPropertiesSet();
            log.info(key + "数据源初始化成功");

            return true;
        } catch (Exception e) {
            log.error(e + "");
            return false;
        }
    }

    public boolean delDatasources(String datasourceid) {
        Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
        if (dynamicTargetDataSources2.containsKey(datasourceid)) {
            Set<DruidDataSource> druidDataSourceInstances = DruidDataSourceStatManager.getDruidDataSourceInstances();
            for (DruidDataSource l : druidDataSourceInstances) {
                if (datasourceid.equals(l.getName())) {
                    dynamicTargetDataSources2.remove(datasourceid);
                    DruidDataSourceStatManager.removeDataSource(l);
                    setTargetDataSources(dynamicTargetDataSources2);
                    afterPropertiesSet();
                    return true;
                }
            }
            return false;
        }
        return false;
    }


    public boolean testDatasource(String key, String driveClass, String url, String username, String password) {
        try {
            Class.forName(driveClass);
            if("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(driveClass)){
                url=url+"user="+username+";password="+password+";";
                DriverManager.getConnection(url);
            }else {
                DriverManager.getConnection(url, username, password);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void testDatasourceConnect(String key, String databasetype, String url, String username, String password) {
        String driveClass = "";
        if ("mysql".equalsIgnoreCase(databasetype)) {
            driveClass = "com.mysql.cj.jdbc.Driver";
        } else if ("oracle".equalsIgnoreCase(databasetype)) {
            driveClass = "oracle.jdbc.OracleDriver";
        } else if ("sqlserver2000".equalsIgnoreCase(databasetype)) {
            driveClass = "net.sourceforge.jtds.jdbc.Driver";
        } else if ("sqlserver".equalsIgnoreCase(databasetype)) {
            driveClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        if (!testDatasource(key, driveClass, url, username, password)) {
            throw new BusinessException("数据源配置有错误");
        }
    }


    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        this.dynamicDefaultTargetDataSource = defaultTargetDataSource;
    }


    public void setDebug(boolean debug) {
        this.debug = debug;
    }


    public boolean isDebug() {
        return this.debug;
    }


    public Map<Object, Object> getDynamicTargetDataSources() {
        return this.dynamicTargetDataSources;
    }


    public void setDynamicTargetDataSources(Map<Object, Object> dynamicTargetDataSources) {
        this.dynamicTargetDataSources = dynamicTargetDataSources;
    }


    public Object getDynamicDefaultTargetDataSource() {
        return this.dynamicDefaultTargetDataSource;
    }


    public void setDynamicDefaultTargetDataSource(Object dynamicDefaultTargetDataSource) {
        this.dynamicDefaultTargetDataSource = dynamicDefaultTargetDataSource;
    }

    public void createDataSourceWithCheck(DbDataSource dataSource) throws Exception {
        String datasourceId = dataSource.getDbConnectCode().toString();
        log.info("准备创建数据源" + datasourceId);
        Map<Object, Object> dynamicTargetDataSources2 = this.dynamicTargetDataSources;
        if (dynamicTargetDataSources2.containsKey(datasourceId)) {
            DruidPooledConnection druidPooledConnection = null;
            log.info("数据源" + datasourceId + "之前已经创建，准备测试数据源是否正常...");

            DruidDataSource druidDataSource = (DruidDataSource) dynamicTargetDataSources2.get(datasourceId);
            boolean rightFlag = true;
            Connection connection = null;


            try {
                log.info("准备获取数据库连接...");
                druidPooledConnection = druidDataSource.getConnection();
                log.info("数据源" + datasourceId + "正常");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                rightFlag = false;
                log.info("缓存数据源" + datasourceId + "已失效，准备删除...");
                if (delDatasources(datasourceId)) {
                    log.info("缓存数据源删除成功");
                } else {
                    log.info("缓存数据源删除失败");
                }
            } finally {
                if (null != druidPooledConnection) {
                    druidPooledConnection.close();
                }
            }
            if (rightFlag) {
                log.info("不需要重新创建数据源");
                return;
            }
            log.info("准备重新创建数据源...");
            createDataSource(dataSource);
            log.info("重新创建数据源完成");
        } else {

            createDataSource(dataSource);
        }
    }


    private void createDataSource(DbDataSource dataSource) throws Exception {
        String datasourceId = dataSource.getDbConnectCode().toString();
        log.info("准备创建数据源" + datasourceId);
        String databasetype = dataSource.getDbConnectType();
        String username = dataSource.getDbConnectUser();
        String password = dataSource.getDbConnectPassword();

        String url = dataSource.getDbConnectUrl();
        String driveClass = "";
        if ("mysql".equalsIgnoreCase(databasetype)) {
            driveClass = "com.mysql.cj.jdbc.Driver";
        } else if ("oracle".equalsIgnoreCase(databasetype)) {
            driveClass = "oracle.jdbc.OracleDriver";
        } else if ("sqlserver2000".equalsIgnoreCase(databasetype)) {
            driveClass = "net.sourceforge.jtds.jdbc.Driver";
        } else if ("sqlserver".equalsIgnoreCase(databasetype)) {
            driveClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
        if (testDatasource(datasourceId, driveClass, url, username, password)) {
            boolean result = createDataSource(datasourceId, driveClass, url, username, password, databasetype);
            if (!result) {
                throw new BusinessException("数据源" + datasourceId + "配置正确，但是创建失败");
            }
        } else {
            throw new BusinessException("数据源配置有错误");
        }
    }
}

