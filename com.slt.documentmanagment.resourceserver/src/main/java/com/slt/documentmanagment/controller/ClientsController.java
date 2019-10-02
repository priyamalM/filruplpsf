package com.slt.documentmanagment.controller;

import com.slt.documentmanagment.ClientDto;
import com.slt.documentmanagment.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clients")
@RestController
@Api(tags = {"Register Applications In The SSO."})
public class ClientsController {

    @Autowired
    ClientService clientsService;

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> findAllClients(){
        List<ClientDto> allClients = clientsService.findAllClients();
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto clientDto){
        ClientDto savedClient = clientsService.saveClient(clientDto);
        return new ResponseEntity<>(savedClient,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDto> editClient(@RequestBody ClientDto clientDto){
        clientDto = clientsService.editOauthClient(clientDto);
        return new ResponseEntity<>(clientDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") long id){
        clientsService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
