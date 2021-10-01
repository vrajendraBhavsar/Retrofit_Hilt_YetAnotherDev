package com.example.retrofit_hilt.test

class CompareAnimalTest {
    val dogCompare: Compare<Dog> = object : Compare<Dog>{
        override fun compare(item1: Dog, item2: Dog): Int {
            return item1.cuteness - item2.cuteness
        }
    }

//    fun display(){
//        dogCompare.compare(Dog(20), Dog(30))
//    }
}