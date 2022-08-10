package model.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScriptsDto {

    private String name;
    private List<MatchersDto> matchers;
    private ActionDto action;


}
