def STUDENT_NAME = "kklimov"
def GITHUB_REPOSITORY = ""
def GITHUB_BRANCH = ""

job('example') {
    label("EPBYMINW2033")
    parameters {
      extensibleChoiceParameterDefinition {
        name("JOBS")
        editable(false)
        description("Choose job") 
        choiceListProvider{
          systemGroovyChoiceListProvider {
            groovyScript {
              script('["job1", "job2"]')
              sandbox(false)
            }
            defaultChoice("job1")
            usePredefinedVariables(true)
          }
        }
      }
    }
}
