package org.example.porti.orders;

import io.portone.sdk.server.payment.PaidPayment;
import io.portone.sdk.server.payment.Payment;
import io.portone.sdk.server.payment.PaymentClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.porti.orders.model.Orders;
import org.example.porti.orders.model.OrdersDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final PaymentClient pg;

    @Transactional
    public OrdersDto.OrdersRes verify(OrdersDto.VerifyReq dto) {
        CompletableFuture<Payment> completableFuture = pg.getPayment(dto.getPaymentId());
        Payment payment = completableFuture.join();

        if (payment instanceof PaidPayment paidPayment) {

            if (paidPayment.getAmount().getTotal() == dto.getAmount()) {

                Orders orders = Orders.builder()
                        .paid(true)
                        .paymentPrice(dto.getAmount())
                        .pgPaymentId(dto.getPaymentId())
                        .merchantUid(dto.getMerchantUid())
                        .planCode(dto.getPlanCode())
                        .email(dto.getEmail())
                        .build();

                Orders saved = ordersRepository.save(orders);
                return OrdersDto.OrdersRes.from(saved);

            } else {
                throw new RuntimeException("결제 금액 위변조가 의심됩니다. (요청 금액 불일치)");
            }
        } else {
            throw new RuntimeException("결제가 완료되지 않았거나 취소된 상태입니다.");
        }
    }
}