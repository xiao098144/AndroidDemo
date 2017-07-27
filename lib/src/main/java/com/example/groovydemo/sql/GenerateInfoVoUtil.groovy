//生成 VO 与  JavaBean 互相转换方法
File file = new File('./')
//println("read current file dir $file " + file.exists() + " " + file.canRead() + " " + file.path)
if (file.exists() && file.canRead()) {
    //匹配指定名称的文件
    file.eachFileMatch(~/Tys.*VO\.java/) { File value ->
//        println("eachFileMatch  value is $value")
    }


}