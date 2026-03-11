package org.example.porti.chat.chatmessage;

import lombok.RequiredArgsConstructor;
import org.example.porti.chat.chatmessage.model.ChatMessageDto;
import org.example.porti.user.model.AuthUserDetails;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")
    public void sendMesage(ChatMessageDto.Send req, StompHeaderAccessor accessor) {
        Authentication authentication = (Authentication) accessor.getSessionAttributes().get("user");
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        ChatMessageDto.Res res = chatMessageService.saveMessage(req, user.getIdx());

        messagingTemplate.convertAndSend("/sub/chat/room/" + req.getRoomIdx(), res);
    }


}
