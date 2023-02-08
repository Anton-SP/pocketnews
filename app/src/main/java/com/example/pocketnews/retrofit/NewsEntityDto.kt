package com.example.pocketnews.retrofit

import android.os.Parcel
import android.os.Parcelable
import com.example.pocketnews.data.NewsEntity
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "entry",strict = false)
data class NewsEntityDto(

    @Element(name = "published")
    var published: String,

    @Element(name = "title")
    var title: String

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    fun convertDTOtoNewsEntity() = NewsEntity(published, title)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(published)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsEntityDto> {
        override fun createFromParcel(parcel: Parcel): NewsEntityDto {
            return NewsEntityDto(parcel)
        }

        override fun newArray(size: Int): Array<NewsEntityDto?> {
            return arrayOfNulls(size)
        }
    }
}
