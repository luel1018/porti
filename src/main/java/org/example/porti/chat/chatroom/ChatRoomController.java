package org.example.porti.chat.chatroom;

import lombok.RequiredArgsConstructor;
import org.example.porti.user.model.AuthUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/create/{guestUserIdx}")
    public ResponseEntity create(@AuthenticationPrincipal AuthUserDetails hostUser, @PathVariable Long guestUserIdx) {
        return ResponseEntity.ok().body(chatRoomService.save(hostUser.getIdx(), guestUserIdx));
    }
}
