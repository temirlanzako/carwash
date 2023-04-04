package yt.company.carwash.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Client;
import yt.company.carwash.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserService userService;

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /*public void createClient(Long id, Client client) {
        Client c = new Client();
        c.setUser(userService.getUser(id));
        c.setTypeOfCars(client.getTypeOfCars());
        c.setFullName(client.getFullName());
        clientRepository.save(c);
    }*/

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
}
