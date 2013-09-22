package relicious

class Country {

    String id
    String country_name
    
    static constraints = {
    }

  static hasMany = [user:User]

    String toString() {
    return country_name 
    }
    

}
