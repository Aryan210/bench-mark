package io.redgreen.benchpress.loginImagePicker.login.api

import io.reactivex.Single
import io.redgreen.benchpress.loginImagePicker.login.ApiService
import io.redgreen.benchpress.loginImagePicker.login.LoginRequest
import io.redgreen.benchpress.loginImagePicker.login.LoginResponse
import java.util.concurrent.TimeUnit

class ApiServiceImpl : ApiService {

    override fun login(request: LoginRequest): Single<LoginResponse> {
        return if (System.currentTimeMillis() % 2 == 0L) {
            Single
                .just(LoginResponse("token"))
                .delay(300, TimeUnit.MILLISECONDS)
        } else {
            Single.error {
                throw Error("Timeout")
            }
        }
    }
}