/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatSecretAPI;


import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.Response;
import java.util.List;

public class YourService {
	public void searchFoodItems() {
		String key = "761c409480ec407bb5827cfcfafefdc0";
		String secret = "60a533f0a4f74a3b9382f1cabacaf22d";
		FatsecretService service = new FatsecretService(key, secret);
		
		String query = "pasta"; //Your query string
		Response<CompactFood> response = service.searchFoods(query);
		//This response contains the list of food items at zeroth page for your query
		
		List<CompactFood> results = response.getResults();
		//This list contains summary information about the food items
		
		Response<CompactFood> responseAtPage3 = service.searchFoods(query, 3);
		//This response contains the list of food items at page number 3 for your query
		//If total results are less, then this response will have empty list of the food items	

        
	}
        public static void main(String[] args) {
        YourService alimento=new YourService();
        alimento.searchFoodItems();
    }
}