package core.data.base

import core.common.di.IOThread
import core.data.datasource.local.CacheManager
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 26/06/2023.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
abstract class BaseRepository {

    @Inject
    lateinit var cacheManager: CacheManager

    @Inject
    @IOThread
    lateinit var ioDispatcher: CoroutineDispatcher
}