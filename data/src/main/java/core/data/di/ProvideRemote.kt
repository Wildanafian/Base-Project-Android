package core.data.di

import core.data.datasource.remote.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProvideRemote {

    @Binds
    fun provideAuthRemote(authentication: AuthenticationRemoteImpl): AuthenticationRemote

    @Binds
    fun provideTokenRemote(tokenRemote: TokenRemoteImpl): TokenRemote

}