package org.example.porti.namecard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.porti.namecard.model.Namecard;
import org.example.porti.namecard.model.NamecardDto;
import org.example.porti.user.UserRepository;
import org.example.porti.user.model.AuthUserDetails;
import org.example.porti.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NamecardService {
    private final NamecardRepository namecardRepository;
    private final UserRepository userRepository;

    public List<NamecardDto.List> list() {
        List<Namecard> namecardList = namecardRepository.findAll();
        return namecardList.stream().map(NamecardDto.List::toDto).toList();
    }

    @Transactional
    public void reg(NamecardDto.Register dto, AuthUserDetails user) {
        Long userIdx = user.getIdx();

        Namecard namecard = namecardRepository.findByUserIdx(userIdx).orElseGet(()->{
            User userEntity = userRepository.findById(userIdx)
                    .orElseThrow(()->new EntityNotFoundException("사용자를 찾을 수 없습니다."));
            return Namecard.builder().user(userEntity).build();
        });

        namecard.update(dto.getTitle(), dto.getColor(), dto.getLayout());

        namecardRepository.save(namecard);
    }
}
