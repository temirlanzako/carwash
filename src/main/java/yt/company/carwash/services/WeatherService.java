package yt.company.carwash.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import yt.company.carwash.weatherApi.Temperature;
import yt.company.carwash.weatherApi.WeatherData;

import java.util.Objects;

@Service
public class WeatherService {
    private final String API_KEY = "7de2567026265f9be03283d38275a6e0";
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherData getWeatherData(String cityName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + "?q=" + cityName + "&appid=" + API_KEY)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = Objects.requireNonNull(response.body()).string();
            System.out.println(responseBody);

            ObjectMapper objectMapper = new ObjectMapper();
            WeatherData weatherData = objectMapper.readValue(responseBody, WeatherData.class);
            double toCelsius = weatherData.getTemperature().getTemp() - 273.15;
            Temperature temperature = new Temperature();
            temperature.setTemp(Math.round(toCelsius*100.0)/100.0);
            weatherData.setTemperature(temperature);

            return weatherData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
