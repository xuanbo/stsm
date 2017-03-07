// 引用模板
import index from '../components/index'
import notFound from '../components/notFound'

// team
import teamIndex from '../components/team/index'
import teamUser from '../components/team/user'

// 配置路由
export default [
  {
    path: '/',
    component: index
  },
  {
    path: '/team',
    component: teamIndex
  },
  {
    path: '/team/:id',
    component: teamUser
  },
  {
    path: '*',
    component: notFound
  }
]
