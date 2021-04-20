package com.anandm.composeview.di

import com.anandm.composeview.ComposeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<ComposeApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(composeApp: ComposeApp): Builder

        fun builder(): AppComponent
    }

}