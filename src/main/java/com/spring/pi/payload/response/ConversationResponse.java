package com.spring.pi.payload.response;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.Conversation;
import com.spring.pi.entities.Generic_Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {
    private Date date ;
    private Set<Generic_Message> messages = new LinkedHashSet<>();




}
