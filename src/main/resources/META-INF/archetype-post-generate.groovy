import java.nio.file.Path
import java.nio.file.Paths

// the properties available to the archetype
Properties properties = request.properties

// language is either java or kotlin (default)
String language = properties.get("language")

removeUnwantedFiles(language, "main")
removeUnwantedFiles(language, "test")

def removeUnwantedFiles(String language, String scope) {
    String appModulePath = getModulePath()
    String packagePath = getPackagePath()
    for (File file : new File(appModulePath + "/src/$scope/$language/$packagePath/").listFiles()) {
        if (!file.getName().endsWith(getExtensionForLanguage(language)) && !file.delete()) {
            throw new IOException();
        }
    }
}

String getModulePath() {
    Path projectPath = Paths.get(request.outputDirectory, request.artifactId)
    String appModule = request.artifactId + "-app"
    return "${projectPath.toString()}/$appModule"
}

String getPackagePath() {
    Properties properties = request.properties
    String packageName = properties.get("package")
    return packageName.replace(".", "/")
}


static String getExtensionForLanguage(String language) {
    if (language == "kotlin") return "kt"
    else return "java"
}
