package core.model.data.response

import com.google.gson.annotations.SerializedName

data class TokenRes(

	@field:SerializedName("expiresIn")
	val expiresIn: String? = null,

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("tokenType")
	val tokenType: String? = null
)
