package core.model.data.request

import com.google.gson.annotations.SerializedName

data class TokenReq(

	@field:SerializedName("grantType")
	val grantType: String? = null
)
