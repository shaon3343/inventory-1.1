package controllers;

import models.Product;
import dummy.DummyReceipt;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	static Form<DummyReceipt> dummyReceipt = Form.form(DummyReceipt.class);
    public static Result index() {
        return ok(index.render("Create Receipt",dummyReceipt));
    }
  
    public static Result getProductById(){
    	
    	//System.out.println(request().getQueryString("productId"));
    	String pId = request().getQueryString("productId");
    	Product prod = Product.findByIdAndQty(Integer.parseInt(pId));
    	
    	if(prod==null)
    		System.out.println("################# EMPTY###########");
    	
    	return ok(""+prod.productPrice);
    	
    }
    public static Result submitReceipt(){
    	
		return TODO;
    }
}
