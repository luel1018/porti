package org.example.porti.namecard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.porti.user.model.AuthUserDetails;
import org.example.porti.user.model.User;

public class NamecardDto {
    @Getter
    @Builder
    public static class List{
        private Long idx;
        private String title;
        private String layout;
        private String color;
        private Long user;

        public static NamecardDto.List toDto(Namecard entity){
            return List.builder()
                    .idx(entity.getIdx())
                    .color(entity.getColor())
                    .title(entity.getTitle())
                    .layout(entity.getLayout())
                    .user(entity.getUser().getIdx())
                    .build();
        }
    }


    @Getter
    @Builder
    public static class Register{
        private String title;
        private String layout;
        private String color;

        public Namecard toEntity(){
            return Namecard.builder()
                    .title(this.title)
                    .layout(this.layout)
                    .color(this.color)
                    .build();
        }
    }
}
