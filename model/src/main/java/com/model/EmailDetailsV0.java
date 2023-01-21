package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetailsV0 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String recipient;
    private String recipientFirstName;
    private String recipientLastName;
    private String password;
    private String msgBody;
    private String subject;
    private String attachment;
}

