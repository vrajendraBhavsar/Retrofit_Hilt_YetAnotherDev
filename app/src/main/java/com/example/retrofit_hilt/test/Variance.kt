package com.example.retrofit_hilt.test

abstract class  Bird(val size: Int)

class canary(val cuteness: Int): Bird(100)

class Dove(val terrorFactor: Int): Bird(1)


// Covariance

val canaryList: List<canary> = listOf()
val birdList: List<Bird> = canaryList // Works!

val spiderList: List<Dove> = birdList // Compiler error, cannot assign List<Bird> to List<Dove>


// Contravariance

interface CompareBird<in T> {
    fun compareBird(first: T, second: T): Int
}

val birdCompare: CompareBird<canary> = object: CompareBird<canary> {
    override fun compareBird(first: canary, second: canary): Int {
        return first.cuteness - second.cuteness
    }
}

val animalCompare: Compare<Bird> = birdCompare // Compiler error, cannot assign List<canary> to List<Bird>

val spider: CompareBird<Dove> = object: CompareBird<Bird> { // Works!
    override fun compareBird(first: Bird, second: Bird): Int {
        return first.size - second.size
    }
}