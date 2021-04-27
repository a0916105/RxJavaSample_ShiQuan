package tw.idv.jew.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
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
    }
}