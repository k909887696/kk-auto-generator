import Cookies from 'js-cookie'
import { valid_platform_jurisdiction } from '@/api/meiya/common/login'

const TokenKey = 'kk_auto_generator_front_token_' + process.env.NODE_ENV

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function validPlatformJurisdiction(permission) {
  valid_platform_jurisdiction({ jurisdictionIds: [permission] }).then(response => {
    return response.data.jurisdictions[permission] && response.data.jurisdictions[permission] === '1'
  })
}

export function getPermissionPlatformJurisdiction(allPermissions) {
  const validRequest = { jurisdictionIds: allPermissions || [] }
  const permissions = []
  valid_platform_jurisdiction(validRequest).then(response => {
    allPermissions.forEach(p => {
      if (response.data.jurisdictions[p] && response.data.jurisdictions[p] === '1') {
        permissions.push(p)
      }
    })
  })
  // console.log(permissions)
  return permissions
}
