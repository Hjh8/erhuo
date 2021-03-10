import http from '@/http'

export let queryGoodsList = (sort, page) => {
  return http({
    url: 'goods/list?sort=' +sort +"&page=" +page,
    method: 'get',
    withCredentials: false
  })
}