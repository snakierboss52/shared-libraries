def call(manualCheck = true) {
    // Login to ECR
    sh "aws ecr get-login-password --region ${REGION} | docker login --username AWS --password-stdin ${SKEEMA_IMAGE}"

    //Pull golang image from dockerhub
    sh "docker pull ${SKEEMA_IMAGE}"

    //Add the configuration file for skeema
    sh "aws --region=${REGION} ssm get-parameter --name ${MIGRATIONS_ENVPARAMETER} --output text --query Parameter.Value > .skeema"
    withDockerContainer(image: "${SKEEMA_IMAGE}", args: "-e XDG_CACHE_HOME='/tmp/.cache'") {
        //Run migrations based on parameters
        COMMAND = params.ALLOW_UNSAFE ? '--allow-unsafe' : ''
        sh "skeema diff ${ENVIRONMENT} ${COMMAND} || :"
        if (manualCheck) {
            input id: 'Skeema', message: 'Push changes?'
        }
        sh "skeema push ${ENVIRONMENT} ${COMMAND}"
    }
}
