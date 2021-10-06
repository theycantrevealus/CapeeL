package com.example.medancapilpelaporan.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(

	@field:SerializedName("response_package")
	val responsePackage: ResponsePackage,

	@field:SerializedName("token")
	val token: String? = null
)

data class ResponsePackage(

	@field:SerializedName("response_message")
	val responseMessage: String,

	@field:SerializedName("response_result")
	val responseResult: Int

)


