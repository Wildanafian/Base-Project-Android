package core.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.lang.ref.WeakReference
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by Wildan Nafian on 26/10/23.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object ProvideWeakContext {

    @Singleton
    @Provides
    @WeakContext
    fun weakContext(@ApplicationContext context: Context) = WeakReference(context)

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class WeakContext
}