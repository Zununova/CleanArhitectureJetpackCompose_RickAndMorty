package com.example.cleanarhitecturejetpackcompose.data.models

import com.google.gson.annotations.SerializedName

data class CharacterResponseDto(
    @SerializedName("info")
    val info: InfoDto,
    @SerializedName("results")
    val results: List<ResultDto>
)