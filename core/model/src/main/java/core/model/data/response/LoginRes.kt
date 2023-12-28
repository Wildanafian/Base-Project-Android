package core.model.data.response

import com.google.gson.annotations.SerializedName

data class LoginRes(

        @field:SerializedName("username")
        val username: String? = null,

        @field:SerializedName("password")
        val password: String? = null,
)
