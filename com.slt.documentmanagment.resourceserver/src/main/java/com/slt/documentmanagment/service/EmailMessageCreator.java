package com.slt.documentmanagment.service;

import org.springframework.stereotype.Service;

@Service
public class EmailMessageCreator {

    public String createVerificationMessage(String username, String password){
        String message = "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body aria-readonly=\"false\"><span style=\"font-family:courier new,courier,monospace\"><u><strong>SLT DMS USER MANAGMENT&nbsp;</strong></u></span><br />\n" +
                "<br />\n" +
                "<span style=\"color:#0000CD\">Account has been Created.</span>\n" +
                "<ul>\n" +
                "\t<li>User Name : "+username+"</li>\n" +
                "\t<li>Password : "+password+"</li>\n" +
                "</ul>\n" +
                "</body>\n" +
                "</html>\n" ;
        return message;
    }

    public String createPasswordChangedMessage(String username, String password){
        String message = "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "</head>\n" +
                "<body aria-readonly=\"false\"><span style=\"font-family:courier new,courier,monospace\"><u><strong>SLT DMS USER MANAGMENT&nbsp;</strong></u></span><br />\n" +
                "<br />\n" +
                "<span style=\"color:#0000CD\">Account has been Changed.</span>\n" +
                "<ul>\n" +
                "\t<li>User Name : "+username+"</li>\n" +
                "\t<li>Password : "+password+"</li>\n" +
                "</ul>\n" +
                "</body>\n" +
                "</html>\n" ;
        return message;
    }

}