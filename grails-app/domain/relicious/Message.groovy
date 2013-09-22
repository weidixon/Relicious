package relicious

class Message {
    Date dateCreated
    String messageContent
    static constraints = {
           messageContent(blank:false)
    }
//static belongsTo = [user:User]
}
