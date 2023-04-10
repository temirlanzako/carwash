package yt.company.carwash.weatherApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("main")
    private Temperature temperature;
    @JsonProperty("weather")
    private List<Weather> weather;
}
