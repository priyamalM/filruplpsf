package com.slt.documentmanagment.service;

import com.slt.documentmanagment.ClientDto;
import com.slt.documentmanagment.model.OauthClientDetails;

import java.util.List;

public interface ClientService {

    List<ClientDto> findAllClients();
    ClientDto saveClient(ClientDto clientDto);
    ClientDto editOauthClient(ClientDto clientDto);
    void deleteClient(long id);
    ClientDto getClientDtoFromClient(OauthClientDetails clientDetails);
    OauthClientDetails getOauthClientFromClientDto(ClientDto clientDto);

}