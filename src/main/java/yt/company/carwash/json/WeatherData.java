package yt.company.carwash.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("weather")
    private List<Weather> weather;
}
