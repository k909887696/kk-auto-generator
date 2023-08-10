package com.kk.kkautogenerator.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.kk.common.utils.RSAUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.kkautogenerator.dao.entity.DbConnect;
import com.kk.kkautogenerator.dao.mapper.DbConnectMapper;
import com.kk.kkautogenerator.service.IDbConnectService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.kkautogenerator.model.vo.DbConnectListVo;
import com.kk.kkautogenerator.model.dto.DbConnectListDto;
import com.kk.kkautogenerator.model.vo.DbConnectAddVo;
import com.kk.kkautogenerator.model.vo.DbConnectEditVo;
import com.kk.kkautogenerator.model.dto.DbConnectDto;
import com.kk.kkautogenerator.model.vo.DbConnectDetailsVo;
import com.kk.kkautogenerator.model.vo.DbConnectDeleteVo;
import com.kk.common.utils.MapperUtils;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 数据库链接 服务实现类
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Service
public class DbConnectServiceImpl extends MppServiceImpl<DbConnectMapper, DbConnect> implements IDbConnectService {

    @Resource
    public MapperUtils mapperUtils;
    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    public void insertIgnoreBatch(List<DbConnect> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<DbConnect> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    public void insert(DbConnectAddVo vo)
    {
        if(StringUtils.isEmpty(vo.getDbConnectName()))
        {
            throw new BusinessException("db链接名称不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectDatabase()))
        {
            throw new BusinessException("db链接数据库名不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectUrl()))
        {
            throw new BusinessException("db链接地址不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectUser()))
        {
            throw new BusinessException("db链接用户不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectType()))
        {
            throw new BusinessException("db链接数据库类型不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectPassword()))
        {
            throw new BusinessException("db链接密码不能为空！");
        }

        DbConnect model = mapperUtils.map(vo,DbConnect.class);
        try {
            model.setDbConnectPassword(RSAUtil.encryptRSAStr(model.getDbConnectPassword()));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        this.baseMapper.insert(model);
    }
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    public int update(DbConnectEditVo vo)
    {
        if(StringUtils.isEmpty(vo.getDbConnectName()))
        {
            throw new BusinessException("db链接名称不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectDatabase()))
        {
            throw new BusinessException("db链接数据库名不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectUrl()))
        {
            throw new BusinessException("db链接地址不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectUser()))
        {
            throw new BusinessException("db链接用户不能为空！");
        }
        if(StringUtils.isEmpty(vo.getDbConnectType()))
        {
            throw new BusinessException("db链接数据库类型不能为空！");
        }

        DbConnect model = mapperUtils.map(vo,DbConnect.class);
        if(!StringUtils.isEmpty(vo.getDbConnectPassword())) {
            try {
                model.setDbConnectPassword(RSAUtil.encryptRSAStr(model.getDbConnectPassword()));
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("数据库链接更新失败!");
        }
        return r;
    }
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    public DbConnectDto selectById(DbConnectDetailsVo vo)
    {
        DbConnect model = mapperUtils.map(vo,DbConnect.class);
        DbConnect res = this.baseMapper.selectById(model);
        res.setDbConnectPassword("");
        DbConnectDto dto = mapperUtils.map(res,DbConnectDto.class);
        return dto;
    }

    /**
     * 获取db链接详情（密码解密，内部使用不可暴露接口）
     * @param vo
     * @return
     */
    public DbConnect selectByIdWithDecrypt(DbConnectDetailsVo vo)
    {
        DbConnect model = mapperUtils.map(vo,DbConnect.class);
        DbConnect res = this.baseMapper.selectById(model);
        try {
            res.setDbConnectPassword(RSAUtil.decryptRSAStr(res.getDbConnectPassword()));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return res;
    }
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    public int deleteById(DbConnectDeleteVo vo)
    {
        DbConnect model = mapperUtils.map(vo,DbConnect.class);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("数据库链接删除失败!");
        }
        return r;
    }
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    public PageResult<DbConnectListDto>  selectPageList(DbConnectListVo vo){

        IPage<DbConnectListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<DbConnectListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }



}
