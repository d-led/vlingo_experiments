package dled.github

import io.vlingo.actors.Actor
import java.lang.Thread.sleep
import java.util.concurrent.ThreadLocalRandom

class RandomLoader : Actor, Loader  {
    private val batcher: Batcher

    constructor(batcher: Batcher) : super() {
        this.batcher = batcher
    }

    override fun load(count: Int) {
        if (count<1)
            return

        batcher.batch(Item(count))

        sleep(ThreadLocalRandom.current().nextLong(30,100))

        super.selfAs(Loader::class.java).load(count-1)
    }

}
