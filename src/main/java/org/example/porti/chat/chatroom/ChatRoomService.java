package org.example.porti.chat.chatroom;

import lombok.RequiredArgsConstructor;
import org.example.porti.chat.chatroom.model.ChatRoom;
import org.example.porti.chat.chatroom.model.ChatRoomDto;
import org.example.porti.user.UserRepository;
import org.example.porti.user.model.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoomDto.CreateRes save(Long hostUserIdx, Long guestUserIdx) {
        User hostUser = userRepository.findById(hostUserIdx).orElseThrow();
        User guestUser = userRepository.findById(guestUserIdx).orElseThrow();
        ChatRoom chatRoom = ChatRoomDto.toEntity(hostUser, guestUser);
        return ChatRoomDto.CreateRes.from(chatRoomRepository.save(chatRoom));
    }
}
