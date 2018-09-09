package dled.github

import io.vlingo.actors.Definition
import io.vlingo.actors.World
import java.lang.Thread.sleep

class App {
    fun run() {
        val world = World.start("playground")
        try {
            val processor = world.actorFor(
                    Definition.has(BatchProcessor::class.java, Definition.NoParameters),
                    Processor::class.java
            )

            val batcher = world.actorFor(
                    Definition.has(RxBatcher::class.java, Definition.parameters(processor)),
                    Batcher::class.java
            )

            val loader = world.actorFor(
                    Definition.has(RandomLoader::class.java, Definition.parameters(batcher)),
                    Loader::class.java
            )

            loader.load(42)

            sleep(6000)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            world.terminate()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().run()
        }
    }
}
