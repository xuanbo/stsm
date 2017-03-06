// 引用模板
import index from '../components/index'
import notFound from '../components/notFound'

// 配置路由
export default [
  {
    path: '/',
    component: index
  },
  {
    path: '*',
    component: notFound
  }
]
