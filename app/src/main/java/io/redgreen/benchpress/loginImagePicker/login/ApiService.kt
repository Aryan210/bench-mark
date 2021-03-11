package io.redgreen.benchpress.loginImagePicker.login

import io.reactivex.Single

interface ApiService {
    fun login(request: LoginRequest) : Single<LoginResponse>
}