package org.example.porti.section.model;

import lombok.*;

public class SectionDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Req {
        private String sectionTitle;
        private String contents;
        private Integer sectionOrder;

        public Section toEntity() {
            return Section.builder()
                    .sectionTitle(this.sectionTitle)
                    .contents(this.contents)
                    .sectionOrder(this.sectionOrder)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Res {
        private Long idx;
        private String sectionTitle;
        private String contents;
        private Integer sectionOrder;

        public static Res from(Section entity) {
            return Res.builder()
                    .idx(entity.getIdx())
                    .sectionTitle(entity.getSectionTitle())
                    .contents(entity.getContents())
                    .sectionOrder(entity.getSectionOrder())
                    .build();
        }
    }
}
