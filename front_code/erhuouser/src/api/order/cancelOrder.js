import http from '@/http'

export let cancelOrder = (orderId, goodsId, nums) => {
  return http({
    url: 'order/cancel?orderId='+orderId+"&goodsId="+goodsId+"&nums="+nums,
    method: 'put',
    withCredentials: false
  })
}