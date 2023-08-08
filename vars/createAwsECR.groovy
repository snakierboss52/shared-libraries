def call(String project){
    try{
        sh "aws ecr create-repository --repository-name $project"
    }
    catch(e){
        echo 'repo already exists'
    }
}