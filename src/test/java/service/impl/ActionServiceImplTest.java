package service.impl;

import model.dto.ActionDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.ActionService;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ActionServiceImplTest {

    private final static String PATH = "src/test/java/service/impl/data/test.json";
    private final File requestFile = new File(PATH);

    private final ActionService actionService = new ActionServiceImpl(requestFile);
    private final ActionDto actionDto = Mockito.mock(ActionDto.class);

    @Test
    public void testAction() {

        when(actionDto.getActionName()).thenReturn("copyTo");
        when(actionDto.getActionParam()).thenReturn("src/test/java/service/impl/data/");

        assertDoesNotThrow(() -> actionService.action(actionDto));

    }
}