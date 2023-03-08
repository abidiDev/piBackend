package com.spring.pi.controllers;


import com.spring.pi.entities.Actor;
import com.spring.pi.entities.Conversation;
import com.spring.pi.payload.request.ConversationRequest;
import com.spring.pi.payload.request.GenerateConversationRequest;
import com.spring.pi.payload.request.deleteMessageRequest;
import com.spring.pi.payload.response.ConversationResponse;
import com.spring.pi.services.IchatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    IchatService ichatService;

    @PostMapping("/generateConversationToActors")
    @ResponseBody
    public Conversation generateConversationToActors(@RequestBody GenerateConversationRequest generateConversationRequest){
        return ichatService.generateConversationToActors(generateConversationRequest);
    }
    @GetMapping("/addActorToConversation/{idActor}/{idConv}")
    public Conversation addActorToConversation(@PathVariable Long idActor,@PathVariable Long idConv){
        return ichatService.addActorToConversation(idActor,idConv);

    }
    @DeleteMapping("/removeActorFromConversation/{idActor}/{idConv}")

    public void removeActorFromConversation(@PathVariable Long idActor,@PathVariable Long idConv){
        ichatService.removeActorFromConversation(idActor,idConv);
    }

    @DeleteMapping("/removeConversation/{idConv}")

    public ConversationResponse removeConversation(@PathVariable Long idConv){
       return  ichatService.removeConversation(idConv);
    }

    @GetMapping("/getConversationMessages/{idConv}")

    public ConversationResponse getConversationMessages(@PathVariable Long idConv){
        return ichatService.getConversationMessages(idConv);
    }
    @PostMapping("/sendMessage")
    @ResponseBody
    public ConversationResponse sendMessage(@RequestBody ConversationRequest cr){
        return ichatService.sendMessage(cr);
    }
    @PostMapping("/deleteMessage")
    @ResponseBody
    public ConversationResponse deleteMessage(@RequestBody deleteMessageRequest dr){
        return  ichatService.deleteMessage(dr);
    }
    @GetMapping("/seeMessages/{idConv}")

    public ConversationResponse seeMessages(@PathVariable Long idConv){
        return  ichatService.seeMessages(idConv);
    }


}
