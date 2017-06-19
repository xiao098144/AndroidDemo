File file = new File('values.txt')
if (file != null) {
    StringBuilder sb = new StringBuilder();
    file.eachLine { value ->
        if (value != null && value.length() > 0) {
            sb.append(value+",");
//                sb.append(value.substring(5) + "\n");
        }
    };
    println(sb)
}