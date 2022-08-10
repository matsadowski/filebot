package service.impl;

import org.junit.jupiter.api.Test;
import service.MatcherRuleFunction;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatcherRuleFunctionTest {

    private final static String PATH = "src/test/java/service/impl/data/test.json";
    private final File requestFile = new File(PATH);

    private final MatcherRuleFunction matcherRuleService = new MatcherRuleServiceImpl(requestFile);

    @Test
    public void testExtensionIsNot() {
        assertFalse(matcherRuleService.extensionIsNot("json"));
        assertTrue(matcherRuleService.extensionIsNot("pdf"));
    }

    @Test
    public void testExtensionIs() {
        assertTrue(matcherRuleService.extensionIs("json"));
        assertFalse(matcherRuleService.extensionIs("pdf"));
        assertFalse(matcherRuleService.extensionIs("xlsx"));
    }

    @Test
    public void testModifiedDateGreaterThen() {

        assertTrue(matcherRuleService.modifiedDateGreaterThen("20200101"));
        assertFalse(matcherRuleService.modifiedDateGreaterThen("20230101"));
    }

    @Test
    public void testModifiedDateLessThen() {
        assertFalse(matcherRuleService.modifiedDateLessThen("20200101"));
        assertTrue(matcherRuleService.modifiedDateLessThen("20230101"));
    }

    @Test
    public void testNameContains() {
        assertTrue(matcherRuleService.nameContains("test"));
        assertTrue(matcherRuleService.nameContains("json"));
        assertFalse(matcherRuleService.nameContains("xlsx"));
    }


}