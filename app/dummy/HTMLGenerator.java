package dummy;

import java.util.Map;

import models.Product;

public class HTMLGenerator {

	public static String generateROw(){
		String rowWithSelectTag ="<tr class=\"item-row\">"
			  +"<td class=\"item-name\"><div class=\"delete-wpr\">"
			  +"<select id=\"prod\" name=\"product.id\" onchange=\"setPriceAndName(this)\">"
			  +"<option class=\"blank\" value=\"\">-- Choose a Product --</option>";
		      for(Map.Entry<String,String> mp:Product.getProductsAsMap().entrySet()){
		    	  rowWithSelectTag = rowWithSelectTag		    	
		    			  +"<option value="+mp.getKey()+">"+mp.getValue()+"</option>";
		      }
		      rowWithSelectTag = rowWithSelectTag+"</select>"
		      +"<a class=\"delete\" href=\"javascript:;\" title=\"Remove row\">X</a></div></td>"
		      +"<td class=\"description\"><textarea id=\"description\"> </textarea></td>"
		      +"<td><textarea class=\"cost\" id=\"cost\">$0</textarea></td>"
		      +"<td><textarea class=\"qty\" id=\"qty\" onblur=\"checkQty(this)\">1</textarea></td>"
		      +"<td><span class=\"price\">$0</span></td>"
		      +"</tr>";
		
		return rowWithSelectTag;				
		
	}
}
