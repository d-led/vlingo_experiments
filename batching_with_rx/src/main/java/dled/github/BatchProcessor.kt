package dled.github

import io.vlingo.xoom.actors.Actor

class BatchProcessor : Actor(), Processor {
    override fun process(items: Collection<Item>) {
        println("Processing: ${items}")
    }
}
