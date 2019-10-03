package com.slt.documentmanagment.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.*;

public class CustomAuthoritiesExtractor implements AuthoritiesExtractor {

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(asAuthorities(map));
    }

    private String asAuthorities(Map<String, Object> map) {

        List<String> authorities = new ArrayList<>();
        String  authoritiesString= map.get("authorities").toString();
        authoritiesString = authoritiesString.substring(1, authoritiesString.length() - 1);
        authoritiesString = authoritiesString.trim();
        return authoritiesString;
    }
}