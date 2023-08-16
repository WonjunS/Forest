package com.example.forest.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.forest.dto.chat.ChatMessageCreateDto;
import com.example.forest.model.ChatMessage;
import com.example.forest.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StompChatController {
	
	private final ChatService chatService;
	
	private final SimpMessagingTemplate template;
	
	@MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageCreateDto message){
		log.info("enter()");
        message.setMessage(message.getLoginId() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageCreateDto message){
    	log.info("message(message = {})", message);
    	
    	chatService.create(message);
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
    
    @MessageMapping("/sub/chat/room/{roomId}")
    @SendTo("/pub/chat/room/{roomId}")
    public List<ChatMessage> handleMessages(List<ChatMessage> messages) {	
        return messages;
    }

}
