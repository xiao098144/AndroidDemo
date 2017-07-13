File file = new File('values.properties')
if (file != null) {
    StringBuilder sb = new StringBuilder();
    file.eachLine { value ->
        if (value != null && value.length() > 0) {
            if (!value.startsWith("#"))
                sb.append(value+",");
        }
        if ("end".equalsIgnoreCase(value))
            return
    };
    println(sb)
}