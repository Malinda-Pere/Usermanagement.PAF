package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.User;

@Path("/User")
public class UserManagement {
	User itemObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser()
	{
		return itemObj.readUser();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertUser(@FormParam("id") String id,
	@FormParam("name") String name,
	@FormParam("address") String address,
	@FormParam("phone") String phone,
	@FormParam("nic") String nic,
	@FormParam("mail") String mail,
	@FormParam("powerarea") String powerarea)
	{
		String output = itemObj. insertUser(id, name, address, phone, nic, mail, powerarea);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(userData).getAsJsonObject();
		
		//Read the values from the JSON object
		String code = itemObject.get("code").getAsString();
		String id = itemObject.get("id").getAsString();
		String name = itemObject.get("name").getAsString();
		String address = itemObject.get("address").getAsString();
		String phone = itemObject.get("phone").getAsString();
		String nic = itemObject.get("nic").getAsString();
		String mail = itemObject.get("mail").getAsString();
		String powerarea = itemObject.get("powerarea").getAsString();
		
		String output = itemObj.updateUser(code, id, name, address, phone, nic, mail, powerarea);
		return output;
	}
	

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String code = doc.select("code").text();
		String output = itemObj.deleteUser(code);
		return output;
	}
	

}
