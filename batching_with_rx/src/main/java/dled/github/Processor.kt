package dled.github

interface Processor {
    fun process(items: Collection<Item>)
}
