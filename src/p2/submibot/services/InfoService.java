package p2.submibot.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InfoService {

	private void getUser(String token) throws Exception {

		String url = "https://canvas.instructure.com/api/v1/users/self";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestProperty("Authorization", token);
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();

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
		System.out.println(user);
	}

	public static void main(String[] args) throws Exception {
		InfoService is = new InfoService();
		is.getUser("Bearer 7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ");
	}

}
