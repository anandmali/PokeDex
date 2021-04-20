package com.anandm.composeview.di

import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun getValue() = "Some value from main module"

}