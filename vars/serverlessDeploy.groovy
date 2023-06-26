def call(String environment, String region, String aws_key, String aws_secret, domainCreate=false) {
    script {
        sh "serverless config credentials --provider aws --key $aws_key --secret $aws_secret"
        if (domainCreate){
            sh "serverless create_domain --stage $environment --region $region"
        }
        sh "serverless deploy --stage $environment --region $region"
    }
}