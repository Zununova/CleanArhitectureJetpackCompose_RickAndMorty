package com.example.cleanarhitecturejetpackcompose.data.models

import com.example.cleanarhitecturejetpackcompose.domain.models.Origin
import com.google.gson.annotations.SerializedName

data class OriginDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun OriginDto.toDomain(): Origin {
    return Origin(
        name, url
    )
}