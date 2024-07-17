package controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Client> getClients(@PathVariable long id) {
        log.info("Поиск клиента по идентификатору {}", id);

        Optional<Client> client = clientService.findById(id);

        return clientService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@Validated @RequestBody Client client) throws URISyntaxException {
        log.info("Добавление клиента {}", client.getClientName());

        long id = clientService.save(client);

        return ResponseEntity
                .created(new URI("http://localhost:8080/clients/" + id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") long id) {
        log.info("Удаление клиента по идентификатору {}", id);

        boolean isDeleted = clientService.deleteById(id);

        return isDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
