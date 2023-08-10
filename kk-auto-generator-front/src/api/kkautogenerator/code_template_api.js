/**
* 代码模板 api接口
*
* @author kk
* @since 2023-07-20
*/
import request from '@/utils/request4Quantization'
import config from '@/config'

// 查询列表分页
export function get_code_template_page_list(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + 'kkautogenerator/api/v1/code_template/get_code_template_page_list',
    method: 'post',
    data: params,
    isLoading
  })
}
export function get_code_template_group_by_type(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + 'kkautogenerator/api/v1/code_template/get_code_template_group_by_type',
    method: 'post',
    data: params,
    isLoading
  })
}

// 删除
export function deleteById(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + 'kkautogenerator/api/v1/code_template/delete_by_id',
    method: 'post',
    data: params,
    isLoading
  })
}
// 插入
export function insert(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + 'kkautogenerator/api/v1/code_template/insert',
    method: 'post',
    data: params,
    isLoading
  })
}
// 更新
export function update(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + 'kkautogenerator/api/v1/code_template/update',
    method: 'post',
    data: params,
    isLoading
  })
}
// 获取详情
export function get_details(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + '/kkautogenerator/api/v1/code_template/get_details',
    method: 'post',
    data: params,
    isLoading
  })
}
