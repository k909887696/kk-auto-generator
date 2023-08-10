import Layout from '@/layout'
/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    // redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '代码生成器', icon: 'el-icon-dish' }
    }]
  },
  {
    path: '/kkautogenerator',
    component: Layout,
    name: 'kkautogenerator',
    meta: { title: '系统设置', icon: 'el-icon-setting' },
    children: [
      {
        path: 'db_connect_list',
        name: 'kkautogenerator_db_connect_list',
        component: () => import('@/views/kkautogenerator/db_connect_list'),
        meta: { title: '数据库链接', icon: 'el-icon-connection', menus: 'menu_kkautogenerator_db_connect_list', check_permission: false }
      },
      {
        path: 'code_template_group_list',
        name: 'kkautogenerator_code_template_group_list',
        component: () => import('@/views/kkautogenerator/code_template_group_list'),
        meta: { title: '代码模板分组', icon: 'el-icon-folder', menus: 'menu_kkautogenerator_code_template_group_list', check_permission: false }
      },
      {
        path: 'code_template_list',
        name: 'kkautogenerator_code_template_list',
        component: () => import('@/views/kkautogenerator/code_template_list'),
        meta: { title: '代码模板', icon: 'el-icon-document', menus: 'menu_kkautogenerator_code_template_list', check_permission: false }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

function filterAsyncMenus(routes) {
  const res = []
  routes.forEach(route => {
    const tmp = { ...route }
    if (tmp.meta) {
      if (tmp.meta.menus && tmp.meta.menus !== '') {
        // console.log('generateMenus-forEach:' + tmp.meta.menus)
        res.push(tmp.meta.menus)
      }
    }
    if (tmp.children) {
      const tmpmenus = filterAsyncMenus(tmp.children)
      if (tmpmenus && tmpmenus.length > 0) {
        tmpmenus.forEach(menu => {
          res.push(menu)
        })
      }
    }
  })
  return res
}

function generateMenus() {
  const res = filterAsyncMenus(constantRoutes)
  // console.log('generateMenus' + JSON.stringify(res))
  return res
}

const allmenus = generateMenus()

const rootrouters = {
  allmenus,
  constantRoutes
}

export default rootrouters
