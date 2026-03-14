package org.example.porti.notification;

import jakarta.transaction.Transactional;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.example.porti.notification.model.NotificationDto;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final PushService pushService;

    public NotificationService(NotificationRepository notificationRepository) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        this.notificationRepository = notificationRepository;

        if (Security.getProperty(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        this.pushService = new PushService();
        this.pushService.setPublicKey("BLHgfPga02L2u89uc4xjhbUFTy_U04rQCjGq7o24oxtqfVmAPHTxOmp6xndSHZtGQpmt7gqTFdMXco2gRNP7_p8");
        this.pushService.setPrivateKey("pWhOI-mTyOyx5hogOmKRiYHDCtm_IMpnz1lzWNdMfKU");
    }

    @Transactional
    public void subscribe(NotificationDto.Subscribe dto) {
        notificationRepository.deleteByUserIdx(dto.getUserIdx());
        notificationRepository.save(dto.toEntity());
    }

    // receiverIdx로 notification정보를 찾은 후 senderEmail, contents를 포함해서 푸시알림 전송
    public void sendToUser(Long receiverIdx, Long senderIdx, String senderEmail, String contents) {
        // receiverIdx로 notification 정보 확인
        notificationRepository.findAllByUserIdx(receiverIdx).forEach(entity -> {
            try {
                // subscription + payload(senderEmail + contents)를 notification객체에 담아서 푸시알림 전송
                Subscription.Keys keys = new Subscription.Keys(entity.getP256dh(), entity.getAuth());
                Subscription subscription = new Subscription(entity.getEndpoint(), keys);
                Notification notification = new Notification(subscription, NotificationDto.Payload.builder().senderIdx(senderIdx).senderEmail(senderEmail).contents(contents).build().toString());
                pushService.send(notification);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
