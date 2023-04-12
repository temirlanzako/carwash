package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Client;
import yt.company.carwash.repository.CityRepository;
import yt.company.carwash.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final ClientRepository clientRepository;
    private final UserService userService;

    private final CityRepository cityRepository;

    @Lazy
    @Autowired
    private OrderBaseService orderBaseService;

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(String name, String surname, String phone, Long cityId, Long userId) {
        Client client = clientRepository.findByPhone(phone);
        if (client == null) {
            Client account = new Client();
            account.setUser(userService.getUser(userId));
            account.setCity(cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City not found")));
            account.setPhone(phone);
            account.setName(name);
            account.setSurname(surname);
            clientRepository.save(account);
            return account;
        } else {
            throw new IllegalArgumentException("User with this phone number is already exist");
        }
    }
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
