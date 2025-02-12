FROM openjdk:17
EXPOSE 9080
ADD target/DevopsApp.jar DevopsApp.jar 
ENTRYPOINT ["java", "-jar", "DevopsApp.jar"]