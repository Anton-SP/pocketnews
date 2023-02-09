package com.example.pocketnews.data

import com.example.pocketnews.retrofit.Children
import com.example.pocketnews.retrofit.Data
import com.example.pocketnews.retrofit.NewsEntityDto

fun convertChildrenDataToEntity(data: Data):List<NewsEntity> {
    return data.children.map { NewsEntity(it.data.author,it.data.title) }
}
