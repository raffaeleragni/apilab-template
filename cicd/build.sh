#!/bin/bash -x

DOCKER_REPO_AND_IMAGE="repository:9999/path/image"

GIT_TAG=$(git describe --exact-match --tags HEAD)
if [ ! -z "$GIT_TAG" ]; then
  echo "Detected git tag: $GIT_TAG"
fi

# start up the components for integration
docker-compose up -d
# databases need a lot of time to startup and docker-compose returns in an async way
sleep 5

# build, test, create artifact
./mvnw install
BERR=$?

# compose is only needed for integration tests so get rid of it as soon as possible
docker-compose down

if [ $BERR -ne 0 ]; then
  exit $BERR
fi

# Build the image

set -e

docker build --build-arg APP_VERSION=${GIT_TAG} -t tmpimage .
if [ ! -z "$GIT_TAG" ]; then
  docker tag tmpimage ${DOCKER_REPO_AND_IMAGE}:${GIT_TAG}
  docker tag tmpimage ${DOCKER_REPO_AND_IMAGE}:latest
  docker push ${DOCKER_REPO_AND_IMAGE}:${GIT_TAG}
  docker push ${DOCKER_REPO_AND_IMAGE}:latest
  docker rmi ${DOCKER_REPO_AND_IMAGE}:${GIT_TAG}
  docker rmi ${DOCKER_REPO_AND_IMAGE}:latest
fi
docker rmi tmpimage
