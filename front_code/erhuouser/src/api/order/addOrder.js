import http from '@/http'

export let addOrder = (body) => {
  // let params = new URLSearchParams()
  // params.append('body', body)
  return http({
    url: 'order/add',
    method: 'post',
    withCredentials: false,
    data: body
  })
}