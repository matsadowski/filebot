package service.impl;

import model.dto.FilebotRequestDto;
import model.dto.ScriptsDto;
import service.ActionService;
import service.FileBotService;
import service.JsonService;
import service.MatcherRuleService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileBotServiceImpl implements FileBotService {

    private final static Function<List<Boolean>, Boolean> IS_ONLY_TRUE = r -> r.stream().allMatch(s -> s.equals(true));

    private final JsonService jsonService;
    private final ActionService actionService;
    private final MatcherRuleService matcherRuleService;

    public FileBotServiceImpl(String json, File requestFile) {
        this.jsonService = new JsonServiceImpl(json);
        this.actionService = new ActionServiceImpl(requestFile);
        this.matcherRuleService = new MatcherRuleServiceImpl(requestFile);
    }


    @Override
    public void execute() throws IOException {
        List<Boolean> result = new ArrayList<>();
        FilebotRequestDto requestDto = jsonService.parse();
        for (ScriptsDto scriptsDto : requestDto.getScripts()) {

            result.clear();
            scriptsDto.getMatchers().forEach(m -> result.add(matcherRuleService.match(m)));

            if (IS_ONLY_TRUE.apply(result)) {
                actionService.action(scriptsDto.getAction());
                break;
            }
        }
    }


}
