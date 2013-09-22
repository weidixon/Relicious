package relicious

class Comment {
byte[] photo			
Date dateCreated
Double rating		
String comment	
		
         static constraints = { 
           comment(blank:false) 
           rating(blank:false)
           photo(blank:true, maxSize: 163840000)
          dateCreated(blank:false)
         }
    static belongsTo = [recipe:Recipe,user:User]
}