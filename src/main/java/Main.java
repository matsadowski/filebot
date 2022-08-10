import service.FileBotService;
import service.impl.FileBotServiceImpl;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import static service.utils.FileUtils.getFilesFromDirectory;


public class Main {

    public static void main(String[] args) {

        final String json = args[1];
        final String path = args[0];

        Set<File> filesFromDirectory = getFilesFromDirectory(path);

        for (File f : filesFromDirectory) {
            try {
                FileBotService fileBotService = new FileBotServiceImpl(json, f);
                fileBotService.execute();
            } catch (IOException e) {
                throw new UnsupportedOperationException("Error while executing FileBotService");
            }
        }


    }

}
