import http from '@/http'

export let queryOrder = (buyerId) => {
  return http({
    url: 'order/queryOrder?buyerId='+buyerId,
    method: 'get',
    withCredentials: false
  })
}