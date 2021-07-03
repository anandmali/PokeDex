package com.anandm.composeview.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainModule {

    @Provides
    fun getValue() = "Some value from main module"

}