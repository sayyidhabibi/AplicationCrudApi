package com.example.aplicationcrudapi.model.action

import com.google.gson.annotations.SerializedName

data class ResponseData(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)
