package tw.idv.jew.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable
            .just(1, 2, 3)
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(i: Int?) {
                    println(i)
                }

                override fun onError(e: Throwable) {
                    println("Whoops：" + e.message)
                }

                override fun onComplete() {
                    println("Completed Observable.")
                }
            })

        //FromArray
        val list = listOf("John", "Marry")
        val listObservable = Observable.fromArray(list)

        val justObservable = Observable.fromArray("John", "Marry")

        //Create
        val observable = Observable.create<String> {
            try {
                it.onNext("John")
                it.onNext("Marry")
                it.onComplete()
            }catch (e: Exception){
                it.onError(e)
            }
            it.onComplete()
        }

        //Observer
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(string: String?) {
                println(string)
            }

            override fun onError(e: Throwable) {
                println("Whoops：" + e.message)
            }

            override fun onComplete() {
                println("Completed Observable.")
            }
        }

        //Subscribe with lambda
        val lObservable = Observable.just(1, 2, 3)
        lObservable.subscribe() //Without any callback
        lObservable.subscribe { //onNext
        }
        lObservable.subscribe ({ //onNext
        }, {    //onError
        })
        lObservable.subscribe ({ //onNext
        }, {    //onError
        }, {    //onComplete
        })

        //Disposable
        var disposable: Disposable? = null

        val dObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                disposable = d
            }

            override fun onNext(string: String?) {
                println(string)
            }

            override fun onError(e: Throwable) {
                println("Whoops：" + e.message)
            }

            override fun onComplete() {
                println("Completed Observable.")
            }
        }
        //Call when you don't need to observe anymore
        disposable?.dispose()

        //簡化Disposable
        val sDisposable = Observable
                .just(1, 2, 3)
                .subscribe {
                    println(it)
                }
        //Call when you don't need to observe anymore
        sDisposable?.dispose()

        //CompositeDisposable
        //init once
        val compositeDisposable = CompositeDisposable()

        for (i in 0..100){
            val disposable = Observable
                    .just(1, 2, 3)
                    .subscribe()
            compositeDisposable.add(disposable)
        }
        //Call when you don't need to observe anymore
        compositeDisposable.dispose()

        //Operator：map、filter...
        /*val observable = Observable.just(1, 2, 3)
        val mapObservable = observable.map { it * 2 }
        mapObservable.subscribe {
            println(it)
        }*/
        Observable
                .just(1, 2, 3)
                .filter { it%2 == 1 }
                .map { it * 2 }
                .subscribe {
                    println(it)
                }
    }
}