import http from '@/http'

export let queryGoodsNums = (value) => {
  return http({
    url: 'search/goodsNum?value='+value,
    method: 'get',
    withCredentials: false
  })
}