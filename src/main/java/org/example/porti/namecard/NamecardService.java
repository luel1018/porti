package org.example.porti.namecard;

import lombok.RequiredArgsConstructor;
import org.example.porti.namecard.model.Namecard;
import org.example.porti.namecard.model.NamecardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NamecardService {
    private final NamecardRepository namecardRepository;

    public List<NamecardDto.List> list() {
        List<Namecard> namecardList = namecardRepository.findAll();
        return namecardList.stream().map(NamecardDto.List::toDto).toList();
    }
}
