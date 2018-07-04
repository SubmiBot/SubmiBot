package p2.submibot.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

	public static void zip(String sourceDirPath, String zipFilePath) throws IOException {
	    Path zipFileP = Files.createFile(Paths.get(zipFilePath));
		try (ZipOutputStream zipOutputStrem = new ZipOutputStream(Files.newOutputStream(zipFileP))) {
		    Path sourceDirP = Paths.get(sourceDirPath);
		    Files.walk(sourceDirP)
		    .filter(path -> !Files.isDirectory(path))
		    .forEach(path -> {
		    	ZipEntry zipEntry = new ZipEntry(sourceDirP.relativize(path).toString());
				try {
					zipOutputStrem.putNextEntry(zipEntry);
					Files.copy(path, zipOutputStrem);
					zipOutputStrem.closeEntry();
				} catch (IOException e) {
					System.err.println(e);
				}
		    });
		}
	}	

}
