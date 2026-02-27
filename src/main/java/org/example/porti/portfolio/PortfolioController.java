package org.example.porti.portfolio;

import lombok.RequiredArgsConstructor;
import org.example.porti.portfolio.model.PortfolioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 포트폴리오 목록 조회
    @GetMapping("/list")
    public ResponseEntity list() {
        List<PortfolioDto.portRes> dto = portfolioService.list();

        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("result", dto);
        return ResponseEntity.ok(response);
    }
}
