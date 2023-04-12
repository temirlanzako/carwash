package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.*;
import yt.company.carwash.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderBaseService {

    private final OrderBaserepository orderBaserepository;
    private final WebClientService webClientService;
    private final CompanyService companyService;
    private final CityRepository cityRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleWashTypeRepository vehicleWashTypeRepository;

    private final CompanyRepository companyRepository;
    public OrderBase getOrder(Long id) {
        return orderBaserepository.findById(id).orElseThrow();
    }
    public List<OrderBase> getAllOrders() {
        return orderBaserepository.findAll();
    }


    public void deleteOrder(Long id) {
        orderBaserepository.deleteById(id);
    }
    public void changeStatus(Long id) {
        OrderBase orderBase = getOrder(id);
        if(orderBase.getIsCompleted().equals(Boolean.FALSE)) {
            orderBase.setIsCompleted(Boolean.TRUE);
        } else {
            orderBase.setIsCompleted(Boolean.FALSE);
        }
        orderBaserepository.save(orderBase);
    }
    public void createOrder(Long clientId, Long companyId, Long cityId, Long serviceId, Long vehicleId, Timestamp timestamp) {
        if(hasAvailableBoxes(companyId, timestamp) == Boolean.TRUE) {
            OrderBase orderBase = new OrderBase();
            Company company = companyService.getCompany(companyId);
            orderBase.setClient(webClientService.getClient(clientId));
            orderBase.setCompany(company);
            orderBase.setCity(cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City not found")));
            orderBase.setServiceType(vehicleWashTypeRepository.findById(serviceId).orElseThrow(() -> new EntityNotFoundException("ServiceType not found")));
            orderBase.setVehicleType(vehicleTypeRepository.findById(vehicleId).orElseThrow(() -> new EntityNotFoundException("VehicleType not found")));
            orderBase.setTimestamp(timestamp);
            orderBase.setIsCompleted(Boolean.FALSE);
            orderBaserepository.save(orderBase);
        } else {
            throw new IllegalArgumentException("No available boxes in specified time");
        }
    }
    public List<OrderBase> getCompanyOrders(Long companyId) {
        List<OrderBase> orderBases = getAllOrders();
        List<OrderBase> companyOrderBases = new ArrayList<>();
        for(OrderBase ord : orderBases) {
            if(ord.getCompany().getId() == companyId) {
                companyOrderBases.add(ord);
            }
        }
        return companyOrderBases;
    }
    public Boolean hasAvailableBoxes(Long companyId, Timestamp timestamp) {
        List<OrderBase> companyOrders = getCompanyOrders(companyId);
        Company company = companyService.getCompany(companyId);
        int capacity = company.getCapacity();
        int count = 0;
        for(OrderBase order : companyOrders) {
            if(order.getTimestamp().equals(timestamp) && !(order.getIsCompleted())) {
                count++;
            }
            if(count >= capacity ){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
