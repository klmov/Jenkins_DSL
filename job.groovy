def STUDENT_NAME = "kklimov"
def GITHUB_REPOSITORY = ""
def GITHUB_BRANCH = ""

job('example') {
    label("EPBYMINW2033")
    parameters {
      activeChoiceReactiveParam("JOBS") {
        description('Allows user choose from multiple choices')
        filterable()
        choiceType('CHECKBOX')
        groovyScript {
          script('["job1", "job2"]')
        }
      }
    }
}
