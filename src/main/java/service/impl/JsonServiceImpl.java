package service.impl;

import model.dto.FilebotRequestDto;
import org.codehaus.jackson.map.ObjectMapper;
import service.JsonService;

import java.io.File;
import java.io.IOException;

public record JsonServiceImpl(String path) implements JsonService {

    @Override
    public FilebotRequestDto parse() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(path), FilebotRequestDto.class);
        } catch (IOException e) {
            throw new UnsupportedOperationException("Error while parsing json");
        }

    }


}
