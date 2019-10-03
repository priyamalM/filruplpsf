package com.slt.documentmanagment.controller;

import com.slt.documentmanagment.ClientDto;
import static com.slt.documentmanagment.configuration.ApplicationRoles.*;
import com.slt.documentmanagment.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequestMapping("/clients")
@RestController
@Api(tags = {"Register Applications In The SSO."})
public class ClientsController {

    @Autowired
    ClientService clientsService;

    @GetMapping("/all")
    @RolesAllowed({ADMIN})
    public ResponseEntity<List<ClientDto>> findAllClients(){
        List<ClientDto> allClients = clientsService.findAllClients();
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({ADMIN})
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto clientDto){
        ClientDto savedClient = clientsService.saveClient(clientDto);
        return new ResponseEntity<>(savedClient,HttpStatus.CREATED);
    }

    @PutMapping
    @RolesAllowed({ADMIN})
    public ResponseEntity<ClientDto> editClient(@RequestBody ClientDto clientDto){
        clientDto = clientsService.editOauthClient(clientDto);
        return new ResponseEntity<>(clientDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @RolesAllowed({ADMIN})
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id){
        clientsService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
