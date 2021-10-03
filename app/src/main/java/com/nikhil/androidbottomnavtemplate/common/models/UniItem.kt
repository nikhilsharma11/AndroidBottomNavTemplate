package com.nikhil.androidbottomnavtemplate.common.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UniItem (

    @Json(name = "hero_shot_id")
    val domains : List<String>?,

    @Json(name = "name")
    val name : String,

    @Json(name = "country")
    val country : String,

    @Json(name = "alpha_two_code")
    val alpha_two_code : String?,

    @Json(name = "state-province")
    val stateProvince : String?,

    @Json(name = "web_pages")
    val webPages : List<String>?
) : Parcelable