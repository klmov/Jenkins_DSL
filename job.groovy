def STUDENT_NAME = "kklimov"
def GITHUB_REPOSITORY = ""
def GITHUB_BRANCH = ""

def script = "
def dick = 'pen'
println dick
"
job('example') {
    label("EPBYMINW2033")
    configure {
    project->
        project / 'properties' << 'hudson.model.ParametersDefinitionProperty' {
        parameterDefinitions {
            'com.cwctravel.hudson.plugins.extended__choice__parameter.ExtendedChoiceParameterDefinition' {
                name 'BUILD_JOBS'
                quoteValue 'false'
                saveJSONParameterToFile 'false'
                visibleItemCount '15'
                type 'PT_CHECKBOX'
                groovyScript "${script}"
                multiSelectDelimiter ','
                projectName "dwer"
            }
        }
    }
}
}
