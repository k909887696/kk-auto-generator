/**
* ${table.comment!} api接口
*
* @author ${author}
* @since ${date}
*/
import request from '@/utils/request4View'
import config from '@/config'

// 查询${table.comment!}列表分页
export function get${entity}PageList(params, isLoading) {
  return request({
    url: config.${baseNameSpace.moduleName}_api_url + '<#if baseNameSpace.moduleName?? && baseNameSpace.moduleName != "">/${baseNameSpace.moduleName}</#if>/${baseNameSpace.apiPrefixLogin}/${baseNameSpace.controllerVNamePrefix?cap_first}${entity}/get${entity}PageList',
    method: 'post',
    data: params,
    isLoading
  })
}
// 删除${table.comment!}
export function delete${entity}ById(params, isLoading) {
  return request({
      url: config.${baseNameSpace.moduleName}_api_url + '<#if baseNameSpace.moduleName?? && baseNameSpace.moduleName != "">/${baseNameSpace.moduleName}</#if>/${baseNameSpace.apiPrefixLogin}/${baseNameSpace.controllerVNamePrefix?cap_first}${entity}/delete${entity}ById',
    method: 'post',
    data: params,
    isLoading
  })
}
// 插入${table.comment!}
export function insert${entity}(params, isLoading) {
  return request({
    url: config.${baseNameSpace.moduleName}_api_url + '<#if baseNameSpace.moduleName?? && baseNameSpace.moduleName != "">/${baseNameSpace.moduleName}</#if>/${baseNameSpace.apiPrefixLogin}/${baseNameSpace.controllerVNamePrefix?cap_first}${entity}/insert${entity}',
    method: 'post',
    data: params,
    isLoading
  })
}
// 更新${table.comment!}
export function update${entity}(params, isLoading) {
  return request({
    url: config.${baseNameSpace.moduleName}_api_url + '<#if baseNameSpace.moduleName?? && baseNameSpace.moduleName != "">/${baseNameSpace.moduleName}</#if>/${baseNameSpace.apiPrefixLogin}/${baseNameSpace.controllerVNamePrefix?cap_first}${entity}/update${entity}',
    method: 'post',
    data: params,
    isLoading
  })
}
// 获取${table.comment!}详情
export function get${entity}Details(params, isLoading) {
  return request({
    url: config.${baseNameSpace.moduleName}_api_url + '<#if baseNameSpace.moduleName?? && baseNameSpace.moduleName != "">/${baseNameSpace.moduleName}</#if>/${baseNameSpace.apiPrefixLogin}/${baseNameSpace.controllerVNamePrefix?cap_first}${entity}/get${entity}Details',
    method: 'post',
    data: params,
    isLoading
  })
}
