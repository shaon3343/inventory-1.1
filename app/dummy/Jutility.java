package dummy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;

import util.AppConst;

public class Jutility {
	public static List<Map<String,String>> processJSON(JsonNode json){
		ArrayNode arr = (ArrayNode) json;
    	Iterator<JsonNode> it = arr.iterator();
    	 
    	List<Map<String,String>> retProdList = new ArrayList<>();
    	while (it.hasNext()) {
    		JsonNode obj = it.next();
    		Map<String,String> productMap = new LinkedHashMap<String, String>();
    	 //   System.out.println("language: " + obj.findPath("language").getTextValue());
    		
    		String prodId = obj.findPath("prodId").getTextValue();
	        String prodCost =  obj.findPath("prodCost").getTextValue();
	        String prodQty = obj.findPath("prodQty").getTextValue();
    		
	        productMap.put(AppConst.productId,prodId);
	        productMap.put(AppConst.productCost,prodCost);
	        productMap.put(AppConst.productQty,prodQty);
	        
    	   /* System.out.println("### PROD ID: "+obj.findPath("prodId").getTextValue()+
    	    		" ###PROD COST "+obj.findPath("prodCost").getTextValue()+
    	    		" ##Prod Quantity: "+obj.findPath("prodQty").getTextValue());*/
    	    retProdList.add(productMap);
    	}
    	return retProdList;
	}
}
