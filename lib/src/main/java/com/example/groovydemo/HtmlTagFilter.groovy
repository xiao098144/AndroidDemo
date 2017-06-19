import java.util.regex.Matcher
import java.util.regex.Pattern

File file = new File('html.txt')
if (file != null) {
    StringBuilder sb = new StringBuilder()
    List<String> list = new ArrayList<>()
    file.eachLine { value ->

//        if (value.contains("&lt;img src"))
//            println(" found img tag  " + value)
        if (value.contains("&amp;emsp;")) {
            list.add(value)
        } else {
            sb.append(value)
        }
    }
    def str = sb.toString()
    str = str.replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("<strong>", "<view style=\"font-weight:bold;\">").replace("</strong>", "</view>").replace("<br />", "").replace("<p>", "<view>").replace("</p>", "</view>").replace("<span", "<view").replace("span>", "view>");
//    str = str.replace("&lt;", "<").replace("&gt;", ">").replace("&quot;","\"").replace("<br />","").replace("<p>", "<view>").replace("</p>", "</view>").replace("<span", "<view").replace("span>", "view>");
    println(str)
    println("===================================")
    println(" 共检测到记录项 " + list.size())
//    list.each { it ->
//        println(it)
//    }
    println("====================================  deal with img tag ")

    List<Map<String, String>> list2 = new ArrayList<>()
    Map<String, String> map = new HashMap<>()
    def array = str.split("</view>")
    for (int i = 0; i < array.length; i++) {
        println(" i = ${i}  value is ${array[i]}")
    }
//    def idx = str.indexOf("<img src")
//    if (idx != -1) {
//        def str1 = str.substring(idx)
//        def str2 = str.substring(0, idx)
//        def idx1 = str2.lastIndexOf("<view")
//        def idx2 = str2.lastIndexOf("</view>")
//        def str3 = str2.substring(idx2 + "</view>".length())
//        println("  str3 = ${str3}")
//        println(" str1  = ${str1}  ////   str2 = ${str2}  idx1 = ${idx1}  idx2 = ${idx2}  " + "</view>".length())
//        def num1 = appearNumber(str1, "</view>")
//        def num2 = appearNumber(str1, "<view")
//        println(" 检测到img ")
//        println("匹配</view>的次数   num1 = ${num1} 匹配<view 的次数  num2 = ${num2}")
//        num1 -= num2
//        println(" 过滤掉相互匹配之后  num1 = ${num1}")
//    }
}

public static int appearNumber(String srcText, String findText) {
    int count = 0;
    Pattern p = Pattern.compile(findText);
    Matcher m = p.matcher(srcText);
    while (m.find()) {
        count++;
    }
    return count;
}