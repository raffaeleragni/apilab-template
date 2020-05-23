#!/bin/bash
set -xe

#
# Requirements to be installed for this to work:
# - bash
# - docker
# - docker-compose
# Environment required for this to work:
# - docker must be already logged in for pushing the image
# - ~/.kube/config must contain the configuration for deployment when this script starts
# - environment variables required:
#   - DOCKER_REPO_AND_IMAGE, the full path to where to push the built image
# - optional environment:
#   - APP_PREFIX, contextual prefix to permit parallel builds
#   - SDKMAN_SDK, the sdk to use using sdkman
#

DIRNAME="$(dirname $0)"

APP_PREFIX=${APP_PREFIX:-"application"}
SDKMAN_SDK=${SDKMAN_SDK:-"14.0.1.hs-adpt"}
DOCKER_REPO_AND_IMAGE=${DOCKER_REPO_AND_IMAGE:-"unknown"}

GIT_TAG=${GIT_TAG:-$(git describe --exact-match --tags HEAD || true)}





# ========== #



wait_for_postgres() {
  # wait for postgres
  until docker-compose exec -T db psql -U postgres -c "select 1" -d postgres; do
    sleep 1
  done
}

build_application() {
  if [ ! -d ~/.sdkman ]; then curl -s "https://get.sdkman.io" | bash; fi
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk install java ${SDKMAN_SDK} || true
  sdk use java ${SDKMAN_SDK}

  ./mvnw install
}

docker_build_and_push() {
  local IMAGE="${APP_PREFIX}-tmpimage"
  local APP_VERSION=${GIT_TAG:-"unknown"}
  local FINAL_IMAGE_TAGGED=${DOCKER_REPO_AND_IMAGE}:${GIT_TAG}
  local FINAL_IMAGE_LATEST=${DOCKER_REPO_AND_IMAGE}:latest
  docker build --build-arg APP_VERSION=${APP_VERSION} -t ${IMAGE} .
  if [ ! -z "$GIT_TAG" ]; then
    docker tag ${IMAGE} ${FINAL_IMAGE_TAGGED}
    docker tag ${IMAGE} ${FINAL_IMAGE_LATEST}
    docker push ${FINAL_IMAGE_TAGGED}
    docker push ${FINAL_IMAGE_LATEST}
    docker rmi ${FINAL_IMAGE_TAGGED}
    docker rmi ${FINAL_IMAGE_LATEST}
  fi
  docker rmi ${IMAGE}
}

deploy() {

  if [ -z "$GIT_TAG" ]; then
    return
  fi

  cat > Chart.yaml <<EOF
apiVersion: v2
name: app
description: app microservice
type: application

appVersion: ${GIT_TAG}
version: ${GIT_TAG}.1
EOF

  docker run --rm \
    -v $PWD/helm-chart:/helm-chart \
    -v $HOME/.kube:/root/.kube \
      dtzar/helm-kubectl \
        helm upgrade app-template /helm-chart

}



# ========== #



# ensure latest images are downloaded
docker-compose pull

# start with a fresh environment
docker-compose down
docker-compose up -d
wait_for_postgres

# build and push
build_application
# stop env
docker-compose down
# push image
docker_build_and_push

# clean up orphan images
yes|docker container prune
yes|docker volume prune

# deploy in case there is a tag
deploy
