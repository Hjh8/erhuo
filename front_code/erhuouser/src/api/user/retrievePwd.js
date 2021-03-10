import http from '@/http'

export let retrievePwd = (phone, newPwd) => {
  return http({
    url: 'user/retrievePwd?phone=' + phone+'&newPwd='+newPwd,
    method: 'put',
    withCredentials: false
  })
}