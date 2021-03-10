import http from '@/http'

export let queryRelease = (userId) => {
  return http({
    url: 'user/myRelease?userId=' + userId,
    method: 'get',
    withCredentials: false
  })
}