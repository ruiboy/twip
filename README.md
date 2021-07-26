# TWIP
A simple java / react app that allows you to plan a trip of multiple city stops
and find the weather for each stop using openweathermap API 5 day forecast.

# Running it

Need to put a valid https://openweathermap.org/current APPID
  in file `src/main/resources/application.yml`: 
  
```
twip:
  weather:
    cityForecast:
      appId: <your-app-id-here>
```

Then run with `mvn spring-boot:run`
