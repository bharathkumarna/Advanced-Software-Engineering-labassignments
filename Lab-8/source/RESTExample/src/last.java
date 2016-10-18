import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/last")
public class last {
	public static void main(String args[]){}
	public last(){}
	
	  public double b,height,bmi,ideal,i,caloriescurrent,caloriesideal,c1,c2;
	  String status;
	  @GET
	  @Path("/first")
	  @Produces("application/xml")
	  public double calculate(double h1,double w1){
		first object=new first();
		bmi=object.calculate(h1, w1);
		return bmi;
	  }
	  
	  @GET
	  @Path("/ideal")
	  @Produces("application/xml")
	  public double calculateideal(double b,double h2){
		ideal object=new ideal();
		ideal=object.calculateideal(b, h2);
		return ideal;
	  }
	  
	  

	@Path("{f}/{d}")
	  @GET
	  @Produces("application/json")
	  public Response first(@PathParam("f")double h,@PathParam("d")double w) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		height=h/100;

		b=calculate(height,w);
		if(b<18.5)
			status="Underweight";
		else if((b>=18.5)&&(b<=24.9))
			status="Normal or Healthy Weight";
		else if((b>=25)&&(b<=29.9))
			status="Overweight";
		else
			status="Obese";
		i=calculateideal(b,height);
		
		
		jsonObject.put("BMI", calculate(height,w));
		jsonObject.put("Ideal",calculateideal(b,height));
		jsonObject.put("Status",status);
		
		

		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	  }
}

