package com.spring.pi.payload.request;

import com.spring.pi.entities.Generic_Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationRequest {
    private Long idSender;
    private Long idConversation;
    private String content;

}
