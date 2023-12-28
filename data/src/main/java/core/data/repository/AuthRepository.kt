package core.data.repository

import core.model.base.ConsumeResultDomain
import core.model.data.request.LoginReq
import kotlinx.coroutines.flow.Flow

/**
 * Created by Wildan Nafian on 12/05/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

interface AuthRepository {
    fun doLogin(body: LoginReq): Flow<ConsumeResultDomain<Boolean>>
    fun doLogout()

    fun doClearData()
    fun checkIsLogin(): Boolean
    fun checkIsInSession(): Boolean
}