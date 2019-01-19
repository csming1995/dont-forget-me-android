package com.csming.dontforgetme.main.repository.impl

import com.csming.dontforgetme.api.BookApi
import com.csming.dontforgetme.api.DailyApi
import com.csming.dontforgetme.common.model.BookModel
import com.csming.dontforgetme.common.model.RecordingModel
import com.csming.dontforgetme.common.model.UserModel
import com.csming.dontforgetme.main.repository.BookRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Created by csming on 2018/10/4.
 */
class BookRepositoryImpl @Inject constructor(
        private val bookApi: BookApi,
        private val dailyApi: DailyApi
) : BookRepository {


    override fun getBooks(token: String, observer: Observer<List<BookModel>?>) {
//        bookApi.getBooks(token)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer)

        val list = ArrayList<BookModel>(1)
        list.add(BookModel(
                owner = UserModel(
                        "",
                        "少棉",
                        "",
                        "",
                        "",
                        ""
                ),
                name = "喵子",
                cover = "",
                description = "云吸猫日记吸吸吸吸吸吸吸吸吸吸吸吸吸吸吸吸吸吸吸",
                createTime = "2019-01-17 5:31"
        ))
        observer.onNext(list)
    }

    override fun getDailies(token: String, observer: Observer<List<RecordingModel>?>) {
        dailyApi.getDailies(token)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

}