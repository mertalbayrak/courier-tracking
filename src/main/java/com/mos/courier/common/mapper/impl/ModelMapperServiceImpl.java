package com.mos.courier.common.mapper.impl;

import com.mos.courier.common.mapper.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelMapperServiceImpl implements ModelMapperService {

    private final ModelMapper modelMapper;

    @Override
    public <D> D forResponseMap(Object source, Class<D> destinationType) {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return map(source, destinationType);
    }

    @Override
    public <D> D forRequestMap(Object source, Class<D> destinationType) {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
        return map(source, destinationType);
    }

    private <D> D map(Object source, Class<D> destinationType) {
        return source == null ? null : modelMapper.map(source, destinationType);
    }


    private <S, D> List<D> mapList(List<S> source, Class<D> destinationType) {

        List<D> resultList = null;

        if (source != null && !source.isEmpty()) {

            resultList = new ArrayList<>();

            for (S element : source) {
                resultList.add(map(element, destinationType));
            }
        }

        return resultList;
    }
}
