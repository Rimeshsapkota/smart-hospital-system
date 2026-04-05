package com.smarthospital.authservice.systemadmin;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")  // ← makes it a Spring bean
public interface HospitalMapper {

    HospitalDetaiRequestDto toDto(HospitalDetail hospitalDetail);

    // map a List automatically
    List<HospitalDetail> toDtoList(List<HospitalDetail> hospitalDetails);
}
