package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.OrderBase;
import yt.company.carwash.models.WebClient;
import yt.company.carwash.repository.CityRepository;
import yt.company.carwash.repository.WebClientRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final WebClientRepository webClientRepository;
    private final UserService userService;

    private final CityRepository cityRepository;

    @Lazy
    @Autowired
    private OrderBaseService orderBaseService;

    public WebClient getClient(Long id) {
        return webClientRepository.findById(id).orElseThrow();
    }

    public List<WebClient> getAllClients() {
        return webClientRepository.findAll();
    }

    public WebClient createClient(String name, String surname, String phone, Long cityId, Long userId) {
        WebClient client = webClientRepository.findByPhone(phone);
        if (client == null) {
            WebClient account = new WebClient();
            account.setUser(userService.getUser(userId));
            account.setCity(cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City not found")));
            account.setPhone(phone);
            account.setName(name);
            account.setSurname(surname);
            webClientRepository.save(account);
            return account;
        } else {
            throw new IllegalArgumentException("User with this phone number is already exist");
        }
    }
    public void deleteClient(Long id) {
        webClientRepository.deleteById(id);
    }
}
