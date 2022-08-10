package service.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ActionName {

    DELETE("delete"), MOVE_TO("moveTo"), COPY_TO("copyTo");

    private final String type;

    public static ActionName value(final String type) {
        return Arrays.stream(values()).filter(num -> num.type.equals(type)).findFirst().orElse(ActionName.DELETE);
    }

}
