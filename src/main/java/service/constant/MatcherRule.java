package service.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MatcherRule {


    EXTENSION_IS_NOT("extensionIsNot"),
    EXTENSION_IS("extensionIs"),
    MODIFIED_DATE_GREATER_THEN("modifiedDateGreaterThen"),
    MODIFIED_DATE_LESS_THEN("modifiedDateLessThen"),
    NAME_CONTAINS("nameContains");

    private final String type;

    public static MatcherRule value(final String type) {
        return Arrays.stream(values()).filter(num -> num.type.equals(type)).findFirst().orElse(MatcherRule.EXTENSION_IS_NOT);
    }

}
