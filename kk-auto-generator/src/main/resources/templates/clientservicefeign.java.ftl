package ${customClientPackageName}.client;


import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ${customClientPackageName}.vointra.req.${entity}ListReqVo;
import ${customClientPackageName}.vointra.res.${entity}ListResVo;
import ${customClientPackageName}.vointra.req.${entity}AddReqVo;
import ${customClientPackageName}.vointra.req.${entity}EditReqVo;
import ${customClientPackageName}.vointra.res.${entity}ResVo;
import ${customClientPackageName}.vointra.req.${entity}DetailsReqVo;
import ${customClientPackageName}.vointra.req.${entity}DeleteReqVo;
<#list customClientServiceFeignNamespace as imp>
import ${imp};
</#list>
/**
 * <p>
 * ${table.comment!} 客户端
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Component
@FeignClient(name = "${r'${'}customClient.${package.ModuleName}${r'}'}", configuration = BaseFeignMonitorLoggerConfig.class, contextId = "meiya-core-feign-${entity}")
public interface ${entity}ClientProxy  {


    /**
    * 分页获取${table.comment!}结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @PostMapping(value = BaseWebApiFinal.PathInternalApi + CustomClientFinal.CustomSystemPath + "/Intra${entity}/select${entity}PageList")
    BasePageApiTRspVo<${entity}ListResVo>  select${entity}PageList(@RequestBody ${entity}ListReqVo vo);
    /**
    * 单条插入${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @PostMapping(value = BaseWebApiFinal.PathInternalApi + CustomClientFinal.CustomSystemPath + "/Intra${entity}/insert${entity}")
    BaseApiTRspVo<?> insert${entity}(@RequestBody ${entity}AddReqVo vo);
    /**
    * 更新${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @PostMapping(value = BaseWebApiFinal.PathInternalApi + CustomClientFinal.CustomSystemPath + "/Intra${entity}/update${entity}")
    BaseApiTRspVo<?> update${entity}(@RequestBody ${entity}EditReqVo vo);
    /**
    * 单条查询${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @PostMapping(value = BaseWebApiFinal.PathInternalApi + CustomClientFinal.CustomSystemPath + "/Intra${entity}/select${entity}ById")
    BaseApiTRspVo<${entity}ResVo> select${entity}ById(@RequestBody ${entity}DetailsReqVo vo);
    /**
    * 删除${table.comment!}
    * @param vo 请求参数
    * @return 结果集
    */
    @PostMapping(value = BaseWebApiFinal.PathInternalApi + CustomClientFinal.CustomSystemPath + "/Intra${entity}/delete${entity}ById")
    BaseApiTRspVo<?> delete${entity}ById(@RequestBody ${entity}DeleteReqVo vo);

}

