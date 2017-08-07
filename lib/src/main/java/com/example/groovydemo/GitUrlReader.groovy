def file = new File('D:\\studioJob')
if (file != null && file.exists() && file.canRead()) {
    List dir = traversqlDir(file)
//    dir.each { value ->
//        generateGitClone(analysisFile(value))
//    }
}

List traversqlDir(File dir) {
    def filekey = "config"
    def ignoreList = [".gradle", "gradle", "build", "app", ".idea", "src"]
    def list = []
    if (dir.isFile() && dir.getName().endsWith(filekey)) {
        list.add(dir)
        generateGitClone(analysisFile(dir))
    } else {
        dir.eachFile { File file ->
            if (file.isFile() && file.getName().endsWith(filekey)) {
                list.add(file)
                generateGitClone(analysisFile(file))
            } else if (file.isDirectory() && !ignoreList.contains(file.getName())) {
                traversqlDir(file)
            }
        }
    }
    return list
}

String analysisFile(File file) {
    def lines = file.readLines()
//    print("max lines $lines.indices.size() ")
    for (i in lines.indices) {
        def value = lines.get(i).trim()
        if (value.startsWith("url")) {
            value = value.substring(value.indexOf("=") + 1).trim()
            return value
        }
//        print(i)
    }
}

void generateGitClone(String value) {
    if (value == null || value.length() == 0) return
    println("git clone $value")
}