package org.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {
    // DTO를 위해선 매퍼가 필수적임 !!

    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                // private인 내용도 가져올 수 있게 만들어줌
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }
    public ModelMapper get() {
        return modelMapper;
    }
}
