package service.impl;

import model.dto.MatchersDto;
import service.MatcherRuleService;
import service.constant.MatcherRule;
import service.utils.FileUtils;
import service.MatcherRuleFunction;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public record MatcherRuleServiceImpl(File requestFile) implements MatcherRuleService, MatcherRuleFunction {

    @Override
    public boolean extensionIsNot(String param) {
        return !extensionIs(param);
    }

    @Override
    public boolean extensionIs(String param) {
        return Objects.equals(FileUtils.getExtensionByStringHandling(requestFile).orElse(null), param);
    }

    @Override
    public boolean modifiedDateGreaterThen(String paramDate) {
        return getLastModifiedDateFromLong().after(parseStringToDate(paramDate));
    }

    @Override
    public boolean modifiedDateLessThen(String paramDate) {
        return getLastModifiedDateFromLong().before(parseStringToDate(paramDate));
    }

    private Date parseStringToDate(String paramDate) {
        try {
            return (simpleDateFormat.parse(paramDate));
        } catch (ParseException e) {
            throw new UnsupportedOperationException("Error while parsing date");
        }
    }

    private Date getLastModifiedDateFromLong() {
        return new Date(requestFile.lastModified());
    }

    @Override
    public boolean nameContains(String param) {
        return requestFile.getName().contains(param);
    }

    @Override
    public boolean match(MatchersDto matcher) {

        return switch (MatcherRule.value(matcher.getMatcherRule())) {
            case EXTENSION_IS -> extensionIs(matcher.getParam());
            case NAME_CONTAINS -> nameContains(matcher.getParam());
            case EXTENSION_IS_NOT -> extensionIsNot(matcher.getParam());
            case MODIFIED_DATE_GREATER_THEN -> modifiedDateGreaterThen(matcher.getParam());
            case MODIFIED_DATE_LESS_THEN -> modifiedDateLessThen(matcher.getParam());
        };

    }

}
