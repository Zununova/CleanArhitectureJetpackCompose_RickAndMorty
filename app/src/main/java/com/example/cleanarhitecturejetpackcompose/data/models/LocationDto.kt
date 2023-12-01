package com.example.cleanarhitecturejetpackcompose.data.models

import com.example.cleanarhitecturejetpackcompose.domain.models.Location
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun LocationDto.toDomain(): Location {
    return Location(
        name, url
    )
}