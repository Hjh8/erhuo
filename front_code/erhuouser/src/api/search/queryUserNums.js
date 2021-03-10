import http from '@/http'

export let queryUserNums = (value) => {
  return http({
    url: 'search/userNum?value='+value,
    method: 'get',
    withCredentials: false
  })
}