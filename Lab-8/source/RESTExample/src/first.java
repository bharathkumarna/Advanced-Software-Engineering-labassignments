
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/first")
public class first {
	public double bmi,b;
@GET
@Produces("application/xml")

public double calculate(double h,double w)
{
	b=w/(h*h);
	bmi=Math.round(b*100.0)/100.0;
	return bmi;
}
}
