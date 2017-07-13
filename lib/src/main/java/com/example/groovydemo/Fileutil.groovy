File file = new File('file1.txt')
StringBuilder sb = new StringBuilder()
if (file != null && file.exists()) {
    sb.append("(")
    file.eachLine { value ->
        sb.append(value + ",")
    }
    sb.append(")")
    println(sb)
}