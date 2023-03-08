package com.spring.pi.controllers;

import com.spring.pi.entities.Comment;
import com.spring.pi.services.IServiceComment;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/CRUD")
public class ControllerComment {
    IServiceComment iServiceComment;

    @PostMapping("/AddCommentAndasginToAds/{idAds}")
    @PreAuthorize("hasRole('USER') ")
    @ResponseBody
    public Comment AddCommentAndasginToAds(@PathVariable long idAds,@RequestBody Comment comment) {
        return iServiceComment.AddCommentAndasginToAds(idAds,comment);
    }


}
