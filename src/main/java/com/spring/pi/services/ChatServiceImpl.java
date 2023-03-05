package com.spring.pi.services;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.Conversation;
import com.spring.pi.entities.Generic_Message;
import com.spring.pi.entities.Specification;
import com.spring.pi.payload.request.ConversationRequest;
import com.spring.pi.payload.request.GenerateConversationRequest;
import com.spring.pi.payload.request.deleteMessageRequest;
import com.spring.pi.payload.response.ConversationResponse;
import com.spring.pi.payload.response.MessageResponse;
import com.spring.pi.repositories.ActorRepository;
import com.spring.pi.repositories.ConversationRepository;
import com.spring.pi.repositories.Generic_MessageRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements IchatService{
    ConversationRepository conversationRepository;
    ActorRepository actorRepository;
    Generic_MessageRepository genericMessageRepository;

    @Override
    public Conversation generateConversationToActors(GenerateConversationRequest generateConversationRequest) {
        Conversation c=new Conversation();
        c.setDate(new Date());
        conversationRepository.save(c);
        for (Long id:generateConversationRequest.getIdactors()) {
            Actor a=actorRepository.findById(id).orElse(null);

            c.getActors().add(a);
            conversationRepository.save(c);
        }

        return c;
    }

    @Override
    public Conversation addActorToConversation(Long idActor, Long idConv) {
        Actor a=actorRepository.findById(idActor).orElse(null);
        Conversation c=conversationRepository.findById(idConv).orElse(null);
        c.getActors().add(a);
        conversationRepository.save(c);
        return c;
    }

    @Override
    public void removeActorFromConversation(Long idActor, Long idConv) {
        Actor a=actorRepository.findById(idActor).orElse(null);
        Conversation c=conversationRepository.findById(idConv).orElse(null);
        c.getActors().remove(a);
        conversationRepository.save(c);
    }


    @Override
    public ConversationResponse removeConversation(Long idConv) {
        Conversation c= conversationRepository.findById(idConv).orElse(null);

        //disjoin messages from users
        for (Generic_Message message:c.getGeneric_Messages()) {
            if (message.getSpecification()==(Specification.MESSAGE)){

                message.setActor(null);
            }
        }
        //deleting messages of conversation
        c.setGeneric_Messages(new HashSet<Generic_Message>());
        conversationRepository.save(c);


        //creating response Conversation
        ConversationResponse cr=new ConversationResponse();
        cr.setDate(c.getDate());
        cr.setMessages(c.getGeneric_Messages());
        return cr;
    }

    @Override
    public ConversationResponse getConversationMessages(Long idConv) {

        Set<Generic_Message> messages=new HashSet<Generic_Message>();

        //getting conversation messages from Generic Messages
        Conversation c= conversationRepository.findById(idConv).orElse(null);
        for (Generic_Message message:c.getGeneric_Messages()) {
            if (message.getSpecification()==(Specification.MESSAGE)){
                messages.add(message);
            }
        }
        //creating response Conversation
        ConversationResponse cr=new ConversationResponse();
        cr.setDate(c.getDate());
        cr.setMessages(messages);
        return cr;
    }

    @Override
    public ConversationResponse sendMessage(ConversationRequest cr) {
        // sender of message
        Actor sender = actorRepository.findById(cr.getIdSender()).orElse(null);
        //message generation
        Generic_Message message=new Generic_Message();
        message.setContent(cr.getContent());
        message.setSpecification(Specification.MESSAGE);
        message.setActor(sender);
        message.setDate(LocalDateTime.now());
        message.setSeen(Boolean.FALSE);
        //conversation of message
        Conversation c=conversationRepository.findById(cr.getIdConversation()).orElse(null);
        //joining
        genericMessageRepository.save(message);

        c.getGeneric_Messages().add(message);
        conversationRepository.save(c);
        return getConversationMessages(c.getId());
    }

    @Override
    public ConversationResponse deleteMessage(deleteMessageRequest dr) {
        // deleter of message
        Actor deleter = actorRepository.findById(dr.getIdDeleter()).orElse(null);
        //message to delete
        Generic_Message message=genericMessageRepository.findById(dr.getIdMessage()).orElse(null);
        //conversation of message
        Conversation c=conversationRepository.findById(dr.getIdConversation()).orElse(null);
        //deleting
        c.getGeneric_Messages().remove(message);
        conversationRepository.save(c);
        genericMessageRepository.delete(message);

        return getConversationMessages(c.getId());

    }
    public ConversationResponse seeMessages(Long idConv){

        Set<Generic_Message> messages=new HashSet<Generic_Message>();

        //getting conversation messages from Generic Messages
        Conversation c= conversationRepository.findById(idConv).orElse(null);
        for (Generic_Message message:c.getGeneric_Messages()) {
            if (message.getSpecification()==(Specification.MESSAGE)&&message.getSeen()==Boolean.FALSE){
                message.setSeen(Boolean.TRUE);

                message.setSeenDate(LocalDateTime.now());



            }
            genericMessageRepository.save(message);
        }
        return getConversationMessages(idConv);

    }

    @Override
    public ConversationResponse respondMessages() {
        return null;
    }

}
