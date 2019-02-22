package com.webserviceapp.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : " + msg;

		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("/result")
        @Consumes(MediaType.APPLICATION_JSON)
	public Response getPostMsg(String res) {

		String output = "Jersey say : " + res

                Gson gson = GsonBuilder.create();
                Object model=null;
                try{
                     Map map = (Map)JSONUtil.deserialize(content);
                     Object model = gson.fromJson(gson.toJson(map.get(<Class>.class.getSimpleName().toLowerCase())), <Class>.class);
                     System.out.println(model); 
                }catch(Exception e){
                     e.printStackTrace();
                }

		return Response.status(200).entity(model.toString()).build();

	}


	@POST
	@Path("/resultget")
        @Consumes(MediaType.APPLICATION_JSON)
	public Response getGetMsg(@QueryParam("rep") String res, String content) {

		String output = "Jersey say : " + res + " Content : "+content;


		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("/{resultparam}")
        @Consumes(MediaType.APPLICATION_JSON)
	public Response getGetMsg(@PathParam("resultparam") String par, @QueryParam("rep") String res, String content) {

		String output = "Output is Path param : " + par + " Query param : "+ res + " Content : "+content;


		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("/resultPerson")
        @Consumes(MediaType.APPLICATION_JSON)
	public Response getGetMsg(Person person) {//Currently not working

		String output = "Jersey say : " + person;


		return Response.status(200).entity(person).build();

	}
}