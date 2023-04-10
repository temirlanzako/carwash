package yt.company.carwash.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.WebClient;
import yt.company.carwash.services.ClientService;
import yt.company.carwash.services.WeatherService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final WeatherService weatherService;

    @GetMapping(value = "{id}")
    public WebClient getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @GetMapping
    public List<WebClient> getAllClients() {
        return clientService.getAllClients();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(value = "/create{id}")
    public ResponseEntity<Object> createClient(@RequestParam String name,
                                               @RequestParam String surname,
                                               @RequestParam String phone,
                                               @RequestParam Long cityId,
                                               @PathVariable(name = "id") Long userId) {
        return ResponseEntity.ok(clientService.createClient(name, surname, phone, cityId, userId));
    }
    @GetMapping(value = "/weather/{london}")
    public ResponseEntity<Object> getWeather(@PathVariable String london) throws IOException {

        return ResponseEntity.ok(weatherService.getWeatherData(london));
    }
   /* @PutMapping(value = "/update")
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }*/

}
