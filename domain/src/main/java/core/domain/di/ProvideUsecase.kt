package core.domain.di

import core.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProvideUsecase {
    @Binds
    fun provideAkunUseCase(akunUseCase: ProfileUseCaseImpl): ProfileUseCase

    @Binds
    fun provideHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase

    @Binds
    fun provideLoginUseCase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun provideStartingUseCase(impl: StartingUseCaseImpl): StartingUseCase

}