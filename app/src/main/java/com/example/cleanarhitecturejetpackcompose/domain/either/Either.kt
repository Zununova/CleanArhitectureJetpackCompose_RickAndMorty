package com.example.cleanarhitecturejetpackcompose.domain.either

sealed class Either<out L, out R> {

    data class Success<L>(val data: L) : Either<L, Nothing>()
    data class Error<R>(val message: R) : Either<Nothing, R>()
}