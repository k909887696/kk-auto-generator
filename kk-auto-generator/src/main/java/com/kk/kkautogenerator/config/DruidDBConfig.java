 package com.kk.kkautogenerator.config;
 
 import com.alibaba.druid.pool.DruidDataSource;
 import com.kk.kkautogenerator.utils.DynamicDataSource;
 import java.sql.SQLException;
 import java.util.HashMap;
 import java.util.Map;
 import javax.sql.DataSource;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.Primary;
 import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
 import org.springframework.transaction.annotation.EnableTransactionManagement;
 
 
 
 
 @Configuration
 @EnableTransactionManagement
 public class DruidDBConfig
 {
   @Value("${spring.datasource.url}")
   private String dbUrl;
   @Value("${spring.datasource.username}")
   private String username;
   @Value("${spring.datasource.password}")
   private String password;
   @Value("${spring.datasource.driver-class-name}")
   private String driverClassName;
   @Value("${spring.datasource.initialSize:5}")
   private int initialSize;
   @Value("${spring.datasource.minIdle:5}")
   private int minIdle;
   @Value("${spring.datasource.maxActive:10}")
   private int maxActive;
   @Value("${spring.datasource.maxWait:60000}")
   private int maxWait;
   
   @Bean
   @Primary
   @Qualifier("defaultDataSource")
   public DataSource dataSource() throws SQLException {
     DruidDataSource datasource = new DruidDataSource();
     
     datasource.setUrl(this.dbUrl);
     datasource.setUsername(this.username);
     datasource.setPassword(this.password);
     datasource.setDriverClassName(this.driverClassName);
     
     datasource.setInitialSize(this.initialSize);
     datasource.setMinIdle(this.minIdle);
     datasource.setMaxActive(this.maxActive);
     datasource.setMaxWait(this.maxWait);
     
     datasource.setPoolPreparedStatements(true);
     datasource.setMaxPoolPreparedStatementPerConnectionSize(50);
     datasource.setConnectionProperties("oracle.net.CONNECT_TIMEOUT=6000;oracle.jdbc.ReadTimeout=60000");
     datasource.setTestOnBorrow(true);
     datasource.setTestWhileIdle(true);
     String validationQuery = "select 1 from dual";
     datasource.setValidationQuery(validationQuery);
     datasource.setFilters("stat,wall");
     datasource.setTimeBetweenEvictionRunsMillis(60000L);
     datasource.setMinEvictableIdleTimeMillis(180000L);
     datasource.setKeepAlive(true);
     
     datasource.setRemoveAbandoned(true);
     datasource.setRemoveAbandonedTimeout(3600);
     datasource.setLogAbandoned(true);
     return (DataSource)datasource;
   }
   
   @Bean(name = {"dynamicDataSource"})
   @Qualifier("dynamicDataSource")
   public DynamicDataSource dynamicDataSource() throws SQLException {
     DynamicDataSource dynamicDataSource = new DynamicDataSource();
     dynamicDataSource.setDebug(false);
     dynamicDataSource.setDefaultTargetDataSource(dataSource());
     Map<Object, Object> targetDataSources = new HashMap<>();
     targetDataSources.put("defaultDataSource", dataSource());
     dynamicDataSource.setTargetDataSources(targetDataSources);
     return dynamicDataSource;
   }
   
   @Bean(name = {"dynamicJdbcTemplate"})
   @Qualifier("dynamicJdbcTemplate")
   public NamedParameterJdbcTemplate dynamicJdbcTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
     return new NamedParameterJdbcTemplate(dataSource);
   }
   
   @Bean(name = {"kkJdbcTemplate"})
   @Qualifier("kkJdbcTemplate")
   @Primary
   public NamedParameterJdbcTemplate adiJdbcTemplate(@Qualifier("defaultDataSource") DataSource dataSource) {
     return new NamedParameterJdbcTemplate(dataSource);
   }
 }


