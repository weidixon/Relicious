package relicious

class Recipe {
    String name
    String description
    String ingredient
    String step
    Integer prepTime
    Integer cookTime
    Integer serving
    String tips
    String category
    Boolean isPrivate
    Boolean isApprove
    Date dateCreated
    byte[] picture
    byte[] video
    
    static belongsTo = [user:User, recipebook:RecipeBook]

    static constraints = {
	name (blank:false, size:1..50)
	description(size:0..300)
            ingredients(blank:false, size:1..1000)
            step(blank:false, size:1..1000)
            prepTime() //find out how to convert to hours
            cookTime()
            serving()
            tips(size:0..200)
            category (inList:["ChineseCuisine","MalayCuisine", "IndianCuisine", "Others"])
            isPrivate()
            isApprove(display:false,blank:false)
            picture(blank:false)
            video()
              
        
    }
}
