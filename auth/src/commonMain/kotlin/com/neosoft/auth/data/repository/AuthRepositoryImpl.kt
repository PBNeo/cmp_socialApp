package com.neosoft.auth.data.repository
import com.neosoft.auth.data.remote.AuthApiService
import com.neosoft.auth.data.remote.dto.RegisterRequest
import com.neosoft.auth.domain.entity.User
import com.neosoft.auth.domain.repository.AuthRepository
import com.neosoft.localStorage.dao.UserInfoDao
import com.neosoft.localStorage.database.AppDatabase
import com.neosoft.localStorage.entity.UserInfo
import com.neosoft.localStorage.repository.UserRepository

class AuthRepositoryImpl(
    private val api: AuthApiService,
 //  private val userRepository: UserRepository
) : AuthRepository {

    override suspend fun registerUser(mobileNo: String, countryCode: String): User {
        println(" reached rpo")
        val response = api.registerUserMock(RegisterRequest(mobileNo, countryCode))

        val userEntity = UserInfo(
            userId = response.userId,
            mobileNo = mobileNo,
            countryCode = countryCode,
            token = response.token,
            refreshToken = response.refreshToken,
            gender = "",
            about = "",
            emailId = ""
        )

   //     userRepository.saveUser(userEntity)

        return User(
            userId = response.userId,
            mobileNo = mobileNo,
            countryCode = countryCode,
            token = response.token,
            refreshToken = response.refreshToken
        )
    }
}
