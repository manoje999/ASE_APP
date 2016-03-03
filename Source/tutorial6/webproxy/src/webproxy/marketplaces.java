package webproxy;

import java.io.*;
import java.net.URL;			
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/restaurants")
public class marketplaces {
	  @Path("{place}")
	  @GET
	  @Produces("application/json")
	   public Response locateplace(@PathParam("place") String start) throws JSONException {
		  
		StringBuilder content = new StringBuilder();
		JSONObject jsonObject = new JSONObject();
		String start1 = miami;
		URL places = new URL("https://api.foursquare.com/v2/venues/search?client_id=EY4IS2KD0V2XWGITLARLH5AAWZZRL0AFV31PWJD3XHKGB4TD&client_s0000000000000000000000ecret=5AA21M5MMBMKXBH2JI5APIE2YZM4VKRHDQZW25035MET4QE4&v=20160302&limit=5&near=miami&querysearchQuery=casino");
		URLConnection yc = places.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
                
        id = in.readLine("id");
        
        URL places = new URL("https://api.foursquare.com/v2/venues/4cb73ede56fca1cd51375c18/tips?sort=recent&client_id=EY4IS2KD0V2XWGITLARLH5AAWZZRL0AFV31PWJD3XHKGB4TD&client_secret=5AA21M5MMBMKXBH2JI5APIE2YZM4VKRHDQZW25035MET4QE4&v=20160302&limit=5");
		URLConnection yc = places.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String finalstring = jsonObject.toString(in);
        name = finalstring.getString("name")
        place = finalstring.getString("place")
        review = finalstring.getString("text")
        
        jsonObject.put("name", name); 
        jsonObject.put("city", place);
        jsonObject.put("feedback",review);

	;
 
		String result = "{ \n data" + jsonObject + "\n}";
		return Response.status(200).entity(result).build();
	  }

}
