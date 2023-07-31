package snippet;

public class Snippet {
	@GetMapping(value="/datosmarvel")
		public JsonNode viewDataFromApi() {   	
	        return data.getDataFromApi();
	    }      
}

