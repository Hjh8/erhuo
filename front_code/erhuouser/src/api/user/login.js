import http from '@/http'

export let login = (body) => {
  return http({
    url: 'user/login',
    method: 'post',
    withCredentials: false,
    data: body
  })
}