package io.redgreen.benchpress.loginImagePicker.login.schedulers

import io.reactivex.Scheduler

interface AppSchedulers {

    fun main() : Scheduler

    // Add for bg, network, io etc.

}
