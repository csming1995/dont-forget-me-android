package com.csming.dontforgetme.login.repository

import rx.Observer

/**
 * @author Created by csming on 2018/10/4.
 */
interface RegisterRepository {

    fun register(tel: String, phoneNum: String, password: String, observer: Observer<String?>)
}