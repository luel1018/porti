package org.example.porti.namecard.model;

import lombok.Builder;
import lombok.Getter;

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
                    .build();
        }
    }


    @Getter
    @Builder
    public static class Register{
        private String title;
        private String layout;
        private String color;
        private Long user;

        public Namecard toEntity(){
            return Namecard.builder()
                    .title(this.title)
                    .layout(this.layout)
                    .color(this.color)
                    .build();
        }
    }
}
