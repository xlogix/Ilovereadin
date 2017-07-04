package com.notadeveloper.app.ilovereadin

import com.squareup.moshi.Json


/**
 * Created by krsnv on 03-Jul-17.
 */
data class UserModel(

    @Json(name = "user_type")
    var userType: String = "",
    @Json(name = "member_data_id")
    var memberDataId: String = "",
    @Json(name = "city")
    var city: String = "",
    @Json(name = "name")
    var name: String = "",
    @Json(name = "dob")
    var dob: String = "",
    @Json(name = "gender")
    var gender: String = "",
    @Json(name = "address_1")
    var address1: String = "",
    @Json(name = "pincode")
    var pincode: String = "",
    @Json(name = "mobile_no")
    var mobileNo: String = "",
    @Json(name = "membership_plan_id")
    var membershipPlanId: String = "",
    @Json(name = "membership_status_flag")
    var membershipStatusFlag: String = "",
    @Json(name = "expiry_date")
    var expiryDate: String = ""
)

data class Result(@Json(name = "success") val success: Int, @Json(name = "error") val error: String,
    @Json(name = "member") var member: UserModel)