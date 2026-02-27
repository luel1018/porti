package org.example.porti.namecard.model;

import lombok.Builder;
import lombok.Getter;

public class NamecardDto {

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
