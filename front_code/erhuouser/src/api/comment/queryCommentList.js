import http from '@/http'

export let queryCommentList = (goodsId) => {
  return http({
    url: 'comment/list?goodsId='+goodsId,
    method: 'get',
    withCredentials: false
  })
}