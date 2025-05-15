FROM bellsoft/liberica-openjdk-alpine

# install curl, jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# add test files
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

# run the test
#ENTRYPOINT java -cp 'libs/*' \
#    -Dselenium.grid.enabled=true \
#    -Dselenium.grid.hubHost=${hubHost} \
#    -Dbrowser=${BROWSER} \
#    org.testng.TestNG \
#    -threadcount ${THREAD_COUNT} \
#    test-suites/${TEST_SUITE}
ENTRYPOINT sh runner.sh