package service.impl;

import model.dto.ActionDto;
import service.ActionService;
import service.constant.ActionName;
import java.io.*;
import static service.utils.FileUtils.*;

public record ActionServiceImpl(File requestFile) implements ActionService {

    @Override
    public void action(ActionDto actionDto) throws IOException {


        switch (ActionName.value(actionDto.getActionName())) {

            case DELETE -> deleteFile(requestFile);
            case COPY_TO -> {
                createDirectory(actionDto.getActionParam());
                File responseFile = new File(actionDto.getActionParam() + "/" + requestFile.getName());
                copyTo(requestFile, responseFile);

            }
            case MOVE_TO -> {
                createDirectory(actionDto.getActionParam());
                File responseFile = new File(actionDto.getActionParam() + "/" + requestFile.getName());
                moveTo(requestFile, responseFile);
            }
            default -> throw new IllegalStateException("Unexpected value: " + ActionName.value(actionDto.getActionName()));
        }
    }


}
