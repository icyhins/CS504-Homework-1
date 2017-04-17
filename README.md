# Running Information Analysis Service
```
This project is Silver Kan's homework of BitTiger CS504. It's a DEMO base on the user story about simple upload/query running user information.
```

## Framework reference
```
Spring Framework
Spring Boot
Spring Data JPA
Lombok
MySQL
Docker
```

## How to get source
```
git clone  https://github.com/icyhins/CS504-Homework-1.git
cd CS504-Homework-1
```
## How to build
```
mvn clean install
```

## Run Docker & Database
### Run MySQL Docker
```
docker-compose up -d
```
### Login DB
DB console
```
mysql --host=127.0.0.1 --port=3306 --user=root --password=root
```
Create database for the first time
```
create database running_information_analysis_db;
```
Exit DB console
```
\q
```

## Run Spring Application
```bash
java -jar ./target/running-information-analysis-service-1.0.0.BUILD-SNAPSHOT.jar
```

## Request Example

Upload JSON Data (with POST method)
```
http://localhost:9000/upload
```
Delete All Data
```
http://localhost:9000/delete/all
```
Delete by running ID
```
http://localhost:9000/delete/id/{runningId}
```
Query By Heart Rate
```
http://localhost:9000/query/heartRate/{heartRate}?page=0&size=2
page: optional, default 0
size: optional, default 20
```
Query By Heart Rate greater than
```
http://localhost:9000/query/heartRateGreaterThan/{heartRate}?page=0&size=2
page: optional, default 0
size: optional, default 20
```
Query All Order by health level
```
http://localhost:9000/query/all?page=0&size=2
page: optional, default 0
size: optional, default 20
```

## LICENSE
MIT
