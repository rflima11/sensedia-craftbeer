FROM openjdk:8
LABEL maintainer="rodolfo.lima.ferreira11@gmail.com"
EXPOSE 9000
ADD target/craft-beer.jar craft-beer.jar
ENTRYPOINT ["java", "-jar", "craft-beer.jar"]