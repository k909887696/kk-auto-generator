 package com.kk.kkautogenerator.service;
 
 import com.kk.kkautogenerator.model.vo.DbDataSource;
 import com.kk.kkautogenerator.utils.DBContextHolder;
 import com.kk.kkautogenerator.utils.DynamicDataSource;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;
 
 
 
 @Service
 @Transactional
 public class SqlRepository
 {
   private static final Logger log = LoggerFactory.getLogger(SqlRepository.class);
 
   
   @Autowired
   @Qualifier("dynamicJdbcTemplate")
   private NamedParameterJdbcTemplate dynamicJdbcTemplate;
 
   
   @Resource
   private DynamicDataSource dynamicDataSource;
 

   private boolean dynamicFlag = true;
 
 
   
   public List<Map<String, Object>> doSelect(DbDataSource dataSource, String sql, Map<String, Object> params) throws Exception {
     if (dynamicFlag) {
       this.dynamicDataSource.createDataSourceWithCheck(dataSource);
       DBContextHolder.setDataSource(dataSource.getDbConnectCode().toString());
     } 
     
     log.info("sql_params:" + params);
     List<Map<String, Object>> resultList = this.dynamicJdbcTemplate.queryForList(sql, params);
     log.info("查询数据库结果doSelect-result:" + resultList.toString());
     return resultList;
   }
 
   
   public void testConnect(DbDataSource dataSource) throws Exception {
     this.dynamicDataSource.testDatasourceConnect(dataSource.getDbConnectCode().toString(), dataSource.getDbConnectType(), dataSource.getDbConnectUrl(), dataSource.getDbConnectUser(), dataSource.getDbConnectPassword());
   }
 }


