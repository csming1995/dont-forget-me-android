package com.csming.dontforgetme.repository

import io.reactivex.Observer

/**
 * @author Created by csming on 2018/10/4.
 */
interface RegisterRepository {

    fun register(tel: String, phoneNum: String, password: String, observer: Observer<String?>)
}