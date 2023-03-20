package com.mos.courier.common.mapper;

public interface ModelMapperService {
    <D> D forResponseMap(Object source, Class<D> destinationType);

    <D> D forRequestMap(Object source, Class<D> destinationType);
}
