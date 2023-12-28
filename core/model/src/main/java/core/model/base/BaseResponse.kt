package core.model.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
        @field:SerializedName("responseCode")
        val code: String? = "",

        @field:SerializedName("message_en")
        val messageEn: String? = "",

        @field:SerializedName("message_id")
        val messageId: String? = "",

        @field:SerializedName("responseMessage")
        val message: String? = "",

        @field:SerializedName("responseData")
        val data: T? = null,

)
