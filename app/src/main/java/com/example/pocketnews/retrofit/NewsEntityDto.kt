package com.example.pocketnews.retrofit

import android.os.Parcel
import android.os.Parcelable
import com.example.pocketnews.data.NewsEntity
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/*
@Parcelize
data class NewsEntityDto(

    @SerializedName("approved_at_utc")
    var published: String,

    @SerializedName("title")
    var title: String

) : Parcelable {

    fun convertDtoToNewsEntity() = NewsEntity(published,title)

}*/
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
    val name: String //id for pagging
)


