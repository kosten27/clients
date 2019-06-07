package com.kostenko.services;

import com.kostenko.models.Client;
import com.kostenko.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    private ClientRepository clientRepository;

    @Before
    public void setUp() throws Exception {
        clientRepository = mock(ClientRepository.class);
    }

    @Test
    public void findAllTest() {
        //GIVEN
        Client client = new Client();
        client.setFirstName("Test");
        client.setLastName("Test");
        List<Client> expectedClients = new ArrayList<>();
        expectedClients.add(client);
        when(clientRepository.findAll()).thenReturn(expectedClients);

        //WHEN
        List<Client> clients = clientRepository.findAll();

        //THEN
        assertEquals(expectedClients.size(), clients.size());
    }

    @Test
    public void saveClientTest() {
        //GIVEN
        Client expectClient = new Client();
        expectClient.setId(1L);
        expectClient.setFirstName("Test");
        expectClient.setLastName("Test");
        when(clientRepository.save(any())).thenReturn(expectClient);

        //WHEN
        Client client = clientRepository.save(expectClient);

        //THEN
        assertEquals(expectClient.getId(), client.getId());
    }
}