package org.example.porti.portfolio.model;

import lombok.*;
import org.example.porti.section.model.Section;
import org.example.porti.section.model.SectionDto;

import java.util.ArrayList;
import java.util.List;

public class PortfolioDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Req {
        private String title;
        private String period;
        private String role;

        private List<SectionDto.Req> sectionList;

        public Portfolio toEntity() {
            return Portfolio.builder()
                    .title(this.title)
                    .period(this.period)
                    .role(this.role)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Res {
        private Long idx;
        private String title;
        private String period;
        private String role;

        private List<SectionDto.Res> sectionList;

        public static Res from(Portfolio entity) {
            List<SectionDto.Res> sectionDto = new ArrayList<>();

            return Res.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .period(entity.getPeriod())
                    .role(entity.getRole())
                    .sectionList(entity.getSectionList().stream().map(SectionDto.Res::from).toList())
                    .build();
        }
    }
}
