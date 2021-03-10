import http from '@/http'

export let add = (body) => {
  // let params = new URLSearchParams()
  // params.append('body', body)
  return http({
    url: 'card/add',
    method: 'post',
    withCredentials: false,
    data: body
  })
}