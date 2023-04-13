package yt.company.carwash.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.OrderBase;
import yt.company.carwash.services.OrderBaseService;

import javax.print.attribute.standard.JobKOctets;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderBaseService orderBaseService;


    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderBaseService.getAllOrders());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) {
        try {

            return ResponseEntity.ok(orderBaseService.getOrder(id));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createOrder(@RequestParam(name = "client_id") Long clientId, @RequestParam(name = "company_id") Long companyId, @RequestParam(name = "city_id") Long cityId, @RequestParam(name = "service_type_id") Long serviceId, @RequestParam(name = "vehicle_type_id") Long vehicleId, @RequestParam(name = "time") Timestamp timestamp) {

        try {
            orderBaseService.createOrder(clientId, companyId, cityId, serviceId, vehicleId, timestamp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteOrder(@RequestParam Long id) {
        orderBaseService.deleteOrder(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateOrder(@RequestParam Long id) {
        try {
            orderBaseService.changeStatus(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
