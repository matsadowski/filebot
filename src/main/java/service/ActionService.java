package service;

import model.dto.ActionDto;
import java.io.IOException;

public interface ActionService {

    void action(ActionDto actionDto) throws IOException;
}
