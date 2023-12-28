package core.domain.usecase

/**
 * Created by Wildan Nafian on 07/10/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */
interface ProfileUseCase {

    fun doLogout()
    fun getProfileData(): String

}