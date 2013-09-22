package relicious

class RecipeBook {

     String name
      
    static belongsTo = [user:User]
    static hasMany = [recipe:Recipe]
      
    static constraints = {
    name(blank:false, size:1..32)

}
