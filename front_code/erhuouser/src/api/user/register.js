import http from '@/http'

export let register = (body) => {
  return http({
    url: 'user/register',
    method: 'post',
    withCredentials: false,
    data: body
  })
}