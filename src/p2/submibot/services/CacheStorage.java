package p2.submibot.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CacheStorage {

	private final static String PATH = selectPath();
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static FileInputStream fis;
	
	private static void verifyDir() {
		File diretorio = new File(PATH);
		if (!diretorio.exists()) {
			diretorio.mkdirs();
			System.out.println(PATH);
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
	
	private static UserInfo readFile(File file) throws ClassNotFoundException, IOException {
		fis = new FileInputStream(file);
		ois = new ObjectInputStream(fis);
		return (UserInfo) ois.readObject();
	}

	public static void writeUserInfo(String token, String matr) throws IOException {
		verifyDir();
		File file = new File(PATH + "/.user_cache.dat");
		writeFile(file, new UserInfo(token, matr));
	}
	
	public static UserInfo readUserInfo() throws ClassNotFoundException, IOException {
		File file = new File(PATH + "/.user_cache.dat");
		return file.exists() ? readFile(file) : null;
	}
 	
//	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		writeUserInfo("7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ", "117 110 647");
//		System.out.println(readUserInfo());
//	}
}

