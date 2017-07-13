File file0 = new File('C:/Users/sks/Desktop/dd.txt')
Map map0 = new HashMap()
Map map = new LinkedHashMap()
if (file0 != null && file0.exists()) {
    file0.eachLine { value ->
        if (value != null && value.length() > 0) {
            def split = value.split(";")
            def object = map0.get(split[1])
//            println(split[0] + "-" + split[1] + "-" + object)
            if (object == null) {
                map0.put(split[1], split[0])
            } else {
                map.put(object, split[0])
            }
        }
    }
}
//println(map0)
//println(map)

File file = new File('C:/Users/sks/Desktop/dd.sql')
if (file != null && file.exists()) {
    List<String> list = new ArrayList<>()
    StringBuilder sb = new StringBuilder()
    def file2 = new File('C:/Users/sks/Desktop/fileWrite.txt')

    if (file2.exists())
        file2.delete()
    println("${file2.length()}  file2 is exist" + file2.exists() + " " + file2.absolutePath)
    def printWriter = file2.newPrintWriter() //

    file.eachLine { value ->
        if (value != null && value.length() > 0) {
//            def of = value.lastIndexOf(",")
//            def s = value.substring(0, of)
//            def of1 = s.lastIndexOf(",")
//            def s1 = value.substring(of)
//            s = s.substring(0, of1)
//            println(" before replace value = " + value)
//            value = value.replace(s1, ',21')
//            value = s + ", 21" + s1
            if (value.contains('``')) value = value.replace('``', '`api`')
//            def of2 = value.lastIndexOf("(") + 1
//            def s2 = value.substring(of2)
//            def s3 = value.substring(0, of2)
//            def of3 = s2.indexOf(",") + 1
//            def s4 = s2.substring(of3)
////            println(s2 + "--" + s3 + "--" + s4)
//            value = s3 + s4
//            println(" after replace value = " + value)
            def of4 = value.lastIndexOf(",") + 1
            def s6 = value.substring(0, of4)
            def of5 = value.lastIndexOf(");")
            def s5 = value.substring(of4, of5).trim()
//            println("s5" + s5 + " map.get(s5) = " + map.get(s5))
            value = s6 + map.get(s5) + ");"

            printWriter.write(value)
            printWriter.write('\n')
//            println(" final value is " + value)
//            sb.append(value + "\n")
        }
    }
    printWriter.flush()
    printWriter.close()
//    println(list)
//    println(sb)
}