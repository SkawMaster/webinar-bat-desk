#!/bin/bash
set +e
available=no
while [ $available == "no" ]
 do
  oc get pods -l deploymentconfig=deployment-bat-desk --config ./config --output='jsonpath={.items[*].metadata.name}' | grep "No resources found" || available=yes
done
sleep 10
oc get pods -l deploymentconfig=deployment-bat-desk --config ./config --output='jsonpath={.items[*].metadata.name}'
