def STUDENT_NAME = "kklimov"
def GITHUB_REPOSITORY = ""
def GITHUB_BRANCH = ""

job('example') {
    label("EPBYMINW2033")
    parameters {
      booleanParam('FLAG', true)
      activeChoiceParam("JOBS") {
        choiceType('CHECKBOX')
        groovyScript {
          script('["job1", "job2"]')
        }
      }
    }
}
