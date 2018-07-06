def label = "mypod-${UUID.randomUUID().toString()}"
podTemplate(label: label,
	containers: [
	  containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat'),
	  containerTemplate(name: 'docker', image: 'docker:stable', ttyEnabled: true, command: 'cat'),
	  containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.8.0', command: 'cat', ttyEnabled: true)
	],
	volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]
	) {
	
	node(label) {
		
	  
	  stage('Get Source') {
	  	git "https://github.com/jconallen/cloud-poc-app.git"
	  }

	  def props = readProperties  file:'deployment/pipeline.properties'
	  def tag = props['version']
	  def gitSrc = props['gitSrc']
	  def dockerRegistry = props['dockerRegistry']
	  def image = props['image']
	  def deployment = props['deployment']
	  def config = props['config']
	  def service = props['service']
	  def selector = props['selector']
	  def namespace = props['namespace']
	  def jar = props['jar']


	  stage('Build Maven project') {
		container('maven') {
			sh "mvn -B -DskipTests clean package"
		}
	  }
	  stage('Build Docker image') {
		container('docker') {
		  docker.withRegistry("${dockerRegistry}", 'icp-id') {
			sh "docker build --build-arg JAR_FILE=${jar} -t ${image}:${tag} ."
			sh "docker push ${image}:${tag}"
			sh "docker tag ${image}:${tag} ${image}:latest"
			sh "docker push ${image}:latest"
		  }
		}
	  }
	  
	  stage( 'Deploy to Cluster' ) {
		container('kubectl') {
		  withCredentials([[$class: 'UsernamePasswordMultiBinding',
							  credentialsId: 'icp-id',
							  usernameVariable: 'DOCKER_HUB_USER',
							  passwordVariable: 'DOCKER_HUB_PASSWORD']]) {
			  sh "kubectl delete deployments -n ${namespace} --selector=${selector}"
			  sh "sleep 20"
			  sh "kubectl apply -n ${namespace} -f ${config} -f ${deployment}"
			  sh "sleep 20"
			  sh "kubectl apply -n ${namespace} -f ${service}"
		  }
		}
	  }
	}
  }
