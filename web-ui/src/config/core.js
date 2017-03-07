function auth (Vue) {
  Vue.http.get('/user/me').then(resp => {
    if (resp.data.code === 200) {
      setUser(resp.data.data)
    }
  }, resp => {
    console.log(resp.data)
    window.location.assign('http://localhost:9090')
  })
}

const userKey = 'user'

function setUser (data) {
  sessionStorage.setItem(userKey, JSON.stringify(data))
}

function getUser () {
  return JSON.parse(sessionStorage.getItem(userKey))
}

export default {
  auth: auth,
  getUser: getUser
}
