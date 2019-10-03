package com.slt.documentmanagment.configuration;

public interface ApplicationRoles {

        /* Application Roles */
        public static final String ADMIN = "ROLE_admin";
        public static final String USER = "ROLE_user";

        /* Application Scopes */
        public static final String READSCOPE = "#oauth2.hasScope('read')";
}