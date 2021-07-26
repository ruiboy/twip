package net.vixim.twip.weather.openweathermap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.vixim.twip.weather.CityForecast;

public class JsonUtilTest
{
  @Test
  public void test_convertCityForecastJson_2rows()
  {
    String json = "{\n"
        + "  \"cod\": \"200\",\n"
        + "  \"message\": 0,\n"
        + "  \"cnt\": 40,\n"
        + "  \"list\": [\n"
        + "    {\n"
        + "      \"dt\": 1627225200,\n"
        + "      \"main\": {\n"
        + "        \"temp\": 12.51,\n"
        + "        \"feels_like\": 12.14,\n"
        + "        \"temp_min\": 11.9,\n"
        + "        \"temp_max\": 12.51,\n"
        + "        \"pressure\": 1014,\n"
        + "        \"sea_level\": 1014,\n"
        + "        \"grnd_level\": 1008,\n"
        + "        \"humidity\": 89,\n"
        + "        \"temp_kf\": 0.61\n"
        + "      },\n"
        + "      \"weather\": [\n"
        + "        {\n"
        + "          \"id\": 500,\n"
        + "          \"main\": \"Rain\",\n"
        + "          \"description\": \"light rain\",\n"
        + "          \"icon\": \"10n\"\n"
        + "        }\n"
        + "      ],\n"
        + "      \"clouds\": {\n"
        + "        \"all\": 75\n"
        + "      },\n"
        + "      \"wind\": {\n"
        + "        \"speed\": 6.56,\n"
        + "        \"deg\": 302,\n"
        + "        \"gust\": 12.93\n"
        + "      },\n"
        + "      \"visibility\": 10000,\n"
        + "      \"pop\": 0.33,\n"
        + "      \"rain\": {\n"
        + "        \"3h\": 0.11\n"
        + "      },\n"
        + "      \"sys\": {\n"
        + "        \"pod\": \"n\"\n"
        + "      },\n"
        + "      \"dt_txt\": \"2021-07-25 15:00:00\"\n"
        + "    },\n"
        + "    {\n"
        + "      \"dt\": 1627236000,\n"
        + "      \"main\": {\n"
        + "        \"temp\": 12.05,\n"
        + "        \"feels_like\": 11.69,\n"
        + "        \"temp_min\": 11.14,\n"
        + "        \"temp_max\": 12.05,\n"
        + "        \"pressure\": 1014,\n"
        + "        \"sea_level\": 1014,\n"
        + "        \"grnd_level\": 1007,\n"
        + "        \"humidity\": 91,\n"
        + "        \"temp_kf\": 0.91\n"
        + "      },\n"
        + "      \"weather\": [\n"
        + "        {\n"
        + "          \"id\": 803,\n"
        + "          \"main\": \"Clouds\",\n"
        + "          \"description\": \"broken clouds\",\n"
        + "          \"icon\": \"04n\"\n"
        + "        }\n"
        + "      ],\n"
        + "      \"clouds\": {\n"
        + "        \"all\": 73\n"
        + "      },\n"
        + "      \"wind\": {\n"
        + "        \"speed\": 5.72,\n"
        + "        \"deg\": 322,\n"
        + "        \"gust\": 12.47\n"
        + "      },\n"
        + "      \"visibility\": 10000,\n"
        + "      \"pop\": 0.03,\n"
        + "      \"sys\": {\n"
        + "        \"pod\": \"n\"\n"
        + "      },\n"
        + "      \"dt_txt\": \"2021-07-25 18:00:00\"\n"
        + "    }\n"
        + "  ],\n"
        + "  \"city\": {\n"
        + "    \"id\": 2078025,\n"
        + "    \"name\": \"Adelaide\",\n"
        + "    \"coord\": {\n"
        + "      \"lat\": -34.9333,\n"
        + "      \"lon\": 138.6\n"
        + "    },\n"
        + "    \"country\": \"AU\",\n"
        + "    \"population\": 1074159,\n"
        + "    \"timezone\": 34200,\n"
        + "    \"sunrise\": 1627249485,\n"
        + "    \"sunset\": 1627286352\n"
        + "  }\n"
        + "}";

    List<CityForecast> res = JsonUtil.convertCityForecastJson(json);

    assertEquals(2, res.size());

    CityForecast cf;

    cf = res.get(0);
    assertEquals("Adelaide", cf.getCityName());
    assertEquals("AU", cf.getCountryCode());
    assertEquals(new Timestamp(1627225200l * 1000), cf.getTimestamp()); // JSON has epoch time secs, java wants millis
    assertEquals(13, cf.getTemperature());
    assertEquals("Rain", cf.getWeatherMain());

    cf = res.get(1);
    assertEquals("Adelaide", cf.getCityName());
    assertEquals("AU", cf.getCountryCode());
    assertEquals(new Timestamp(1627236000l * 1000), cf.getTimestamp()); // JSON has epoch time secs, java wants millis
    assertEquals(12, cf.getTemperature());
    assertEquals("Clouds", cf.getWeatherMain());
  }

  @Test
  public void test_convertCityForecastJson_null()
  {
    String json = null;
    assertThrows(RuntimeException.class, () -> JsonUtil.convertCityForecastJson(json));
  }

  @Test
  public void test_convertCityForecastJson_empty()
  {
    String json = "{}";
    assertThrows(RuntimeException.class, () -> JsonUtil.convertCityForecastJson(json));
  }

  @Test
  public void test_convertCityForecastJson_junk()
  {
    String json = "{ rubbish junk ";
    assertThrows(RuntimeException.class, () -> JsonUtil.convertCityForecastJson(json));
  }
}
