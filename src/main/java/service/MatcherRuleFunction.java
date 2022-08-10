package service;

public interface MatcherRuleFunction {

    boolean extensionIsNot(String param);

    boolean extensionIs(String param);

    boolean modifiedDateGreaterThen(String paramDate);

    boolean modifiedDateLessThen(String paramDate);

    boolean nameContains(String param);

}
