:: Start Kafka
kubectl create ns kafka
kubectl create ns keda-sample
kubectl apply -f ../strimzi-0.14.0/install/cluster-operator/
kubectl apply -f ../strimzi-0.14.0/install/cluster-operator/020-RoleBinding-strimzi-cluster-operator.yaml
kubectl apply -f ../strimzi-0.14.0/install/cluster-operator/032-RoleBinding-strimzi-cluster-operator-topic-operator-delegation.yaml
kubectl apply -f ../strimzi-0.14.0/install/cluster-operator/031-RoleBinding-strimzi-cluster-operator-entity-operator-delegation.yaml
kubectl apply -f kafka-strimzi.yml -n keda-sample