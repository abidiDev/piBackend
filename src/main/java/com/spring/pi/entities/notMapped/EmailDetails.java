package com.spring.pi.entities.notMapped;

import lombok.*;

import java.io.Serializable;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails implements Serializable {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}

