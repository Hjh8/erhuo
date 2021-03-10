import http from '@/http'

export let confirmOrder = (orderId, goodsId) => {
  return http({
    url: 'order/confirm?orderId='+orderId+"&goodsId="+goodsId,
    method: 'get',
    withCredentials: false
  })
}