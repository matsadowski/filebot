package service;

import model.dto.MatchersDto;
import java.text.SimpleDateFormat;

public interface MatcherRuleService {

    String pattern = "yyyyMMdd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    boolean match(MatchersDto matchersDto);

}
