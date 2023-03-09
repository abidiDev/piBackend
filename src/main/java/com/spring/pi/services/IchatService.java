package com.spring.pi.services;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.Conversation;
import com.spring.pi.entities.Generic_Message;
import com.spring.pi.payload.request.ConversationRequest;
import com.spring.pi.payload.request.GenerateConversationRequest;
import com.spring.pi.payload.request.deleteMessageRequest;
import com.spring.pi.payload.response.ConversationResponse;

import java.util.List;

public interface IchatService {
    public Conversation generateConversationToActors(GenerateConversationRequest generateConversationRequest);
    public Conversation addActorToConversation(Long idActor, Long idConv);
    public void removeActorFromConversation(Long idActor, Long idConv);

    public ConversationResponse removeConversation(Long idConv);


    public ConversationResponse getConversationMessages(Long idConv);
    public ConversationResponse sendMessage(ConversationRequest cr);
    public ConversationResponse deleteMessage(deleteMessageRequest dr);
    public ConversationResponse seeMessages(Long idConv);

    public ConversationResponse respondMessages();


}
