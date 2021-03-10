import http from '@/http'

export let removeRelease = (goodsId) => {
  return http({
    url: 'user/removeRelease?goodsId=' + goodsId,
    method: 'delete',
    withCredentials: false
  })
}