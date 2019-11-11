package command;

import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import java.util.List;

public class FileCommand {
@Parameter(names = "-file", 
converter = FileConverter.class)
File file;

@Parameter(names = "-files", 
converter = FileConverter.class)
List<File> files;

}
