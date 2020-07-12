# Test task tourist telegram bot

Test task tourist telegram bot is a Spring Boot application.
<hr>

Implementation Stack:
- Maven
- Spring Boot
- Spring Data JPA
- Spring MVC
- Hibernate
- Telegram Bot Java Library
- Lombok
- h2 Database

Bot credentials:

botUserName: `@tourist_123_bot`  
botToken: `1207957789:AAG1fipOxYHfi7LGuVInORe2hgGX4FBcRFw`  
webHookPath: `{your-domain}`
<hr>

### Run the bot locally

Follow the steps to get it runs in local.

**Run ngrok**  
Download ngrok from [here](https://ngrok.com/download).  
Run it from the command line:  
`ngrok.exe http 8080`  
It'll return a custom ***your-domain***.

**Configure the Application**  
Before run this application you usually have to add your-domain to the [application.properties](src\main\resources\application.properties) file.
`telegrambot.webHookPath={your-domain}`

After that you have to set your-domain using api.telegram
`https://api.telegram.org/bot1207957789:AAG1fipOxYHfi7LGuVInORe2hgGX4FBcRFw/setWebhook?url={your-domain}`

You can run the application from your IDE, or you can run it from the command line:

```
git clone https://github.com/dzhin1993/testTask.git
cd testTask
mvn compile
mvn spring-boot:run
```

### Use the Application

#### Talk with the bot

<hr>
When you type a city name to your bot (http://localhost:8080) the Spring Boot application will try to poll your messages and send appropriate answers to the chat with your bot.
<hr>

#### curl samples for crud operations via rest api
You can manage cities data (CRUD operations) here: http://localhost:8080/admin/cities.

##### get All cities
`curl -s http://localhost:8080/admin/cities`

##### get city 1
`curl -s http://localhost:8080/admin/cities/1`

##### delete city 5
`curl -s -X DELETE http://localhost:8080/admin/cities/5`

##### create city
`curl -s -X POST -d '{"city":"new city", "message":"new city created"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/admin/cities`

##### update city 1
`curl -s -X PUT -d '{"city":"updated", "message":"city updated"}' -H 'Content-Type: application/json' http://localhost:8080/admin/cities/1`