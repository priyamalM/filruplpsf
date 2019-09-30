package com.slt.documentmanagment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "com.slt.documentmanagment.model.OauthClientDetails")
@Table(name = "oauth_client_details")
public class OauthClientDetails {

  @Id
  @Column(name = "\"client_id\"", nullable = false)
  private String clientId;
  @Column(name = "\"resource_ids\"", nullable = true)
  private String resourceIds;
  @Column(name = "\"client_secret\"", nullable = true)
  private String clientSecret;
  @Column(name = "\"scope\"", nullable = true)
  private String scope;
  @Column(name = "\"authorized_grant_types\"", nullable = true)
  private String authorizedGrantTypes;
  @Column(name = "\"web_server_redirect_uri\"", nullable = true)
  private String webServerRedirectUri;
  @Column(name = "\"authorities\"", nullable = true)
  private String authorities;
  @Column(name = "\"access_token_validity\"", nullable = true)
  private Integer accessTokenValidity;
  @Column(name = "\"refresh_token_validity\"", nullable = true)
  private Integer refreshTokenValidity;
  @Column(name = "\"additional_information\"", nullable = true)
  private String additionalInformation;
  @Column(name = "\"autoapprove\"", nullable = true)
  private String autoapprove;
}