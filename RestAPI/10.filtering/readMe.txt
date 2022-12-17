The json response is basically the bean searialized into json format.

@JsonProperty("CustomName") => Custom Name for the field in Json response.


We can avoid displaying fields of the bean to be sent as response.



Static Filter - When different handler methods access the bean, 
		it filters the same field for all rest api.
		@JsonIgnore - above field = ignore particular field.
		@JsonIgnoreProperties("FieldName") - above class = ignore particular field,
		Multiple Fields = @JsonIgnoreProperties( {"FieldName1","FieldName2"} )



Dynamic Filter - We also work on the rest api handler methods restricting access, 
		 so the individual methods have different filters for all rest api.

