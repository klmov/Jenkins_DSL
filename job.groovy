def STUDENT_NAME = "kklimov"
def GITHUB_REPOSITORY = ""
def GITHUB_BRANCH = ""

job('example') {
    label("EPBYMINW2033")
    parameters {
        booleanParam('FLAG', true)
        choiceParam('OPTION', ['option 1 (default)', 'option 2', 'option 3'])
    }
}
