import http from '@/http'

export let searchGoods = (value, page) => {
  return http({
    url: 'search/goods?value='+value+"&page="+page,
    method: 'get',
    withCredentials: false
  })
}