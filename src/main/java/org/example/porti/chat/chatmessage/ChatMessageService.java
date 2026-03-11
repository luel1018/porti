package org.example.porti.chat.chatmessage;

import lombok.RequiredArgsConstructor;
import org.example.porti.chat.chatmessage.model.ChatMessage;
import org.example.porti.chat.chatmessage.model.ChatMessageDto;
import org.example.porti.chat.chatroom.ChatRoomRepository;
import org.example.porti.chat.chatroom.model.ChatRoom;
import org.example.porti.user.UserRepository;
import org.example.porti.user.model.User;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatMessageDto.Res saveMessage(ChatMessageDto.Send req, Long senderIdx) {
        ChatRoom room = chatRoomRepository.findById(req.getRoomIdx()).orElseThrow(() -> new MessageDeliveryException("Invalid ChatRoom"));
        User sender = userRepository.findById(senderIdx).orElseThrow(() -> new MessageDeliveryException("Invalid Sender"));

        ChatMessage chatMessage = req.toEntity(room, sender);
        return ChatMessageDto.Res.from(chatMessageRepository.save(chatMessage));
    }

    public List<ChatMessageDto.Res> messages(Long roomIdx) {
        List<ChatMessage> messages = chatMessageRepository.findAllByChatRoomIdxOrderByCreatedAtAsc(roomIdx);
        return messages.stream().map(ChatMessageDto.Res::from).toList();
    }
}
