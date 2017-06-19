long time = System.currentTimeMillis();
println("开始读取数据")
File file = new File('ss.txt')
String pre = "toHexString = ";
int i = 0;
StringBuilder sb = new StringBuilder();
file.eachLine { value ->
    if (value != null && value != '' && value.contains(pre)) {
        value = value.trim();
//        println(" pre.idx ="+value.indexOf(pre)+" pre.length() = "+pre.length())
        int idx = value.indexOf("=");
        int idx2 = value.indexOf("falg");
        int idx3 = value.indexOf(pre) + pre.length();
        println(value)
        try {
            String s;
            if (idx2 > 0)
                s = value.substring(idx3, idx2 - 1);
            else s = value.substring(idx3)
            i++;
            sb.append(s+",");
            println("idx = " + idx + " idx2 = " + idx2 + " idx3 = " + idx3 + " s =" + s + " s.len = " + s.length())

        } catch (Exception e) {
            e.printStackTrace()
            println(" idx3 = " + idx3 + " idx2-1 = " + (idx2 - 1) + " " + e.getMessage())
        }
    }
}
println("result i = "+i+" sb = "+sb)
println("读取完毕 耗时 " + (System.currentTimeMillis() - time))