package com.spring.pi.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class deleteMessageRequest {
    private Long idDeleter;
    private Long idConversation;
    private Long idMessage;
}
