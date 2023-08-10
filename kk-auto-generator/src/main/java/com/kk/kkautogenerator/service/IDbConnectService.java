package com.kk.kkautogenerator.service;

import com.kk.kkautogenerator.dao.entity.DbConnect;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.kkautogenerator.model.vo.DbConnectListVo;
import com.kk.kkautogenerator.model.dto.DbConnectListDto;
import com.kk.kkautogenerator.model.vo.DbConnectAddVo;
import com.kk.kkautogenerator.model.vo.DbConnectEditVo;
import com.kk.kkautogenerator.model.dto.DbConnectDto;
import com.kk.kkautogenerator.model.vo.DbConnectDetailsVo;
import com.kk.kkautogenerator.model.vo.DbConnectDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 数据库链接 服务类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
public interface IDbConnectService extends IMppService<DbConnect> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<DbConnect> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<DbConnectListDto>  selectPageList(DbConnectListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(DbConnectAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(DbConnectEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    DbConnectDto selectById(DbConnectDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(DbConnectDeleteVo vo);

    /**
     * 获取db链接详情（密码解密，内部使用不可暴露接口）
     * @param vo
     * @return
     */
    DbConnect selectByIdWithDecrypt(DbConnectDetailsVo vo);

}
