def call(String REGION, String CONTAINER){
    sh """
    aws ecr get-login-password --region $REGION | docker login --username AWS --password-stdin $CONTAINER
    """
}