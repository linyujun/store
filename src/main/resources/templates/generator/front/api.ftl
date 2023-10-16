import request from '@/utils/request'
import { baseURL } from '@/config'
import {downloadFile} from "@/utils";

const baseUrl = "/api/${changeClassName}";

export {baseURL};

export function getOne(id) {
  return request({
    <#noparse>url: `${baseUrl}/${id}`,</#noparse>
    method: 'get',
  })
}

export function save(data) {
  return request({
    <#noparse>url: `${baseUrl}`,</#noparse>
    method: 'post',
    data
  })
}

export function disable(id) {
  return request({
<#noparse>url: `${baseUrl}/disable/${id}`,</#noparse>
  method: 'get',
  })
}

export function deleteOne(id) {
  return request({
<#noparse>url: `${baseUrl}/delete/${id}`,</#noparse>
  method: 'get',
  })
}

export function del(ids) {
  return request({
<#noparse>url: `${baseUrl}`,</#noparse>
    method: 'delete',
    data: ids
  })
}

export function getAll() {
  return request({
    <#noparse>url: `${baseUrl}/getAll`,</#noparse>
    method: 'get',
  })
}

export function getPageList(data) {
  return request({
  <#noparse>url: `${baseUrl}/getPageList`,</#noparse>
    method: 'post',
    data,
  })
}

export function getOptions() {
  return getAll().then(function (resp) {
    let lists = [];
    if (resp['success']) {
        resp.data.forEach((entity) => {
        lists.push({
          text: entity['name'],
          value: entity['id']
        })
      })
    }
    return Promise.resolve(lists)
  })
}


export function search(name) {
    return request({
      <#noparse>url: `${baseUrl}/search/${name}`,</#noparse>
      method: 'get',
    }).then(function (resp) {
      let lists = []
      if (resp['success']) {
        resp.data.forEach((task) => {
          lists.push({
            text: task['name'],
            value: task['id'],
          })
        })
      }
      return Promise.resolve(lists)
  })
}
