package org.example.porti.portfolio;

import lombok.RequiredArgsConstructor;
import org.example.porti.portfolio.model.PortfolioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/portfolio")
@RestController
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    // 포트폴리오 생성
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody PortfolioDto.Req dto) {
        portfolioService.create(dto);
        return ResponseEntity.ok("성공");
    }

    // 포트폴리오 단일 조회
    @GetMapping("/{idx}")
    public ResponseEntity read(@PathVariable Long idx) {
        return ResponseEntity.ok(portfolioService.read(idx));
    }
}
