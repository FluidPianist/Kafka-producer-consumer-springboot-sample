Some Configurations are different from the ones in docker-compose. Check Properly

For ConfigMap :
1. First Create the ConfigMap and Secrets before deploying
2. apply the configmap and the spring-app.yaml add the confRef property
3. Configs can also be mounted inside the pod as volume. we will see how to do that using secrets

For Secrets :
1. First create the secret : kubectl create secret generic <secret-name> --from-file=secret/
2. add the volume and volume mounts property in spring-app.yaml


For Deploying our spring-boot application in minikube :
1. eval $(minikube docker-env)
2. build the image in the same repo using the docker file : docker build . -t firstapp:v1
3. Apply the spring-app.yaml with imagePullPolicy set as never
4. the service created will be of type loadBalancer, which defines that it is meant to be acessed 
    so external ip will show pending.

For Accessing the service : 
1. for port forwarding to local host :
   1. kubectl port-forward service/spring-app-service 8080:8080
   2. hit http://localhost:8080/api/v1/kafka/publish_hello?username=adeeb&message=hello_from_docker_secured
   
2. Using Ingress :
   1. Apply the demo-ingress with correct port number
   2. run minikube tunnel
   3. Run kubectl get svc to check the external ip assigned to the spring-app-service
   4. Go to /etc/host file in linux and add :  127.0.0.1    	k8s.springboot.demo
   5. If you want to assign a different external ip - change this field : kubernetes.docker.internal to your desired ip and restart minikube
   6. hit http://k8s.springboot.demo/api/v1/kafka/publish_hello?username=adeeb&message=hello_via_ingress

    
Reference :
1. Guide: https://www.lydtechconsulting.com/blog-kafka-kubernetes-demo-pt1.html
2. Repo: https://github.com/lydtechconsulting/kafka-kubernetes-demo/tree/v1.0.0