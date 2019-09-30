package com.slt.documentmanagment.repository;

import com.slt.documentmanagment.model.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<OauthClientDetails,Long> {

}
