import java.util.regex.Pattern

//生成 VO 与  JavaBean 互相转换方法
File file = new File('./')
//def file = new File('D:\\work\\ddoctor-server-v2\\src\\com\\ddoctor\\server\\vo\\cmb')
//println("read current file dir $file " + file.exists() + " " + file.canRead() + " " + file.path)
//def list = []
def map = [:]
if (file.exists() && file.canRead()) {
    //匹配指定名称的文件
    file.eachFileMatch(~/Tys.*SalesVO\.java/) { File value ->
//        println("eachFileMatch  value is $value")
        def name = value.getName()
        name = name.substring(0, name.length() - 5)
        def format = nameFormat(name)
        map.put(name, format)
        def fileds = getFileds(value, format.substring(0, format.length() - 4))
        generateJavaBean(format, fileds)
        generateInfo2VO(name, format, fileds)
        generateVO2Info(name, format, fileds)
    }
//    println("  map is $map")
}

void generateJavaBean(String name, List<Map> fileds) {
    def sb = new StringBuffer()
    def contrunctors = new StringBuilder()//拼接有参构造函数
    def contr = new StringBuilder()//拼接有参构造函数
    def methods = new StringBuilder()     //拼接getset Method
    def packages = "package com.ddoctor.user;\n"
    def doc = "    /**\n" +
            "     * 定义本类的私有变量\n" +
            "     */\n"
    def writer = new File("$name" + ".java").asWritable().newPrintWriter("UTF-8")
    writer.write(packages)

    writer.write("\nimport java.util.Date;\n")

    writer.write("public class $name implements java.io.Serializable {\n")
    contrunctors.append("public $name(")
    fileds.eachWithIndex { Map map, int i ->
        def type = map.get(1)
        def filed = map.get(3)
        sb.append("     private " + type + " " + filed + ";\n\n")
        contrunctors.append(type + " " + filed)
        if (i < fileds.size() - 1)
            contrunctors.append(",")
        else {
            contrunctors.append("){\n")
        }
        contr.append("      this.$filed = $filed;\n")
        methods.append(generateSetMethod(filed, type))
        methods.append(generateGetMethod(filed, type))
    }
    contrunctors.append(contr.toString())
    contrunctors.append("      }\n")
    writer.write(doc)
    writer.write(sb.toString())
    sb.length = 0
    writer.write("public $name(){\n" +
            "}\n")
    writer.write(contrunctors.toString())
    contrunctors.length = 0
    writer.write(methods.toString())
    methods.length = 0
    writer.write("  }")
    writer.flush()
    writer.close()
}
//def infovoWriter
void generateInfo2VO(String VOName, String infoName, List<Map> fileds) {
    def sb = new StringBuffer()
    sb.append("public $infoName getInfoFromVo($VOName vo){\n")
    sb.append("     if(vo == null) return null;\n")
    sb.append("     $infoName info = new $infoName();\n")
    fileds.eachWithIndex { Map map, int i ->
        def type = map.get(1)
        def filed = map.get(3)
        def sqlFiled = map.get(2)
        sb.append("     info." + generateSetMethodLite(filed, type) +
                "(vo." + generateGetMethodLite(sqlFiled, type) + ");\n")
    }
    sb.append("     return info;\n")
    sb.append("}")
    println("$sb")
}

void generateVO2Info(String VOName, String infoName, List<Map> fileds) {
    def sb = new StringBuffer()
    sb.append("public $VOName getVOFromInfo($infoName info){\n")
    sb.append("     if(info == null) return null;\n")
    sb.append("     $VOName vo = new $VOName();\n")
    fileds.eachWithIndex { Map map, int i ->
        def type = map.get(1)
        def filed = map.get(3)
        def sqlFiled = map.get(2)
        sb.append("     vo." + generateSetMethodLite(sqlFiled, type) +
                "(info." + generateGetMethodLite(filed, type) + ");\n")
    }
    sb.append("     return vo;\n")
    sb.append("}")
    println("$sb")
}

String nameFormat(String name) {
    String namePrefix = "TysExtYwRd"
    return name.substring(namePrefix.length(), name.length() - 2) + "Bean";
}

List<Map> getFileds(File file, String table) {
    if (file != null) {
        def array
        def list = []
        def map
        def fileds
        String pattern = ".* *;";
        def r = Pattern.compile(pattern);
//        def m = r.matcher(str);
        file.eachLine { value ->
            if (value != null) {
                value = value.trim()
                if (value.startsWith("private")) value = value.replace("private", "")
                if (value.startsWith("public")) value = value.replace("public", "")
                if (value.startsWith("protected")) value = value.replace("protected", "")
                value = value.trim()
//                println("value is $value")
                def m = r.matcher(value)
//                if (value.startsWith("private") && value.endsWith(";")) {
                if (m && !(value.startsWith("this") || value.startsWith("return") || value.contains(".") || value.startsWith("+"))) {
                    map = [:]
//                    println("value is $value")
                    value = value.substring(0, value.length() - 1)
                    array = value.split(" ")
                    if (array.length == 2) {
                        map.put(1, array[0])
                        map.put(2, array[1])
                        fileds = array[1].contains(lowerCase(table)) ? array[1].substring(table.length()) : array[1]
//                    if (array[2].contains(lowerCase(table))) {
//                        map.put(3, array[2].substring(table.length()))
//                    } else map.put(3, array[2])
                        map.put(3, fileds)
//                        if (table.equalsIgnoreCase("CmbUserPay"))println("value is $value  result map is $map")

                        list.add(map)
                    }
                }
            }
        }
//        if (table.equalsIgnoreCase("CmbUserPay"))
//            println("table is $table and fileds is $list")
        return list
    }
    return null
}

String lowerCase(String name) {
    return String.valueOf(name.charAt(0).toLowerCase()) + name.substring(1)
}

static String generateSetMethodLite(String filed, String javaType) {
    StringBuilder sb = new StringBuilder()
    sb.append("set" + format(filed))
    return sb
    sb.length = 0
}

static String generateSetMethod(String filed, String javaType) {
    StringBuilder sb = new StringBuilder()
    sb.append("public void set" + format(filed) + "($javaType $filed){\n")
    sb.append("     this.$filed = $filed;\n")
    sb.append("}\n")
    return sb
    sb.length = 0
}

static String generateGetMethodLite(String filed, String javaType) {
    StringBuilder sb = new StringBuilder()
    sb.append("get" + format(filed) + "()")
    return sb
    sb.length = 0
}

static String generateGetMethod(String filed, String javaType) {
    StringBuilder sb = new StringBuilder()
    sb.append("public $javaType get" + format(filed) + "(){\n")
    sb.append("     return $filed;\n")
    sb.append("}\n")
    return sb
    sb.length = 0
}


static String format(String s) {
    return String.valueOf(s.charAt(0).toUpperCase()) + s.substring(1)
}
