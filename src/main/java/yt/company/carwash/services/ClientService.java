package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.WebClient;
import yt.company.carwash.repository.CityRepository;
import yt.company.carwash.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserService userService;

    private final CityRepository cityRepository;

    public WebClient getClient(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public List<WebClient> getAllClients() {
        return clientRepository.findAll();
    }

    public WebClient createClient(String name, String surname, String phone, Long cityId, Long userId) {
        WebClient client = clientRepository.findByPhone(phone);
        if(client == null) {
            WebClient account = new WebClient();
            account.setUser(userService.getUser(userId));
            account.setCity(cityRepository.findById(cityId).orElseThrow(()->new EntityNotFoundException("City not found")));
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
    /*public Client updateClient(Client client) {
        return clientRepository.save(client);
    }*/
}
