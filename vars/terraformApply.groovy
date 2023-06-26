def call(manualCheck=false) {

    // Remove .terraform folder
    sh 'rm -rf .terraform'

    //Run terraform init
    sh """terraform init -input=false -no-color\
        -backend-config "bucket=$BUCKET" \
        -backend-config "region=$TF_VAR_region"
        """

    //Run terraform plan and create a tfplan file
    sh "terraform plan -out=tfplan -input=false -no-color"

    // Manual check for terraform apply
    if (manualCheck) {
        input id: 'terraform-apply', message: 'Apply changes?'
    }

    //Run terraform apply
    sh "terraform apply -input=false tfplan -no-color"
}