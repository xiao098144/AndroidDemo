File file = new File('sqlformat.txt')
Map<String, String> map = new HashMap<>()
List<String> list = new ArrayList<>()
file.eachLine { value ->
    if (value != null && value.length() > 0) {
        value = value.toLowerCase()
//        constraint PK_TYS_EXT_YW_PATIENTS_VIPRECO primary key
        if (value.contains("table")) {
            // 记录表名
            if (!map.containsKey("table"))
                map.put("table", value.substring(value.indexOf("table") + 5).trim())
        }
        if (value.contains("primary key") && value.contains("constraint")) {
            def idx = value.indexOf("(")
            list.add("PRIMARY KEY " + value.substring(idx))
        } else if (value.contains("comment")) {
//            println(" read comment about table and column  ")
            def idx2 = value.indexOf("is") + 2
            def comment = value.substring(idx2, value.length() - 1).trim()
            comment = " COMMENT " + comment + " , "
//            println("comment is ${comment}")
            if (value.contains("table")) {
                map.put("table.comment", comment);
            } else {
                def column = value.substring(value.indexOf(".") + 1, idx2 - 2).trim()
//                println(" column is ${column}")
                map.put(column, comment)
            }
        } else {
            list.add(value)
        }

    }
}
println(" table column is ${map} ")
println(" after init  ${list}")
StringBuilder sb = new StringBuilder()
StringBuilder sb2 = new StringBuilder()
list.each { value ->
    if (value.contains("table") || value.contains("(") || value.contains(")")) {
        sb.append(' ' + value + ' ')
    } else {
        def split = value.split(" ")
        def column = split[0]
        def properties = split[1]
        def defaultValue = split[2]
        sb2.append(column)
        switch (properties) {
            case "int":
                break;
            case "float":
                break;
            case "char":
                break;
            case "varchar":
                break;
        }
//        sb2.append(properties)
        //            if (value.contains("null")) {
//                if (!value.contains("not null")) {
//                    value = value.replace("null", "default null")
//                }
    }
}
println(" final result ${sb}")