{
  path: '${table.name}_list',
  name: '${package.ModuleName}_${table.name}_list',
  component: () => import('@/views/${package.ModuleName}/${table.name}_list'),
  meta: { title: '${table.comment}', icon: 'el-icon-date', menus: 'menu_${package.ModuleName}_${table.name}_list', check_permission: false }
}