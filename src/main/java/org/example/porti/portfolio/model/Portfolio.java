package org.example.porti.portfolio.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.porti.section.model.Section;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title; // 프로젝트 제목
    private String period; // 진행 기간
    private String role; // 프로젝트에서 맡은 역할

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Section> sectionList = new ArrayList<>();

}
