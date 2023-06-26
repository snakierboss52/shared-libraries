def call() {
    // Remove all unused local volumes.
    sh 'docker volume prune -f'

    // Remove all unused containers, networks and images
    sh 'docker system prune -f'
}