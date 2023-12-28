package core.data.di

import core.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProvideRepository {

    @Binds
    fun provideAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideTokenRepository(impl: TokenRepositoryImpl): TokenRepository

    @Binds
    fun provideSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}