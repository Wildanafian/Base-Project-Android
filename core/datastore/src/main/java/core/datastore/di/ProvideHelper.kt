package core.datastore.di

import core.datastore.helper.SharedPreferenceHelper
import core.datastore.helper.SharedPreferenceHelperImpl
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
interface ProvideHelper {

    @Binds
    fun provideSharedPreferances(sharedPref: SharedPreferenceHelperImpl): SharedPreferenceHelper

}