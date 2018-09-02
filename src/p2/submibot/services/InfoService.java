package p2.submibot.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InfoService {

	public String getUser(String token) throws Exception {

		String url = "https://canvas.instructure.com/api/v1/users/self";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestProperty("Authorization", token);
		con.setRequestMethod("GET");

		// int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		User user = (User) gson.fromJson(response.toString(), new TypeToken<User>(){}.getType());

		// print result
		return user.toString();
	}

	public String getAssignments(String token) throws Exception {

		String url = "https://canvas.instructure.com/api/v1/courses/1374512/assignments";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestProperty("Authorization", token);
		con.setRequestMethod("GET");

		// int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		List<Assignment> assigs = (ArrayList<Assignment>) gson.fromJson(response.toString(), new TypeToken<ArrayList<Assignment>>(){}.getType());

		return assigs.toString();
	}
	
	public static void main(String[] args) throws Exception {
		InfoService is = new InfoService();
		System.out.println(is.getUser("Bearer 7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ"));
		System.out.println(is.getAssignments("Bearer 7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ"));
	}

}
