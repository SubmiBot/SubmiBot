package p2.submibot.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CacheStorage {

	private final static String PATH = selectPath();
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;

	private static void verifyDir() {
		File diretorio = new File(PATH);
		if (!diretorio.exists()) {
			diretorio.mkdirs();
			System.out.println(PATH);
		} else {
			System.out.println("Diretório já existente");
		}
	}

	private static String selectPath() {
		String path = System.getProperty("user.home");
		path += (System.getProperty("os.name").equals("Linux")) ? "/.submibot" : "\\.submibot";
		return path;
	}

	private static void writeFile(File file, UserInfo userInfo) throws IOException {
		fos = new FileOutputStream(file);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(userInfo);
	}

	public static void writeUserInfo(String token) throws IOException {
		verifyDir();
		File file = new File(PATH + "/token.dat");
		writeFile(file, new UserInfo(token));
	}
}

class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;

	public UserInfo(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}