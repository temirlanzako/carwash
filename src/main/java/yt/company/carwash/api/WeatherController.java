package yt.company.carwash.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yt.company.carwash.services.WeatherService;

@RestController
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping(value = "/{city_name}")
    public ResponseEntity<Object> getWeather(@PathVariable(name="city_name") String cityName) {
        return ResponseEntity.ok(weatherService.getWeatherData(cityName));
    }
}
