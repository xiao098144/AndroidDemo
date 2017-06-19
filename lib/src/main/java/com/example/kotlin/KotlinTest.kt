package com.example.kotlin

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Description：
 * Created on 2017/5/24
 * Author : 萧
 */

fun main(args: Array<String>) {
//    var a: Int = 2
//    var b: Int = 3
//    var c = 3
//    var s1 = " a is $a"
//    a = 3
//    val s2 = "${s1.replace("is", "was")},but now is $a"
//    println("Hello World  ${a + b + c}")
//    print("s1 = $s1 \n s2 = $s2")
//    loopTest()
//    val test = whenTest(1L)
//    print("whenTest.result is $test")
//    rangeTest(1)
//    collectionsTest("apple")

//    println(" data class Customer")
//    Customer customer = Cus
//    val (name, age, sex) = Customer("name", 26, 1)
//    var customer = Customer("name", 26, 1)
//    println(customer)

//    mapTest()

//    println(" lazy property ")
//    val name: String by lazy {
//        "lazy Value"
//    }
//    name.spaceToCamelCase()
//    println(name)

//    println(" if expression")
//    val result = ifExpression("")
//    println(" if expression result = $result")

//    println(" when expression around with try catch")
//    val result = try {
//    whenExpression("")
//    } catch (e: IllegalArgumentException) {
//        e.printStackTrace()
//        println(e.localizedMessage)
//        " go error"
//    }
//    println(" whenExpression result = $result")

//    println(" class Test ")
//    var turtle = Turtle()
//    turtle.color = 0xff0000
//    turtle.name = "turtle"
//    turtle.age = 26
//    println(turtle)
//
//    with(turtle) {
//        penDown()
//        fillWithColor()
//        penUp()
//    }
//    println(" call method in Turtle manually")
//    turtle.penDown()
//    turtle.fillWithColor()
//    turtle.penUp()

//    println(" try with resources ")
//    tryWithResource()

//    val b: Boolean? = null
//
//    val person = Person("name", 1)

//    boxTest()
//    bitOperation()
//    arrayTest()
//    stringTest()
    breakContinueInLoops()
}

fun breakContinueInLoops() {
    println("  break continue in loops  with labels ")
    loop0@ for (i in 1..10) {
//        print(" i = $i")
//        when (i) {
//            6 -> {
//                println(" i == 6 continue@loop0")
//                continue@loop0
//            }
//            8 -> {
//                println("i == 8 break@loop0")
//                break@loop0
//            }
//        }
//        println()
        loop1@ for (j in 2..12) {
//            print(" j = $j ")
            when (j) {
                4 -> {
                    println(" j == 4 continue@loop1")
                    continue@loop1
                }
                8 -> {
                    println("j == 8 continue@loop0")
                    continue@loop0
                }
//                6 -> {
//                    println("j == 6 break@loop1")
//                    break@loop1
//                }
                10 -> {
                    println("i == 10 break@loop0")
                    break@loop0
                }
            }
            println("i = $i j = $j")
        }
    }
}

fun stringTest() {
    println("Strings Test")
    val str: String = "123"
    for (c in str) print("$c ")
    println()
    val s = " Hello World! "
    val s1 = """
        for (i in 1..9){
            print(i)
        }
    """
    var s0 = """
        |trimMarginTest
        |Test2
        |Test3
    """
    val s9 = s0.trimMargin("|")
    println("$s0.trimMargin(\"|\") =+$s9+")
    println("s = $s\ns1 = $s1")
    val s2 = s.trimMargin()
    println("$s.trimMargin() =+$s2+")
    val s8 = s1.trimMargin()
    println("$s1.trimMargin =+$s8")
    val s3 = s.trim()
    val s4 = s.trimEnd()
    val s5 = s.trimStart()
    val s6 = s.plus("plus")
    val s7 = s + " + "
    println("$s.trim() =$s3+\n$s.trimEnd() =+$s4+\n$s.trimStart() =+$s5+\n$s.plus(\"plus\") =+$s6+\n$s + \" + \" =+$s7+")

}

fun arrayTest() {
    val a = Array(3, { i -> i + 1 })
    val b: Array<out Any>
    val c: Array<Any>
    b = a

    val d: IntArray = intArrayOf(1, 2, 3)
    println(" ${a.javaClass} ${a[0]} ${a[1]} ${a[2]}")
    println(" ${b.javaClass} ${b[0]} ${b[1]} ${b[2]}")
    println(" ${d.javaClass} ${d[0]} ${d[1]} ${d[2]}")
//    c = a
}

fun characterTest() {
    val a: Char = 'a'
    val b = '\uFF00'
    val c: Char?

}

fun bitOperation() {
    println(" bit operation")
    var a = 16
    var b = 2
    var c: Int = -a
    var d: Int? = 4
    val e = 0b10010000
    val f = 0b00000010
    println("$e  $f  ")
    println("${e and f}  ${e or f} ${e xor f}")
    println(" c.javaClass = ${c.javaClass}  d.javaClass = ${d?.javaClass}")
    println("a.javaClass = ${a.javaClass} ")

    val shl = a.shl(b)  // signed shift left << 带符号左移
    val shl2 = c.shl(b)
    println("$a.shl($b) = $shl  $c.shl($b) = $shl2")
    val shr = a.shr(b)  // signed shift right >> 带符号右移
    val shr2 = c.shr(b)
    println("$a.shr($b) = $shr  $c.shr($b) = $shr2")
    val ushr = a.ushr(b)  // unsigned shift right >>>
    val ushr2 = c.ushr(b)
    println("$a.ushr($b) = $ushr  $c.ushr($b) = $ushr2")
    val and = a.and(b)
    val and2 = c.and(b)
    println("$a.and($b) = $and   $c.and($b) = $and2")
    val or = a.or(b)
    val or2 = c.or(b)
    println("$a.or($b) = $or  $c.or($b) = $or2")
    val xor = a.xor(b)
    val xor2 = c.xor(b)
    println("$a.xor($b) = $xor $c.xor($b) = $xor2")
    val inv = a.inv()
    val inv2 = c.inv()
    println("$a.inv() = $inv  $c.inv() = $inv2")

    println("$d.inv() = ${d?.inv()}  $d.inc() = ${d?.inc()} ")

}

fun boxTest() {
    println(" boxing of numbers ")
    val a: Int = 1000
    println(a === a)
    val boxedA: Int? = a
    val anotherboxedA: Int? = a
    println(" " + boxedA.toString() + "  " + anotherboxedA.toString())
    println(" a = $a boxedA = $boxedA anotherboxedA = $anotherboxedA")
    // box does not preserve identity 不保持同一性  not the same object
    println(" boxedA === anotherboxedA is ${boxedA === anotherboxedA}")
    // does preserve equality 维持数据一致性  but hold the same value
    println(" boxedA == anotherboxedA  is ${boxedA == anotherboxedA}")

    println(" implicit conversion")
    // small types are not subtypes of bigger types
    val b: Int? = 1
//    val s: Byte? = null
//    val downTo = b?.downTo(s)
    val c: Long? = b?.toLong()
    println(" ${c == b?.toLong()} ")


}

class Person(name: String, age: Int) : Foo<String> {
    override fun foo(a: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun toString(): String {
//        return "Person() name is $name "
//    }

}

interface Foo<out T : Any> {
    fun foo(a: Int): T
}

fun tryWithResource() {
    val stream = Files.newInputStream(Paths.get("D:\\studioJob\\AndroidDemo\\lib\\src\\main\\java\\com\\example\\kotlin\\JavaTest.java"))
//    val stream = Files.newInputStream(Paths.get("\\\\JavaTest.java"))
    stream.buffered()?.reader().use {
        reader ->
        println(reader?.readText())
    }
//    stream.buffered().reader().useLines { line -> println(line.) }
}

class Turtle : Foo<String> {
    override fun foo(a: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var name: String = ""
    var age: Int = 0
    var color: Int = 0

    constructor(name: String, age: Int, sex: Int) {
        this.name = name
        this.age = age
        this.color = sex
    }

    constructor()

    fun penDown() {
        println("$name penDown ")
    }

    fun penUp() {
        println("$name penUp ")
    }

    fun fillWithColor() {
        println("$name fillWithColor $color")
    }

    override fun toString(): String {
        return "Turtle(name='$name', age=$age, sex=$color)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Turtle

        if (name != other.name) return false
        if (age != other.age) return false
        if (color != other.color) return false

        return true
    }


    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + color
        return result
    }


}

fun whenExpression(color: String): Int? {
    val a: Int? = when (color) {
        "RED" -> 0xff0000
        "YELLOW" -> 0xffff00
        "BLUE" -> null
        else -> throw  IllegalArgumentException("Invalid color param value")
    }
    return when (a) {
        in 1..9 -> {
            ""
            println()
            0
        }
        else -> {
            0
        }

    }
}

fun ifExpression(obj: Any): String {
    val result = if (obj is Long) {
        "params is Long"
    } else if (obj is String) {
        "param is String"
    } else if (obj == 1) {
        " param is Int 1"
    } else {
        "NOTHING"
    }
    return result
}

fun String.spaceToCamelCase() {

}

var map: Map<Any, Any> = mapOf("a" to 1, "b" to 2)
fun mapTest() {
    map.forEach { k, v -> print(" map.foreach key is $k and value is $v ") }
    println()
    for ((kv, v) in map) {
        print("map.for loop key is $kv and value is $v")
    }
    println()
    val size = map.size
    val keys = map.keys

//    for (i in keys.indices) {
//        print(" map.keys for loop  key is ${keys.} ")
//    }
}

// loop
val list = listOf("apple", "orange", "kiwi", "oppo", "oa", "oo")

fun collectionsTest(item: Any) {

    val contains = list.contains(item)
    println(" $list.contains$item = $contains")
    if (item in list) println(" $item in $list") else println(" $item is not in $list")
    when {
        item in list -> println(" when condition $item in $list")
        else -> println(" when condition $item is not in $list")
    }

    println(" collections use lambda filter sortby map foreach")
    list.filter { it.startsWith("o") }.sortedBy { it }.map { it.toUpperCase() }.forEach { println(it) }
    val sortedBy = list.sortedBy { it }
    println("sortedBy is List ${sortedBy is List}")
}

fun loopTest(): Any {
    for (item in list) {
        print(item + " ")
    }
    println()
    for (index in list.indices) {
        println(" item at $index is ${list[index]}")
    }
    var idx: Int = 0;
    while (idx in list.indices) {
        println(" between while loop in list.indices item at $idx is ${list[idx]}")
        idx++
    }
    idx = 0
    while (idx < list.size) {
        println(" between while loop < size item at $idx is ${list[idx]}")
        idx++
    }

//    var list2 = listOf("1", "2", "3")
//    var list3 = list2.plus("4")
//    println("list2 is $list2  list3 is $list3 ")
//   var list4 = list.plus("polsu test")
//    println("after plsu list is $list  list4 is $list4")

    return ""
}

fun whenTest(obj: Any): String? {
    var result: String
    when (obj) {
        1 -> result = " result is Int 1"
        "1" -> result = " result is String 1"
        "str" -> result = " result is str"
        is Long -> result = " result is long"
        !is String -> result = " result is not string"
        else -> result = " obj is unknown"
    }
    return result
}

fun isTest(obj: Any, obj2: Any): Int? {
    /* if (obj is String) {
         return obj.length
     }*/
    /*if (obj2 is Int)
        return obj2*/
    if (obj !is String) return null
    return 0
}

fun rangeTest(obj: Int) {
    val x1: Int = 0
    val y: Int = 11
    if (obj in 1..y + 1) {
        println("$obj fit in 1 - ${y + 1}")
    } else if (obj !in 1..y + 1) println(" $obj is not fit in 1 - ${y + 1} ")
    println(" normal for loop  ")
    for (x in x1..y + 1) {
        print("$x ")
    }
    println(" \nfor loop over step 2 ")
    for (x in x1..y + 1 step (2)) {
        print(" $x ")
    }
    println(" \nfor loop downgrade over step 2 ")
    for (x in y + 1 downTo x1 step 2)
        print(" $x ")

    println("\n for loop left $x1 include right $y y+1 ${y + 1} exclude")
    for (x in x1 until y + 1)
        print(" $x ")
}

data class Customer(val name: String, val age: Int, var sex: Int) {

//    fun getName():String{
//        return  name
//    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Customer) return false
        else {
            var equals = name.equals(other.name)
            if (equals) equals = age == other.age
            if (equals) equals = sex == other.sex
            return equals
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "Customer name is $name ,age is  $age ,sex is $sex "
    }

//    fun copy():Customer{
//
//    }

}

