package core.data.base

import core.network.base.ApiInterface
import core.network.helper.RemoteHelper
import core.network.helper.RemoteHelperImpl
import javax.inject.Inject

/**
 * Created by Wildan Nafian on 19/11/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
abstract class BaseRemote : RemoteHelper by RemoteHelperImpl() {

    @Inject
    lateinit var api: ApiInterface

    val errorAuth = "401"

}