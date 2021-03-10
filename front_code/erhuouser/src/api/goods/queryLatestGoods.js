import http from '@/http'

export let queryLatestGoods = (page) => {
  return http({
    url: 'goods/queryLatestGoods?page=' + page,
    method: 'get',
    withCredentials: false
  })
}