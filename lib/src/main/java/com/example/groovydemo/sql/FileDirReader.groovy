package com.example.groovydemo.sql
// 文件夹扫描 并读取文件 生成数据库操作方法
def dir = new File('D:\\work\\ddoctor-server-v2\\src\\com\\ddoctor\\server\\vo\\crm')
String addDoc = "   /**\n" +
        "\t     * 添加一条记录\n" +
        "\t     */\n"
String updateDoc = "    \t/**\n" +
        "\t     * 修改一条记录\n" +
        "\t     */\n"
String deleteDoc = "    /**\n" +
        "\t     * 删除一条记录\n" +
        "\t     */\n"

String getDoc = "   /**\n" +
        "\t     * 获得表中特定主键的数据\n" +
        "\t     */\n"

String allDoc = "\t     /**\n" +
        "\t     * 获得表中的所有数据\n" +
        "\t     */\n"

if (dir.exists() && dir.canRead()) {
    def operationWriter = new File('JavaBean.java').asWritable().newPrintWriter()
    dir.eachFile { value ->
        if (value != null) {
            def name = value.getName()
            def array = readFile(name)
            println(array[0] + "  " + array[1])
            name = name.substring(0, name.length() - 5)
            operationWriter.write(addDoc)
            operationWriter.write(generateAddMethod(name))

            operationWriter.write(updateDoc)
            operationWriter.write(generateUpdateMethod(name))

            operationWriter.write(deleteDoc)
            operationWriter.write(generateDeleteMethod(name))

            operationWriter.write(getDoc)
            operationWriter.write(generateGetMethod(name, array[0], array[1]))

            operationWriter.write(allDoc)
            operationWriter.write(generateGetAllMethod(name, array[0], array[1]))

        }
    }
    operationWriter.flush()
    operationWriter.close()
}

String[] readFile(String path) {
    File file = new File(path)
//    println("file is $file exists() = " + (file.exists()) + "  canread " + (file.canRead()))
    if (file.exists() && file.canRead()) {
//        println("eachWithIndex")
        String[] array = new String[2]
        def target
        file.eachWithIndex { String entry, int i ->
            def trim = entry.trim()
            if (i == 0) {
                entry = trim.substring(trim.indexOf(" "), trim.length() - 1)
                array[0] = entry
            } else {
                if (entry.contains("定义本类的私有变量")) {
                    target = i + 2
                }
                if (i == target) {
                    array[1] = trim.substring(trim.lastIndexOf(" "), trim.length() - 1)
                }
            }
        }
        return array
    }
}

String generateAddMethod(String name) {
    StringBuilder sb = new StringBuilder()
    sb.append("public static boolean add")
    sb.append(name.substring(0, name.length() - 2))
    sb.append("(" + name + " vo)\n ")
    sb.append("{\n")
    sb.append("     return HibernateVIPOperation.addRecordInfo(vo);\n")
    sb.append("}\n")
    return sb.toString()
    sb.length = 0
}

String generateUpdateMethod(String name) {
    StringBuilder sb = new StringBuilder()
    sb.append("public static boolean update")
    sb.append(name.substring(0, name.length() - 2))
    sb.append("(" + name + " vo)\n ")
    sb.append("{\n")
    sb.append("     return HibernateVIPOperation.updateRecordInfo(vo);\n")
    sb.append("}\n")
    return sb.toString()
    sb.length = 0
}


String generateDeleteMethod(String name) {
    StringBuilder sb = new StringBuilder()
    sb.append("public static boolean delete")
    sb.append(name.substring(0, name.length() - 2))
    sb.append("(Integer id)\n ")
    sb.append("{\n")
    sb.append("     String sql = \"delete from $name as p where p.extId=?\";\n")
    sb.append("     return HibernateVIPOperation.deleteRecordInfo(sql, id);\n")
    sb.append("}\n")
    return sb.toString()
    sb.length = 0
}

String generateGetMethod(String name, String packages, String id) {
    StringBuilder sb = new StringBuilder()
    sb.append("public static $name get")
    sb.append(name.substring(0, name.length() - 2))
    sb.append("(Integer $id)\n ")
    sb.append("{\n")
    sb.append("$name vo = ($name) HibernateVIPOperation.getRecordInfo(id, \"$id\",\"$packages.$name\");\n")
    sb.append("     return vo;\n")
    sb.append("}\n")
    return sb.toString()
    sb.length = 0
}

String generateGetAllMethod(String name, String packages, String id) {
    StringBuilder sb = new StringBuilder()
    sb.append("public static $name[] getAll")
    sb.append(name.substring(0, name.length() - 2))
    sb.append("(String query)\n ")
    sb.append("{\n")
    sb.append("try{\n")
    sb.append("List ls = HibernateVIPOperation.getAllRecordInfo(query,\"$packages.$name\");\n")

    sb.append("$name recgroup[] = new $name[ls.size()];\n")
    sb.append("for (int i = 0; i < ls.size(); i++)\n")
    sb.append(" recgroup[i] = ($name) ls.get(i);\n")
    sb.append("     return recgroup;\n")
    sb.append("}catch (Exception ex){\n" +
            "\t\t\tex.printStackTrace();\n" +
            "\t\t}return null;")
    sb.append("}\n")
    return sb.toString()
    sb.length = 0
}