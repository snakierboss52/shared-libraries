def call() {
    sh 'rm -rf ~/.aws/credentials'
    sh 'rm -rf ~/.aws/config'
}