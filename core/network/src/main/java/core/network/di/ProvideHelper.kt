package core.network.di

import core.network.helper.NetworkChecker
import core.network.helper.NetworkCheckerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Wildan Nafian on 12/12/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
interface ProvideHelper {

    @Binds
    fun provideNetworkChecker(networkCheckerImpl: NetworkCheckerImpl): NetworkChecker

}