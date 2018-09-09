package dled.github

import io.vlingo.actors.Actor
import io.vlingo.actors.Scheduled
import java.util.concurrent.ThreadLocalRandom

class RandomLoader : Actor, Loader, Scheduled {
    private val batcher: Batcher

    constructor(batcher: Batcher) : super() {
        this.batcher = batcher
    }

    override fun load(count: Int) {
        if (count < 1)
            return

        batcher.batch(Item(count))

        val delay = ThreadLocalRandom.current().nextLong(30, 100)

        scheduler().scheduleOnce(this, count - 1, 0L, delay)
    }

    override fun intervalSignal(scheduled: Scheduled?, data: Any?) {
        (scheduled as Loader).load(data as Int)
    }
}
