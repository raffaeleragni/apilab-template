#!/bin/bash
DIRNAME=$(dirname $0)
CHARTPATH=$(realpath --relative-to=${PWD} ${DIRNAME}/../helm-chart)
CHARTPATH_FULL=$(realpath ${DIRNAME}/../helm-chart)
GIT_TAG=$1
GIT_TAG=${GIT_TAG:-$(git describe --exact-match --tags HEAD)}
if [ -z "$GIT_TAG" ]; then
  echo "Tag not found either as parameter or in the latest HEAD"
  exit 1
fi

cat > ${CHARTPATH}/Chart.yaml <<EOF
apiVersion: v2
name: app
description: app microservice
type: application

appVersion: ${GIT_TAG}
version: ${GIT_TAG}.1
EOF

docker run --rm \
  -v $CHARTPATH_FULL:/helm-chart \
  -v $HOME/.kube:/root/.kube \
    dtzar/helm-kubectl \
      helm --debug upgrade cluster-domain-api /helm-chart