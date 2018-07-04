
FROM hub.fenxibao.com/base/jre:8u77

WORKDIR /test
COPY target /test/target

EXPOSE 8080
CMD ["java", "-Xms256m", "-Xmx256m", "-jar", "target/k8s-registrator-1.0.0.RELEASE.jar"]