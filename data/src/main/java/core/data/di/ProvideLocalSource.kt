package core.data.di

import core.data.datasource.local.CacheManager
import core.data.datasource.local.CacheManagerImpl
import core.data.datasource.local.SessionManager
import core.data.datasource.local.SessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Wildan Nafian on 14/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
interface ProvideLocalSource {

    @Binds
    fun provideSessionManager(impl: SessionManagerImpl): SessionManager

    @Binds
    fun provideCacheManager(impl: CacheManagerImpl): CacheManager
}