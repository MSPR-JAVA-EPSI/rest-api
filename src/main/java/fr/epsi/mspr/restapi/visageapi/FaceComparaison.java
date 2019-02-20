package fr.epsi.mspr.restapi.visageapi;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class FaceComparaison {
	
	private static String subscriptionKey = "";
	private static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/verify";
	
	public FaceComparaison() {
		subscriptionKey = System.getenv().get("MSPR_MICROSOFT_API_KEY");
		System.out.println(subscriptionKey);
	}
	
	public static boolean compare(String id1, String id2) {
		
		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			URIBuilder builder = new URIBuilder(uriBase);
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.
			StringEntity reqEntity = new StringEntity("{\"faceId1\": \"" + id1 + "\", \"faceId2\": \"" + id2 + "\"}");
			request.setEntity(reqEntity);

			// Execute the REST API call and get the response entity.
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return false;
			} else {
				String jsonString = EntityUtils.toString(entity).trim();
				System.out.println(jsonString);
				if (jsonString.charAt(0) == '[') {
					JSONArray jsonArray = new JSONArray(jsonString);
					return jsonArray.getBoolean(1);
				} else if (jsonString.charAt(0) == '{') {
					JSONObject jsonObject = new JSONObject(jsonString);
					return jsonObject.getBoolean("isIdentical");
				}
			}
		} catch (Exception ex) {
			System.out.println(FaceComparaison.class.getName() + "> " + ex.getMessage());
		}
		return false;
	}

}