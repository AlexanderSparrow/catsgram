FROM amazoncorretto:21
LABEL authors="alexandergorobets"
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#ENV CATSGRAM_IMAGE_DIRECTORY = "images"
