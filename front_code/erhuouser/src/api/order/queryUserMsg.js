import http from '@/http'

export let queryUserMsg = (buyerId) => {
  return http({
    url: 'order/user?buyerId='+buyerId,
    method: 'get',
    withCredentials: false
  })
}