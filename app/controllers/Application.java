package controllers;

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
  
    public static Result submitReceipt(){
    	
		return TODO;
    }
}
