package p2.submibot.services;

import p2.submibot.util.MultipartUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InfoService {

	private final static String token = "7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ";

	public static String get(String url, String token) throws IOException {
		token = "Bearer " + token;

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestProperty("Authorization", token);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}
	
	public static String post(String url, String token) throws IOException {
		token = "Bearer " + token;

		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", token);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}
	
	public static String getUser(String token) throws Exception {
		String resp = get("https://canvas.instructure.com/api/v1/users/self", token);

		Gson gson = new Gson();
		User user = (User) gson.fromJson(resp.toString(), new TypeToken<User>(){}.getType());

		return user.toString();
	}

	public static List<Assignment> getAssignments(String token) throws Exception {
		String resp = get("https://canvas.instructure.com/api/v1/courses/1374512/assignments", token);

		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		List<Assignment> assigs = (ArrayList<Assignment>) gson.fromJson(resp.toString(), new TypeToken<ArrayList<Assignment>>(){}.getType());

		return assigs;
	}
	
	public static String postFile(String token, String assignment) throws IOException {
		token = "Bearer " + token;

		URL urlObj = new URL("https://canvas.instructure.com/api/v1/courses/1374512/assignments/" + assignment +"/submissions/self/files");
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", token);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}

	public static String submit(String id) throws IOException {

		URL urlObj = new URL("https://canvas.instructure.com/api/v1/courses/1374512/assignments/9002458/submissions");
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization", "Bearer 7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ");
		con.getOutputStream().write(("submission%5Bsubmission_type%5D=online_upload&submission%5Bfile_ids%5D%5B%5D=" + id).getBytes());

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}
	
	public static void main(String[] args) throws Exception {
//		String name = "Hericles Emanuel Gomes da Silva";
//		System.out.println(name);
//		name = name.replace(' ', '_');
//		System.out.println(name);
//		name = name.toUpperCase();
//		System.out.println(name);

//		System.out.println(getUser(token));
//		System.out.println(getAssignments(token));

		Gson gson = new Gson();
		String r = postFile(token, "9002458");
		FileUploadPermission re = (FileUploadPermission) gson.fromJson(r, new TypeToken<FileUploadPermission>(){}.getType());


		MultipartUtility mu = new MultipartUtility(re.toString(), "UTF-8");
		mu.addFilePart("", new File("/home/fernandes/Desktop/irene.zip"));
		List<String> res = mu.finish();
		Response resp = (Response) gson.fromJson(res.get(0), new TypeToken<Response>(){}.getType());

		String respo = submit(resp.toString());
		System.out.println(respo);
	}

}
