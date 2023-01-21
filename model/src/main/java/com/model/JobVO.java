package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long jobId;

    private Byte[] data;

    private Date createdAt;

    private Date updatedAt;
}

