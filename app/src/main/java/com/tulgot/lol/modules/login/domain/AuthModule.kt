package com.tulgot.lol.modules.login.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthProvider(impl: FirebaseAuthProvider): AuthProvider

//    @Provides
//    fun provideAuthProvider(): AuthProvider {
//        return FirebaseAuthProvider()
//    }


}