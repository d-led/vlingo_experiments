package dled.github

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import io.vlingo.actors.Actor
import java.util.concurrent.TimeUnit

class RxBatcher : Actor, Batcher {
    private val processor: Processor
    private val subject = PublishSubject.create<Item>()
    private val subscriptions = CompositeDisposable()
    private val defaultTimeoutSeconds = 3L


    constructor(processor: Processor) : super() {
        this.processor = processor

        val window = subject
                .buffer(1, TimeUnit.SECONDS, 10)
                .filter { l -> l.isNotEmpty() }

        window
                .subscribe({l ->
                    processor.process(l)
                },{e->println("interrupted: ${e.localizedMessage}")})
                .addTo(subscriptions)
    }

    override fun batch(item: Item) {
        subject.onNext(item)
    }

    override fun afterStop() {
        subscriptions.dispose()
        super.afterStop()
    }
}
