import http from '@/http'

export let release = (body) => {
  return http({
    url: 'goods/release',
    method: 'post',
    withCredentials: false,
    data: body
  })
}