package org.example.porti.namecard;

import lombok.RequiredArgsConstructor;
import org.example.porti.common.model.BaseResponse;
import org.example.porti.namecard.model.NamecardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/namecard")
@RequiredArgsConstructor
public class NamecardController {
    private final NamecardService namecardService;

    @GetMapping("/list")
    public ResponseEntity list(){
        List<NamecardDto.List> dto = namecardService.list();
        return ResponseEntity.ok(BaseResponse.success(dto));
    }
}
