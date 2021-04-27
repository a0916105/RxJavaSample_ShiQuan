package tw.idv.jew.rxjavasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable
            .just(1, 2, 3)
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: Int?) {
                    println(t)
                }

                override fun onError(e: Throwable) {
                    println("Whoops：" + e.message)
                }

                override fun onComplete() {
                    println("Completed Observable.")
                }
            })
    }
}