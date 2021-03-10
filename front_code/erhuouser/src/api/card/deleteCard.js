import http from '@/http'

export let deleteCard = (id) => {
  return http({
    url: 'card/remove?id=' + id,
    method: 'delete',
    withCredentials: false,
  })
}