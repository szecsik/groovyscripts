import groovy.json.JsonSlurper

GroovyShell shell = new GroovyShell()
def tools = shell.parse(new File('functions.groovy'))

def yepyep = "yepsep"


def yepmap = ["a":"b", "b":"v"]

def yekyep = {
    "yekyek ${it}"
}

def yekMeg (name){
    return "klkl ${name}"
}

def yepFunct (List<String>list){
    return list.collect {it.toUpperCase()}
}

def yeplist = ["klk","asd","fff"]


Map<String, String> testmap = new HashMap<>()

testmap.put("kikis","kik")
testmap.put("kikis2","kik")
testmap.put("kikis3","kik")

yepFunct(yeplist).each {
    println it
}

println(testmap.kikis)


tools.greet()

File jsonFile = new File("C:/Users/Krisztián/Downloads/1.json")


JsonSlurper jsonSlurper = new JsonSlurper();
Map<String, String> jsonresp = jsonSlurper.parse(jsonFile)


println jsonresp.id