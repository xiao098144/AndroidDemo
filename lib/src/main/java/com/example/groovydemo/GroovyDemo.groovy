// List 遍历
println 'List 遍历'
List<String> list = new ArrayList<>();
for (int i = 0; i < 10; i++) {
    list.add("item-" + i);
}
list.each { item ->
    print('item = ' + item + '  ')
}

// 数组遍历
println('\n数组遍历 ')
String[] array = new String[5]
array[0] = 'array1'
array[1] = 'array2'
array[2] = 'array3'
array[3] = 'array4'
array[4] = 'array5'
array.each { value ->
    print('String array value = ' + value + '  ')
}
println()
Integer[] intArray = new Integer[5]
for (int i = 0; i < intArray.size(); i++) {
    intArray[i] = new Random().nextInt(20)
}
int sum = 0;
intArray.each { intValue ->
    print('intArray intValue = ' + intValue + '  ')
    sum += intValue
}
println('intArray summary is = ' + sum)

// 文件读写
println '文件读写'
File file = new File('ss.txt')
String pre = "toHexString";
file.eachLine {value ->
    if (value != null && value != '' && value.contains(pre)){
           value =value.trim();
        println(value)
    }
}


File demoFile = new File('Demo.txt')
StringBuilder sb = new StringBuilder();
String prefix = "5555AAAA";
String prefix1 = "public static final String %1s = \"%2s\";";
demoFile.eachLine { value ->
//    println('文件读写 eachLine value = ' + value)
    if (value != null && value != '') {
        value = value.trim();
        if (value.startsWith("#")) {
            value = value.replaceAll("\\s*", "");
            int idx = value.indexOf("0x");
            try {
                sb.append(String.format(Locale.getDefault(), prefix1, value.substring(7, idx - 1), value.substring(idx + 2, idx + 6)))
                sb.append("\n");
            } catch (Exception e) {
                println("value = " + value + " idx = " + idx + " ");
            }
        } else if (value.startsWith("p")) {
//            print "替换前原始值  value = "+value
//            value = value.replace("String","int")
////            print "   替换String为int后  value = "+value
//            value = value.replaceFirst("\"","0x");
////            print "   替换第一个\"为0x后 value = "+value
//            value = value.replace("\"","");
////            print "   最终替换结束  value = "+value
//            sb.append(value+"\n");
        }
    }
}
//println(sb.toString());
long startTime = System.currentTimeMillis();
println "开始执行  " + startTime


@Grab('mysql:mysql-connector-java:5.1.5')
@GrabConfig(systemClassLoader = true)
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import groovy.sql.Sql;
println("Groovy Mysql")
sql = Sql.newInstance("jdbc:mysql://localhost:3306/test", "admin",
        "admin", "com.mysql.jdbc.Driver")

String show = " show tables ;";
sql.(show).each{ Row ->;
    println(row)
}

//File  file = new File('C:\\Users\\sks\\Desktop\\mobilesegment.txt')
//String insert = "insert into mobilesegment (mobilesegment,area,mobiletype,areacode) values (%1s,%2s,%3s,%4s)";
//file.eachLine("GBK") {value,linenumber ->
//    if (linenumber >=2) {
//        String[] arrays = value.split("\\s+")
//        try {
//            sql.execute(String.format(insert, arrays[0], arrays[1], arrays[2], arrays[3]))
//        } catch (Exception e) {
//           e.printStackTrace()
//        }
//    }
//}
//String group_concat = " select group_concat(mobilesegment),area,areacode,mobiletype from mobilesegment group by area,areacode ,mobiletype;";
//sql.(group_concat).each{ Row ->;
//    println row
//}
//println("执行结束   "+(System.currentTimeMillis()-startTime))
//
//println(" read xlsx")
//
//File excelFile = new File("device.xlsx")
//FileInputStream is = new FileInputStream(excelFile);
//
//XSSFWorkbook workbook = new XSSFWorkbook(is);
////workbook.setMissingCellPolicy(Row.);
//
////循环sheet
//(0..<workbook.sheetIterator().collect { return it }.@size).each { s ->
//    XSSFSheet sheet = workbook.getSheetAt(s);
//    int rows = sheet.physicalNumberOfRows;
//    StringBuilder sb2 = new StringBuilder();
//    def preName = "";
//    def name = "";
//    //忽略第一行,标题行
//    (0..<rows).each { r ->
//        XSSFRow row = sheet.getRow(r);
//        def cells = row.physicalNumberOfCells;
//        (0..<cells).each { c ->
//            XSSFCell cell = row.getCell(c);
//            String cellValue = cell.getStringCellValue();
//
//            if (c == 0) { // A
//                if (cellValue != null && cellValue.length() > 0) name = cellValue;
//                else name = preName;
//            }
//            preName = name
//            println("c == "+c+" name = " + name + " preName = " + preName + " cell = " + cell + " cellValue = " + cellValue)
//            if (c == 1){
//                sb2.append("insert into jzjx_ext_dic_machinetype ( machinetype_name ,machinetype_category ,machinetype_subcategory )values(");
//                sb2.append("'")
//                sb2.append(name);
//                sb2.append(cellValue);
//                sb2.append("'")
//                sb2.append(",")
//                sb2.append("'")
//                sb2.append(name)
//                sb2.append("'")
//                sb2.append(",")
//                sb2.append("'")
//                sb2.append(cellValue)
//                sb2.append("'")
//                sb2.append(");")
//            }
////            if (c < 2) {
////                sb2.append(name);
////                sb2.append(cellValue);
////            } else {
////                sb2.append(name);
////                sb2.append(cellValue);
//////                sb2.append(name+cellValue+"\n");
////            }
////            def name2;
////            switch (c) {
////                case 0:
////                    name2 = "A:"
////                    break;
////                case 1:
////                    name2 = "B:"
////                    break
////                case 2:
////                    name2 = "C:"
////                    break
////                case 3:
////                    name2 = "D:"
////                    break
////            }
////            print name2 + "  " + cell + ", ";
//        }
//        sb2.append("\n")
//        println "";
//    }
//    println(sb2.toString())
//}

//demoFile.filterLine { String str ->
//    if (str.startsWith("#")) {
//        print('Groovy filterLine found filterCode str = ' + str + ' ')
//        return
//    } else print('Groovy filterLine result str = ' + str + ' ')
//}
