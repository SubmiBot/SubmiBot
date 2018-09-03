package p2.submibot.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import p2.submibot.resources.UserInfo;

public class Persistence {

	private final static String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static FileInputStream fis;

	private static UserInfo readFile(File file) throws ClassNotFoundException, IOException {
		fis = new FileInputStream(file);
		ois = new ObjectInputStream(fis);
		return (UserInfo) ois.readObject();
	}

	private static void writeFile(File file, UserInfo userInfo) throws IOException {
		fos = new FileOutputStream(file);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(userInfo);
	}
	
	public static UserInfo readUserInfo() throws ClassNotFoundException, IOException {
		File file = new File(PATH + ".submibot");
		return file.exists() ? readFile(file) : null;
	}

	public static void writeUserInfo(String name, String token) throws IOException {
		File file = new File(PATH + ".submibot");
		writeFile(file, new UserInfo(name, token));
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		writeUserInfo("Hericles Emmanuel", "7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ");
		UserInfo ui = readUserInfo();
		System.out.println(ui.getName());
		System.out.println(ui.getToken());
	}
}

