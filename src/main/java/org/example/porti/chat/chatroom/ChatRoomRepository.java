package org.example.porti.chat.chatroom;

import lombok.RequiredArgsConstructor;
import org.example.porti.chat.chatroom.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
