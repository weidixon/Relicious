package relicious

class Message {
    Date dateCreated
    String messageContent
    static constraints = {
           messageContent(blank:false)//HELLO 1
    }
//static belongsTo = [user:User]
}
