## 1 fase Build

#construir la base de la aplicacion
FROM eclipse-temurin:21-jdk AS builder

# 2 creamos carpeta de trabajo del docker
WORKDIR /app

# 3 se copia todo el proyecto a desde el pc al contenedor
COPY  . .

# 4 ejecuta maven dentro del contenedor y no ejecuta test  
RUN ./mvnw clean package -DskipTests 

#### 2 fase Runtime 
FROM eclipse-temurin:21-jre 

# definir carpeta de trabajo
WORKDIR /app

# copia el jar desde la fase de build a la fase runtime
COPY --from=builder /app/target/*.jar app.jar

# expone el puerto deseado
EXPOSE 8081

# comando que se hace iniciar el docker
ENTRYPOINT [ "java","-jar","app.jar" ]