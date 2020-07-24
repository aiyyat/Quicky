sbt compile
sbt dist
rm -rf svc
set -x && unzip -d svc target/universal/*-1.0-SNAPSHOT.zip && mv svc/*/* svc/ && rm svc/bin/*.bat && mv svc/bin/* svc/bin/start
rm -rf svc/RUNNING_PID
