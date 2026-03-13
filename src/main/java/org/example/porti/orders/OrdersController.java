package org.example.porti.orders;

import lombok.RequiredArgsConstructor;
import org.example.porti.common.model.BaseResponse;
import org.example.porti.common.model.BaseResponseStatus;
import org.example.porti.orders.model.OrdersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping("/verify")
    public ResponseEntity verify(@RequestBody OrdersDto.VerifyReq dto) {
        try {
            OrdersDto.OrdersRes response = ordersService.verify(dto);
            return ResponseEntity.ok(BaseResponse.success(response));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(BaseResponse.fail(BaseResponseStatus.FAIL, e.getMessage()));
        }
    }
}