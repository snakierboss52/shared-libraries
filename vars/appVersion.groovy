def call(rollback=false, sufix='') {
    dir("code/${env.PROJECT_NAME}") {
        if (rollback == false) {
            try {
                if (sufix ==''){
                    getTag = sh(script: 'git describe', returnStdout: true).trim()
                }else{
                    getTag = sh(script: 'git describe', returnStdout: true).trim() +'-'+ sufix
                }
            } catch (Exception e) {
                echo 'This repository does not contain any tag'
                if (sufix ==''){
                    getTag = '0.0.0'
                }else{
                    getTag = '0.0.0' +'-'+ sufix
                }
            }
        } else {
            try {
                consultTag = sh(script: 'git tag -l --sort=-version:refname', returnStdout: true).trim().split('\n')
                getTag = consultTag[1]
            } catch (Exception e) {
                error 'This repository does not contain more tags tag for rollback'
            }
        }
    }

    return getTag
}