import http from '@/http'

export let queryTotalNum = (sort) => {
  return http({
    url: 'goods/totalNum?sort=' + sort,
    method: 'get',
    withCredentials: false
  })
}