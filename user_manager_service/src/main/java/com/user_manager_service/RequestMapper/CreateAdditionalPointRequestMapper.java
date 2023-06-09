package com.user_manager_service.RequestMapper;


import com.model.AdditionalPointVO;
import com.user_manager_service.form.CreateAdditionalPointRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "default")
public interface CreateAdditionalPointRequestMapper {

    CreateAdditionalPointRequestMapper INSTANCE = Mappers.getMapper(CreateAdditionalPointRequestMapper.class);

    AdditionalPointVO map(CreateAdditionalPointRequest createAdditionalPointRequest);
}
