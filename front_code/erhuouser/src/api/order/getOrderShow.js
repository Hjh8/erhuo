import http from '@/http'

export let getOrderShow = (ids) => {
  return http({
    url: 'order/show',
    method: 'get',
    withCredentials: false,
    data: ids
  })
}