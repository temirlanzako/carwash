package yt.company.carwash.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.Client;
import yt.company.carwash.services.OrderBaseService;
import yt.company.carwash.services.WebClientService;
import yt.company.carwash.services.WeatherService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {


    private final WebClientService webClientService;
    private final WeatherService weatherService;

    private final OrderBaseService orderBaseService;

    @GetMapping(value = "{id}")
    public Client getClient(@PathVariable Long id) {
        return webClientService.getClient(id);
    }


    @GetMapping
    public List<Client> getAllClients() {
        return webClientService.getAllClients();
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        webClientService.deleteClient(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(value = "/create{id}")
    public ResponseEntity<Object> createClient(@RequestParam String name,
                                               @RequestParam String surname,
                                               @RequestParam String phone,
                                               @RequestParam Long cityId,
                                               @PathVariable(name = "id") Long userId) {

        return ResponseEntity.ok(webClientService.createClient(name, surname, phone, cityId, userId));
    }
    @GetMapping(value = "/weather/{london}")
    public ResponseEntity<Object> getWeather(@PathVariable String london)  {

        return ResponseEntity.ok(weatherService.getWeatherData(london));
    }
    @PostMapping(value = "/reserve/{companyId}")
    public ResponseEntity<?> makeReservation(@PathVariable Long companyId,
                                             @RequestParam(name = "client_id") Long clientId,
                                             @RequestParam(name = "city_id") Long cityId,
                                             @RequestParam(name = "service_type_id") Long serviceId,
                                             @RequestParam(name = "vehicle_type_id") Long vehicleId,
                                             @RequestParam(name = "time") Timestamp timestamp) {
        try {
            orderBaseService.createOrder(clientId, companyId, cityId, serviceId, vehicleId, timestamp);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
   /* @PutMapping(value = "/update")
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }*/
}
