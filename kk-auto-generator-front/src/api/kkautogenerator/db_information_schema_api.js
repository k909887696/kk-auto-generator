/**
* 数据库系统信息 api接口
*
* @author kk
* @since 2023-07-20
*/
import request from '@/utils/request4Quantization'
import config from '@/config'

// 测试数据链接
export function test_connect(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + '/kkautogenerator/api/v1/db_information_schema/test_connect',
    method: 'post',
    data: params,
    isLoading
  })
}
// 查询数据表信息
export function get_tables_info(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + '/kkautogenerator/api/v1/db_information_schema/get_tables_info',
    method: 'post',
    data: params,
    isLoading
  })
}
// 查询数据表字段信息
export function get_columns_info(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + '/kkautogenerator/api/v1/db_information_schema/get_columns_info',
    method: 'post',
    data: params,
    isLoading
  })
}
// 生成代码
export function generate_code(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + '/kkautogenerator/api/v1/db_information_schema/generate_code',
    method: 'post',
    data: params,
    isLoading
  })
}

