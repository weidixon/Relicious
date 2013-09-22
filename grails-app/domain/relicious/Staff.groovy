package relicious

class Staff {
     static final String passwordCheckRegExp =  /^[040a-zA-Z0-9_\-\!\@\#\$\%\^\&\*\(\)\+\=\{\}\[\]\;\:\.\>\<\,\\\/\`\~\|]*$/
     
    String userName
    String password
    String confirmPassword
    
    Boolean isManager = false
    
  
    static constraints = {
      userName (unique:true,blank:false)
      password(size:4..32,password:true,matches:passwordCheckRegExp, validator: { value, obj -> 
            if( obj.properties['password'] && value != obj.properties['confirmPassword'] ) return ['not.confirmed']
                                                       
        }
    )
    }
    
}
