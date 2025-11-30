package com.neosoft.localStorage.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey val userId: String,
    val emailId: String,
    val mobileNo: String,
    val countryCode: String,
    val token: String,
    val refreshToken: String,
    val gender: String,
    val about: String? = null

)
