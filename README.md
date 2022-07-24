# Spring Boot - Travel calculation project by geographic location (latitude, longitude)

![](https://github.com/tolgacelik22/spring-boot-travel-calculation-project-by-geographic-location/raw/main/src/main/resources/image.PNG)

### Description
  ##### This project was made to calculate the distance with the coordinate information sent according to the geographical location.
    - This project has been prepared using the spring boot framework.
    - PostgreSQL was used as the database.
    - Redis was used as the cache system.
    - Swagger is used as RestApi interface.

### Project Features:
  - Makes travel records according to location information,
  - It logs in when you come close to registered stores or warehouses.
  - Calculates the total distance traveled.

### Setup:
- **Assuming you have docker installed on your computer!**
  - If it is not installed, you need to install docker by following the document https://docs.docker.com/compose/gettingstarted/
- Clone the project.
- Go to `\src\main\resources` with terminal.
- Run `docker-compose up -d` to install PostgreSQL, postgreAdmin and Redis.
- Run the project.
####
**Note:** If you want to use the project with historical data, you can find the backup file in sql format in `\src\main\resources` 
  - To restore from backup;
  - Go to `\src\main\resources` and open terminal there.
  - Run this command: `cat backup.sql | docker exec -i postgresql_postgreadmin_db_1 psql -U admin`
  - Command description
  - `cat <myfile>.sql | docker exec -i <my-container-name> psql -U <my-postgre-username>`
#####
- PostgreAdmin Login Information:
  - Login link: localhost:5050
  - User: pgadmin4@pgadmin.org
  - Password: admin1234
  - Database: javadb
### Use:
- The project is set to run on localhost:8080.
- You can examine the project using the Swagger API interface with the `localhost:8080/swagger-ui/index.html` link on the browser.
- With the `localhost:8080/user-travels` link, you can record with the following sample data as raw/json data in the body via postman.
### Example Data:
    {
    "courierId" : 5,
    "storeId": 1,
    "lat": 33.2213501,
    "lng": 22.126484,
    "time": "14-07-2022 16:44:45"
    }

- When you send a get request to `localhost:8080/user-travels` link, you can see all traveled distance records.
- With the example link `localhost:8080/user-travel-data/user/5` (by changing the ID value at the end), you can list the total distance traveled by a traveler in kilometers.

### Resources
- https://start.spring.io/
- https://redis.io/
- https://hub.docker.com/
- https://www.postgresql.org/
- https://www.pgadmin.org/
- https://www.postman.com/
- https://www.geodatasource.com
- http://springfox.github.io/springfox/docs/current/
- https://swagger.io/
- and of course https://stackoverflow.com/

    
