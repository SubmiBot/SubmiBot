package p2.submibot.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

public class InfoService {

	private void getAssignments(String course, String token) throws Exception {

		String url = "https://canvas.instructure.com/api/v1/courses/" + course + "/assignments/";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestProperty("Authorization", token);
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		JSONArray json = new JSONArray(response.toString());
		List<String> names = new ArrayList<>();

		for (int i = 0; i < json.length(); i++) {
			names.add(json.getJSONObject(i).getString("name"));
		}
		
		System.out.println(Arrays.toString(names.toArray()));
	}

	public static void main(String[] args) throws Exception {
		InfoService is = new InfoService();
		is.getAssignments("1374512", "Bearer 7~P8NctVMFwiLKZeP73EiRzObXGpfMlPQLiDBqmmAd7Lhojzm6ylYU4As0hW9GEkAQ");
	}

}
