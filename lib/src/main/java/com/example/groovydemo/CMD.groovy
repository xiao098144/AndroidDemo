def cmd = "cd "
def path = "D:\\CI"
def execute = cmd.execute(null, new File(path))
execute.waitFor()
print(execute)