import request from '@/utils/request4Quantization'
import config from '@/config'
import Axios from 'axios'

export function get_base_data_item_map(params, isLoading) {
  return request({
    url: config.kkautogenerator_api_url + '/kkautogenerator/api/v1/basedata/get_base_data_item_map',
    method: 'post',
    data: params,
    isLoading,
    isPrompt: false
  })
}

export function download_file(params, isLoading) {
  const link = document.createElement('a')
  link.style.display = 'none'
  link.href = config.kkautogenerator_api_url + '/kkautogenerator/api/v1/basedata/download_file?' + params
  document.body.appendChild(link)
  link.click()
  return link
}

