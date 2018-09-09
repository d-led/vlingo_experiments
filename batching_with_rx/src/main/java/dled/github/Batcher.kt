package dled.github

interface Batcher {
    fun batch(item: Item)
}