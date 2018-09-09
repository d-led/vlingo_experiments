package dled.github

import io.vlingo.actors.Actor

class BatchProcessor : Actor(), Processor {
    override fun process(items: Collection<Item>) {
        println("Processing: ${items}")
    }
}
