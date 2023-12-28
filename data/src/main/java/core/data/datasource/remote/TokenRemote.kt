package core.data.datasource.remote

import core.model.base.ConsumeResultRemote
import core.model.data.response.TokenRes

/**
 * Created by Wildan Nafian on 31/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
interface TokenRemote {
    suspend fun getToken(): ConsumeResultRemote<TokenRes>
}