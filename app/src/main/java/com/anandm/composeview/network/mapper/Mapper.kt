package com.anandm.composeview.network.mapper

interface Mapper<in Input, out Output> {
    fun map(domainDTO: Input): Output
}