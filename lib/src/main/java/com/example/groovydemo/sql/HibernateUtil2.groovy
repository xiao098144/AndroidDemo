package com.example.groovydemo.sql

File file = new File('sql2Hibernate.txt')
if (file.exists() && file.canRead()) {
    List<Map> list = new ArrayList<>()
    // Groovy load Java class
//    GroovyClassLoader loader = new GroovyClassLoader()
//    def clazz = loader.parseClass(new File("SqlDataBean.java"))
//    GroovyObject object = clazz.newInstance()
    // object is just like new SqlDataBean()
    SqlDataBean info = new SqlDataBean()
    Map map0
    file.eachLine { value ->
        if (value != null && value.length() > 0 && value.contains("`") && !value.contains("PRIMARY KEY")) {
            value = value.trim()
            map0 = new HashMap()
            def idx1 = value.indexOf("`")
            def idx2 = value.lastIndexOf("`")
            def s = value.substring(idx1 + 1, idx2)
//            print("name1 is $s")
            if (value.contains("TABLE") || value.contains("table")) {
                //找到表名
                map0.put(SqlDataBean.FILEDTABLE, s)
                if (info.table == null)
                    info.table = s
            } else {
                map0.put(SqlDataBean.FILED_SQL, s)
                def array = s.split("_")
                StringBuilder sb = new StringBuilder()
                for (int i = 0; i < array.length; i++) {
                    if (i == 0) {
                        sb.append(array[i])
                    } else sb.append(format(array[i]))
                }

                map0.put(SqlDataBean.FILED_JAVABEAN, sb.toString());
                sb.setLength(0)
                def trim = value.substring(idx2 + 1).trim()
//                print(" idx2 = $idx2 trim = $trim ")

                def idx3 = trim.indexOf(" ")
                def s1
                if (idx3 != -1) s1 = trim.substring(0, idx3)
                else s1 = trim.substring(0, trim.length() - 1)
//                print(" idx2 = $idx2 trim = $trim idx3 = $idx3 s1 = $s1")
                if (s1.contains("(")) {
                    s1 = s1.substring(0, s1.indexOf("("))
                }
//            print(" final get filed type is $s1 ")
                map0.put(SqlDataBean.FILEDTYPE, doDataType(s1))
                if (value.contains("AUTO_INCREMENT")) {
                    // 主键
                    map0.put(SqlDataBean.PRIMARYKEY, 1)
                }
            }
//            print("value is $value map is $map")
//            info.fileds = map
            list.add(map0)
        }
    }
    info.fileds = list
//    println(" read finished info is $info")
//    print(" after read list is $list")
    //<id name="id" column="id" type="java.lang.Integer">
//<generator class="increment"/>
//</id>
//        <property name="name" column="name" type="java.lang.String"/>
    if (info != null) {
        StringBuilder sb1 = new StringBuilder();
        List javabean = new ArrayList()
        List hibernate = new ArrayList()
        StringBuilder sb2 = new StringBuilder();
        String namePrefix = "TysCrmYwRd";
        String nameSuffix = "VO"
        String packages = "com.ddoctor.server.vo.crm";
        String doc = "/**\n" +
                " * <p>Title: Value code</p>\n" +
                " * <p>Description: data view</p>\n" +
                " * <p>Copyright: Copyright (c) 2017</p>\n" +
                " * <p>Company: ddoctor</p>\n" +
                " * @author xiao\n" +
                " * @version 1.0\n" +
                " */"
        String doc2 = "    /**\n" +
                "     * 定义本类的私有变量\n" +
                "     */"
        def operationWriter = new File('JavaBean.java').asWritable().newPrintWriter()
        def javaBeanWriter
        def hibernateWriter = new File('hibernate.xml').asWritable().newPrintWriter()
        def table = info.table
        def className = "$namePrefix" + format2(table) + "$nameSuffix"
        javaBeanWriter = new File(className + ".java").asWritable().newPrintWriter()
//                hibernateWriter = new File(className + ".xml").asWritable().newPrintWriter()
        javabean.add("package " + packages + ";")
        javabean.add("\n $doc \n")
        javabean.add("public class $className implements java.io.Serializable {\n")
        javabean.add("$doc2\n")
        hibernate.add("    <class name=\"$packages.$className\" table=\"$table\">\n")
        List fileds = info.fileds
        StringBuilder contrunctors = new StringBuilder()//拼接有参构造函数
        StringBuilder contr = new StringBuilder()//拼接有参构造函数
        StringBuilder methods = new StringBuilder()     //拼接getset Method
        if (fileds != null && fileds.size() > 0) {
            contrunctors.append("public $className(")
            fileds.eachWithIndex { Map map, int i ->
                def name2 = map.get(SqlDataBean.FILED_JAVABEAN)
                def name1 = map.get(SqlDataBean.FILED_SQL)
                def type = map.get(SqlDataBean.FILEDTYPE)
                if (name2 != null && name1 != null && type != null) {
                    def javaType = format3(type)
//                if (javaBeanWriter == null) {
//                    javaBeanWriter = new File('JavaVO.java').asWritable().newPrintWriter()
//                }
                    contrunctors.append("$javaType $name2")
                    contr.append("this.$name2 = $name2;\n")
                    methods.append("/**\n")
                    methods.append("*\n")
                    methods.append("*$name2 set 方法\n")
                    methods.append("*/\n")
                    methods.append(generateSetMethod(name2, javaType))

                    methods.append("/**\n")
                    methods.append("*\n")
                    methods.append("*$name2 get 方法\n")
                    methods.append("*/\n")
                    methods.append(generateGetMethod(name2, javaType))

                    if (i < fileds.size() - 1) contrunctors.append(", ")

                    sb2.append("    private " + javaType + " " + name2 + ";\n\n")
                    javabean.add(sb2.toString())
                    sb2.length = 0
                    if (1 == map.get(SqlDataBean.PRIMARYKEY)) {
                        //匹配到主键
                        sb1.append("        <id name=\"")
                        sb1.append(name2 + "\"")
                        sb1.append(" column=\"")
                        sb1.append(name1 + "\"")
                        sb1.append(" type=\"")
                        sb1.append(type + "\">\n")
                        sb1.append("            <generator class=\"increment\"/>\n")
                        sb1.append("        </id>\n")
                        hibernate.add(sb1.toString())
                        sb1.setLength(0)
                    } else {
                        sb1.append("        <property name=\"" + name2 + "\" column=\"" + name1 + "\" type=\"" + type + "\"/>")
                        sb1.append("\n")
                        hibernate.add(sb1.toString())
                        sb1.length = 0
                    }
                }
            }
            contrunctors.append(")\n")
            contrunctors.append("{\n")
            contrunctors.append(contr)
            contrunctors.append("}\n")
        }

        hibernate.add("    </class> ")

        javabean.add("  public $className(){\n  }\n")
        if (contrunctors.length() > 0) javabean.add(contrunctors.toString())
        if (methods.length() > 0) javabean.add(methods.toString())

        javabean.add("}")

        hibernate.forEach { value ->
            hibernateWriter.append(value)
        }
        hibernateWriter.flush()
        hibernateWriter.close()
        javabean.forEach { value ->
            javaBeanWriter.write(value)
        }
        javaBeanWriter.flush()
        javaBeanWriter.close()
    }
} else print('sql2Hibernate.txt not found or can not be read')

String generateSetMethod(String filed, String javaType) {
    StringBuilder sb = new StringBuilder()
    sb.append("public void set" + format(filed) + "($javaType $filed){\n")
    sb.append("this.$filed = $filed;\n")
    sb.append("}\n")
    return sb
    sb.length = 0
}

String generateGetMethod(String filed, String javaType) {
    StringBuilder sb = new StringBuilder()
    sb.append("public $javaType get" + format(filed) + "(){\n")
    sb.append("return $filed;\n")
    sb.append("}\n")
    return sb
    sb.length = 0
}

String format3(String type) {
    if (!type.contains("java.util.Date")) {
        type = type.substring(type.lastIndexOf(".") + 1)
    }
    return type
}

String doDataType(String sqlType) {
    sqlType = sqlType.toLowerCase()
    String type = sqlType
    switch (sqlType) {
        case "char":
        case "varchar":
        case "text":
            type = "java.lang.String"
            break
        case "int":
        case "tinyint":
            type = "java.lang.Integer"
            break
        case "date":
        case "datetime":
        case "timestamp":
            type = "java.util.Date"
            break
        case "float":
            type = "java.lang.Float";
            break
        case "decimal":
            type = "java.math.BigDecimal";
            break
    }
    return type
}

String format2(String s) {
    def array = s.split("_")
    StringBuilder sb = new StringBuilder()
    for (int i = 0; i < array.length; i++) {
        sb.append(format(array[i]))
    }
    return sb.toString()
    sb.length = 0
}

String format(String s) {
    return String.valueOf(s.charAt(0).toUpperCase()) + s.substring(1)
}