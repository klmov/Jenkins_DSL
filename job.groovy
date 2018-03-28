def STUDENT_NAME = "kklimov"
def GITHUB_REPOSITORY = "https://github.com/klmov/Jenkins_DSL.git"
def GITHUB_BRANCH = "master"
def Jobs = []
def script = """
def branchApi = new URL("https://api.github.com/repos/MNT-Lab/mntlab-dsl/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi)
def result = []
branches.each {
  result << it.name
}
return result
"""

for (int i = 1; i <5; i++) {
    Jobs << "MNTLAB-${STUDENT_NAME}-child${i}-build-job"
    job("${Jobs.last()}"){
      label("EPBYMINW2033")
      scm {
        git(GITHUB_REPOSITORY, GITHUB_BRANCH)
      }
      steps {
        shell("bash ./script.sh > output.txt && tar -cvzf child${1}-\$BUILD_NUMBER.tar.gz output.txt")
      }
      publishers {
        archiveArtifacts("child${1}-\$BUILD_NUMBER.tar.gz")
      }
   }
}
job("MNTLAB-${STUDENT_NAME}-main-build-job") {
    label("EPBYMINW2033")
    configure {
    project->
        project / 'properties' << 'hudson.model.ParametersDefinitionProperty' {
        parameterDefinitions {
            'com.cwctravel.hudson.plugins.extended__choice__parameter.ExtendedChoiceParameterDefinition' {
                name 'BRANCH'
                quoteValue 'false'
                saveJSONParameterToFile 'false'
                visibleItemCount '15'
                type 'PT_SINGLE_SELECT'
                groovyScript "['master', 'kklimov']"
                defaultValue "'master'"
                multiSelectDelimiter ','
                projectName "dwer"
            }
            'com.cwctravel.hudson.plugins.extended__choice__parameter.ExtendedChoiceParameterDefinition' {
                name 'BUILD_JOBS'
                quoteValue 'false'
                saveJSONParameterToFile 'false'
                visibleItemCount '15'
                type 'PT_CHECKBOX'
                groovyScript "${Jobs}"
                multiSelectDelimiter ','
                projectName "dwer"
            }
        }
    }
  }
  scm {
    git(GITHUB_REPOSITORY, "\$BRANCH")
  }
  steps {
    downstreamParameterized {
              trigger("\$BUILD_JOBS") {
                  block {
                      buildStepFailure('FAILURE')
                      failure('FAILURE')
                      unstable('UNSTABLE')
                  }
              }
    }
    copyArtifacts('\$BUILD_JOBS')
  }
}
