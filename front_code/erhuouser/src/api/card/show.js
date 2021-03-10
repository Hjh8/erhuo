import http from '@/http'

export let show = (userId) => {
  return http({
    url: 'card/show?userId=' + userId,
    method: 'get',
    withCredentials: false,
  })
}