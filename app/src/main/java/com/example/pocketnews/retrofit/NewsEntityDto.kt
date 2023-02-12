package com.example.pocketnews.retrofit


data class NewsEntityDto(
    val kind: String,
    val data: Data,
)

data class Data(
    val after:String,
    val children: List<Children>
)


data class Children(
    val kind: String,
    val data: Data2
)

data class Data2(
    val title: String,
    val author: String,
    val name: String //id for paging
)


