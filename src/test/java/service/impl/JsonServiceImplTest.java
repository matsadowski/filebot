package service.impl;


import model.dto.ActionDto;
import model.dto.FilebotRequestDto;
import model.dto.ScriptsDto;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.JsonService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class JsonServiceImplTest {

    private final static String PATH = "src/test/java/service/impl/data/test.json";
    private final JsonService jsonService = new JsonServiceImpl(PATH);
    private final FilebotRequestDto filebotRequestDto = Mockito.mock(FilebotRequestDto.class);
    private final ScriptsDto scriptsDto = Mockito.mock(ScriptsDto.class);
    private final ActionDto actionDto = Mockito.mock(ActionDto.class);

    @Test
    public void testJsonParse() {
        FilebotRequestDto requestDto = jsonService.parse();

        List<ScriptsDto> scriptsDtoList = new ArrayList<>();
        when(scriptsDto.getName()).thenReturn("Delete reports");

        when(actionDto.getActionName()).thenReturn("delete");
        when(scriptsDto.getAction()).thenReturn(actionDto);

        scriptsDtoList.add(scriptsDto);
        when(filebotRequestDto.getScripts()).thenReturn(scriptsDtoList);
        Assertions.assertEquals(requestDto.getScripts().get(0).getName(), filebotRequestDto.getScripts().get(0).getName());
        Assertions.assertEquals(requestDto.getScripts().get(0).getAction().getActionName(), filebotRequestDto.getScripts().get(0).getAction().getActionName());

    }

    @Test
    public void testJsonParseException() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> new JsonServiceImpl("src/test/java/service/impl/data/error.json").parse(), "Error while parsing json");
    }


}