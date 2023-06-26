 def call(body) {
     
    //Create folders for Terraform and project code
    sh "mkdir -p terraform"
    codedir = SINGLE_PROJECT == 'true' ? "code/${PROJECT_NAME}" : 'code'
    sh "mkdir -p ${codedir}"

    //Clone Terraform repository
    dir("terraform"){
        git branch: INFRA_BRANCH, url: INFRA_REPOSITORY
    }

    //Clone code repository in a new directory 
    dir(codedir){   
        git branch: CODE_BRANCH, url: CODE_REPOSITORY
    }
 }