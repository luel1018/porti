package org.example.porti.chat.chatroom.model;

import lombok.Builder;
import lombok.Getter;
import org.example.porti.user.model.User;

import java.util.Date;

public class ChatRoomDto {
    public static ChatRoom toEntity(User hostUser, User guestUser) {
        return ChatRoom.builder()
                .hostUser(hostUser)
                .guestUser(guestUser)
                .build();
    }

    @Getter
    @Builder
    public static class CreateRes {
        Long idx;
        Long hostUserIdx;
        Long guestUserIdx;
        Date createdAt;
        Date updatedAt;

        public static CreateRes from(ChatRoom entity) {
            return CreateRes.builder()
                    .idx(entity.getIdx())
                    .hostUserIdx(entity.getHostUser().getIdx())
                    .guestUserIdx(entity.getGuestUser().getIdx())
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(entity.getUpdatedAt())
                    .build();
        }
    }


}
