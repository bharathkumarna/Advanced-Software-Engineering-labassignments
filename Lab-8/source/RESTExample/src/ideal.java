import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/ideal")
public class ideal {
	public double ideal,n;
@GET
@Produces("application/xml")

public double calculateideal(double bmi,double h)
{
	n=(0.5*bmi+11.5)*h*h;
	ideal=Math.round(n*100.0)/100.0;
	return ideal;
}
}


