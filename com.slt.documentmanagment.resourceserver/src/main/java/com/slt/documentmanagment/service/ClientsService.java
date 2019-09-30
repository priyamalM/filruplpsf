package com.slt.documentmanagment.service;

import com.slt.documentmanagment.ClientDto;
import com.slt.documentmanagment.model.OauthClientDetails;
import com.slt.documentmanagment.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsService {

    @Autowired
    ClientsRepository clientsRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public List<ClientDto> findAllClients(){
        List<ClientDto> clientDtoList = null;
        List<OauthClientDetails> oauthClientDetails = clientsRepository.findAll();
        if (oauthClientDetails!=null){
            clientDtoList = oauthClientDetails.stream().map(clientDetails -> {
                ClientDto clientDtoFromClient = getClientDtoFromClient(clientDetails);
                return clientDtoFromClient;
            }).collect(Collectors.toList());
        }
        return clientDtoList;
    }

    public ClientDto saveClient(ClientDto clientDto){
        ClientDto savedClientDto = null;
        if (clientDto!=null){
            OauthClientDetails oauthClient = getOauthClientFromClientDto(clientDto);
            oauthClient = clientsRepository.save(oauthClient);
            savedClientDto = getClientDtoFromClient(oauthClient);
        }
        return savedClientDto;
    }

    public ClientDto editOauthClient(ClientDto clientDto){
        ClientDto updatedClient = null;
        if (clientDto!=null){
            OauthClientDetails oauthClient = getOauthClientFromClientDto(clientDto);
            oauthClient = clientsRepository.save(oauthClient);
            updatedClient = getClientDtoFromClient(oauthClient);
        }
        return updatedClient;
    }

    public void deleteClient(long id){
        clientsRepository.deleteById(id);
    }

    public ClientDto getClientDtoFromClient(OauthClientDetails clientDetails){
        ClientDto clientDto = new ClientDto();
        clientDto.setAccessTokenValidity(clientDetails.getAccessTokenValidity());
        clientDto.setAdditionalInformation(clientDetails.getAdditionalInformation());
        clientDto.setAuthorities(clientDetails.getAuthorities());
        clientDto.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes());
        clientDto.setAutoapprove(clientDetails.getAutoapprove());
        clientDto.setRefreshTokenValidity(clientDetails.getRefreshTokenValidity());
        clientDto.setClientId(clientDetails.getClientId());
        clientDto.setResourceIds(clientDetails.getResourceIds());
        clientDto.setScope(clientDetails.getScope());
        clientDto.setWebServerRedirectUri(clientDetails.getWebServerRedirectUri());
        return clientDto;
    }

    public OauthClientDetails getOauthClientFromClientDto(ClientDto clientDto){
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setAccessTokenValidity(clientDto.getAccessTokenValidity());
        oauthClientDetails.setAdditionalInformation(clientDto.getAdditionalInformation());
        oauthClientDetails.setAuthorities(clientDto.getAuthorities());
        oauthClientDetails.setAuthorizedGrantTypes(clientDto.getAuthorizedGrantTypes());
        oauthClientDetails.setAutoapprove(clientDto.getAutoapprove());
        oauthClientDetails.setRefreshTokenValidity(clientDto.getRefreshTokenValidity());
        oauthClientDetails.setClientId(clientDto.getClientId());
        oauthClientDetails.setResourceIds(clientDto.getResourceIds());
        oauthClientDetails.setScope(clientDto.getScope());
        oauthClientDetails.setWebServerRedirectUri(clientDto.getWebServerRedirectUri());
        if (clientDto.getPassWord()!=null){
            oauthClientDetails.setClientSecret(bCryptPasswordEncoder.encode(clientDto.getPassWord()));
        }
        return oauthClientDetails;
    }

}