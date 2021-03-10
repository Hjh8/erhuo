import http from '@/http'

export let searchUser = (value, page) => {
  return http({
    url: 'search/user?value='+value+"&page="+page,
    method: 'get',
    withCredentials: false
  })
}