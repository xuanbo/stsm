// 引用模板
import index from '../components/index'
import notFound from '../components/notFound'

// team
import teamIndex from '../components/team/index'
import teamUser from '../components/team/user'

// process
import processIndex from '../components/process/index'
import processStart from '../components/process/start'

// task
import taskShow from '../components/task/show'
import taskConfirm from '../components/task/confirm'

// 配置路由
export default [{
  path: '/',
  component: index
}, {
  path: '/team',
  component: teamIndex
}, {
  path: '/team/:id',
  component: teamUser
}, {
  path: '/process',
  component: processIndex
}, {
  path: '/process/:id/start',
  component: processStart
}, {
  path: '/task/:id',
  component: taskShow
}, {
  path: '/task/:id/confirm',
  component: taskConfirm
}, {
  path: '*',
  component: notFound
}]
