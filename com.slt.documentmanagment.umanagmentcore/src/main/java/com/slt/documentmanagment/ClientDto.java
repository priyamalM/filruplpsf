package com.slt.documentmanagment;

import lombok.Data;

@Data
public class ClientDto {
    private String clientId;
    private String resourceIds;
    private String scope;
    private String passWord;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
