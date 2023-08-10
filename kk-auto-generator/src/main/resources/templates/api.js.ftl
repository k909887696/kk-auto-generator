/**
* ${table.comment!} api接口
*
* @author ${author}
* @since ${date}
*/
import request from '@/utils/request4Quantization'
import config from '@/config'

// 查询列表分页
export function get_${table.name}_page_list(params, isLoading) {
  return request({
    url: config.${package.ModuleName}_api_url + '<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/api/v1/${table.name}/get_${table.name}_page_list',
    method: 'post',
    data: params,
    isLoading
  })
}
// 删除
export function deleteById(params, isLoading) {
  return request({
    url: config.${package.ModuleName}_api_url + '<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/api/v1/${table.name}/delete_by_id',
    method: 'post',
    data: params,
    isLoading
  })
}
// 插入
export function insert(params, isLoading) {
  return request({
    url: config.${package.ModuleName}_api_url + '<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/api/v1/${table.name}/insert',
    method: 'post',
    data: params,
    isLoading
  })
}
// 更新
export function update(params, isLoading) {
  return request({
    url: config.${package.ModuleName}_api_url + '<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/api/v1/${table.name}/update',
    method: 'post',
    data: params,
    isLoading
  })
}
// 获取详情
export function get_details(params, isLoading) {
  return request({
    url: config.${package.ModuleName}_api_url + '<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/api/v1/${table.name}/get_details',
    method: 'post',
    data: params,
    isLoading
  })
}
