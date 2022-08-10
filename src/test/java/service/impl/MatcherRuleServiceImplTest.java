package service.impl;

import model.dto.MatchersDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.MatcherRuleService;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MatcherRuleServiceImplTest {

    private final static String PATH = "src/test/java/service/impl/data/test.json";
    private final File requestFile = new File(PATH);

    private final MatcherRuleService matcherRuleService = new MatcherRuleServiceImpl(requestFile);
    private final MatchersDto matchersDto = Mockito.mock(MatchersDto.class);

    @Test
    public void testMatch() {
        when(matchersDto.getMatcherRule()).thenReturn("extensionIsNot");
        when(matchersDto.getParam()).thenReturn("json");
        assertFalse(matcherRuleService.match(matchersDto));
        when(matchersDto.getParam()).thenReturn("pdf");
        assertTrue(matcherRuleService.match(matchersDto));
    }

}