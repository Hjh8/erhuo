import http from '@/http'

export let addComment = (body) => {
  // let params = new URLSearchParams()
  // params.append('body', body)
  return http({
    url: 'comment/add',
    method: 'post',
    withCredentials: false,
    data: body
  })
}