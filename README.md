# TWIP
A simple java / react app that allows you to plan a trip of multiple city stops
and find the weather for each stop using openweathermap API 5 day forecast.

# Running it

Backend: You must set system property `openweathermap.appId`.  Eg. Run with:

```
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dopenweathermap.appId=<your-app-id-here>"
```

Frontend: Run with:

```
npm install
npm start
```

Point your browser at `http://localhost:3000`