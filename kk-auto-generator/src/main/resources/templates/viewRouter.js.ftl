{
  path: '${entity}List',
  name: '${baseNameSpace.moduleName}_${entity}List',
  component: () => import('@/views/${baseNameSpace.moduleName}/${entity}List'),
  meta: { title: '${table.comment}', icon: 'table', menus: 'v_menu_select_${entity}', check_permission: false }
}