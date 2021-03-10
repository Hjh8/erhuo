import http from '@/http'

export let queryGoodsDetail = (goodsId) => {
  return http({
    url: 'goods/detail?goodsId=' + goodsId,
    method: 'get',
    withCredentials: false
  })
}