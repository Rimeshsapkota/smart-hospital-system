package com.smarthospital.authservice.systemadmin;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")  // ← makes it a Spring bean
public interface HospitalMapper {

    // MapStruct reads field names and maps automatically
    // password is not in DTO so it is automatically ignored ✅
    HospitalDetaiRequestDto toDto(HospitalDetail hospitalDetail);

    // map a List automatically
    List<HospitalDetail> toDtoList(List<HospitalDetail> hospitalDetails);
}
