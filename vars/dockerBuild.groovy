def build() {
    sh "docker build ./ --force-rm -t ${ECR_REPO}:${TAG}"
}

def removeLocalImage() {
    //Remove image from the local agent
    sh "docker rmi ${ECR_REPO}:${TAG}"
}


def pullToECR(String repoEcr = env.ECR_REPO) {
    //Pull the image of ECR
    sh "docker pull ${repoEcr}:${TAG}"
}

def tagToECR(String oldRepoEcr = env.ECR_REPO, String repoEcr = env.ECR_REPO) { 
    //Tag the image of ECR
    sh "docker tag ${oldRepoEcr}:${TAG} ${repoEcr}:${TAG}"
}

def buildNetworkHost() {
    sh "docker build ./ --force-rm -t ${ECR_REPO}:${TAG} --network=host"
}

def pushToECR() {
    //Push the image to ECR
    sh "docker push ${ECR_REPO}:${TAG}"
}