package com.csming.dontforgetme.repository

import rx.Observer

/**
 * @author Created by csming on 2018/10/4.
 */
interface LoginRepository {

    fun login(name: String, password: String, observer: Observer<String?>)
}