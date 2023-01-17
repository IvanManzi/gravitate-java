package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetailsV0 {

    private String recipient;
    private String recipientFirstName;
    private String recipientLastName;
    private String password;
    private String msgBody;
    private String subject;
    private String attachment;
}

