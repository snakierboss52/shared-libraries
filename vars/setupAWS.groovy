def call(String aws_key_id, String aws_secret_key, String region) {
    sh """aws --profile default configure set aws_access_key_id $aws_key_id
    aws --profile default configure set aws_secret_access_key $aws_secret_key
    aws configure set region $region
    """                     
}