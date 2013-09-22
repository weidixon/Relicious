package relicious

class User {

    // static transients = [ “confirmPassword” ]

    String userName
    String password
    String confirmPassword
    String email
    Boolean hideEmail
    String firstName
    String lastName
    String gender
    Date dateOfBirth
    Boolean hideDOB
    String nationality
    Country country
    String city
    String state
    Boolean isAdmin
    Boolean signedInViaFacebook
        
     static hasMany = [recipeBook:RecipeBook]
     static hasMany = [recipe:Recipe]
     static hasMany = [badge:Badge]
     static hasMany = [message:Message]

    static constraints = {
    firstName (size: 1..32,blank:false)
    lastName (size: 1..32,nullable:true)
    userName (unique:true,blank:false, size:4..32)
    password (blank:false,password:true, size:4..32) 
    confirmPassword (blank:false,password:true,size:4..32)
    email (email:true,blank:false)
    hideEmail()        
    gender (inList: ['Male','Female'])
    dateOfBirth (display:true,blank:false) 
    hideDOB()
    nationality (blank:false)
    country()
    city()
    state()
    isAdmin(display:false, blank:true)
    signedInViaFacebook(display:false, blank:true)
    }

}
