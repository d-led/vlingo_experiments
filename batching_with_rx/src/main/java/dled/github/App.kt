package dled.github

import io.vlingo.actors.Definition
import io.vlingo.actors.World
import java.lang.Thread.sleep

class App {
    fun run() {
        val world = World.start("playground")
        try {
            val processor = world.actorFor(
                Processor::class.java,
                BatchProcessor::class.java
            )

            val batcher = world.actorFor(
                Batcher::class.java,
                Definition.has(RxBatcher::class.java, Definition.parameters(processor))
            )

            val loader = world.actorFor(
                Loader::class.java,
                Definition.has(RandomLoader::class.java, Definition.parameters(batcher))
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
