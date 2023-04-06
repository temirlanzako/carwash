package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OneToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.*;
import yt.company.carwash.repository.CityRepository;
import yt.company.carwash.repository.OrderRepository;
import yt.company.carwash.repository.VehicleTypeRepository;
import yt.company.carwash.repository.VehicleWashTypeRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final CompanyService companyService;

    private final CityRepository cityRepository;

    private final VehicleTypeRepository vehicleTypeRepository;

    private final VehicleWashTypeRepository vehicleWashTypeRepository;

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    public void changeStatus(Long id) {
        Order order = getOrder(id);
        if(order.getIsCompleted().equals(Boolean.FALSE)) {
            order.setIsCompleted(Boolean.TRUE);
        } else {
            order.setIsCompleted(Boolean.FALSE);
        }
        orderRepository.save(order);
    }
    public void createOrder(Long clientId, Long companyId, Long cityId, Long serviceId, Long vehicleId, Date date) {
        Order order = new Order();
        order.setClient(clientService.getClient(clientId));
        order.setCompany(companyService.getCompany(companyId));
        order.setCity(cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City not found")));
        order.setServiceType(vehicleWashTypeRepository.findById(serviceId).orElseThrow(()-> new EntityNotFoundException("ServiceType not found")));
        order.setVehicleType(vehicleTypeRepository.findById(vehicleId).orElseThrow(()->new EntityNotFoundException("VehicleType not found")));
        order.setDate(date);
        order.setIsCompleted(Boolean.FALSE);
        orderRepository.save(order);
    }
}
