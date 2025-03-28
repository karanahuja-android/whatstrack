/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package whatstrack
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
data class MyLanguage (
    val code:String
)
data class MyTemplate (
    val name:String,
    val language:MyLanguage
)
data class RequestBody (
    val messaging_product:String,
    val to:String,
    val type:String,
    val template:MyTemplate
)
data class TextMessageContent (
      val preview_url:Boolean,
    val body:String
)
data class TextMessageObject (
      val messaging_product:String,
    val recipient_type:String,
    val to:String,
    val type:String,
    val text:TextMessageContent 
)
data class ReactionObject (
    val message_id:String,
    val emoji:String
)
data class ImageObject (
    val link:String
)
data class ReactionMessageObject (
    val messaging_product:String,
    val recipient_type:String,
    val to:String,
    val type:String,
    val reaction:ReactionObject
)
data class ImageMessageObject (
    val messaging_product:String,
    val recipient_type:String,
    val to:String,
    val type:String,
    val image:ImageObject
)

data class LocationObject (
    val longitude:String,
    val latitude:String,
    val name:String,
    val address:String
)
data class LocationMessageObject (
      val messaging_product:String,
      val to:String,
      val type:String,
      val location:LocationObject 

)
data class ButtonReply (
    val id:String,
    val title:String
)
data class ButtonRow (
    val type:String,
    val reply:ButtonReply
)
data class InteractiveAction(
    val buttons:List<ButtonRow>
)
data class InteractiveSectionRow (
    val id:String,
    val title:String,
    val description:String
)
data class InteractiveSection (
    val title:String,
    val rows:List<InteractiveSectionRow>
)
data class InteractiveListAction (
    val button:String,
    val sections:List<InteractiveSection>
)
data class InteractiveBody (
    val text:String
)
data class InteractiveObject (
    val type:String,
    val body:InteractiveBody,
    val action:InteractiveAction
)
data class InteractiveHeader (
    val type:String,
    val text:String
)
data class InteractiveListObject (
    val type:String,
    val header:InteractiveHeader,  
    val body:InteractiveBody,
    val footer:InteractiveBody,
    val action:InteractiveListAction

)
data class InteractiveButtonMessageObject (
    val messaging_product:String,
    val recipient_type:String,
    val to:String,
    val type:String,
    val interactive:InteractiveObject
)
data class InteractiveListMessageObject (
    val messaging_product:String,
    val recipient_type:String,
    val to:String,
    val type:String,
    val interactive:InteractiveListObject

)

class App {

fun sendRequestToFacebook (requestBodyString:String){
      val client = HttpClient.newBuilder().build();
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://graph.facebook.com/v15.0/103161752650905/messages"))
        .POST(HttpRequest.BodyPublishers.ofString(requestBodyString))
         .header("Content-Type", "application/json")
         .header ("Authorization","Bearer EAAJF5XMdhTIBAIgfTCCZCZBDzptloNrFDzRJxEZBbs9ZA3sNrX8V8feOrfXhE7TP4Fv4QawW51cmqY57cX6lCjDn95OCHtqmTb7J3JKlGjKY4GDnhvIXgUw8lZCUV7egoAyxMYQBUQZCLaI46xwS1JNmf7f0kSxayPZA4Qf6hsPRObkNSe6w0KIdicHvrZCm3UwOSROM3sx6iwZDZD")
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString());
    println(response.body())
}

fun sendInteractiveListMessage (){
    val firstSectionRowsList = listOf (
        InteractiveSectionRow (
            "SECTION_1_ROW_1_ID",
            "SECTION_1_ROW_1_TITLE",
            "SECTION_1_ROW_1_DESCRIPTION"
                ),
        InteractiveSectionRow (
            "SECTION_1_ROW_2_ID",
            "SECTION_1_ROW_2_TITLE",
            "SECTION_1_ROW_2_DESCRIPTION"
                ),
        InteractiveSectionRow (
            "SECTION_1_ROW_3_ID",
            "SECTION_1_ROW_3_TITLE",
            "SECTION_1_ROW_3_DESCRIPTION"
        ),
        InteractiveSectionRow (
            "SECTION_1_ROW_4_ID",
            "SECTION_1_ROW_4_TITLE",
            "SECTION_1_ROW_4_DESCRIPTION"
        )
            )

    val sectionsList = listOf (
        InteractiveSection (
            "SECTION_1_TITLE",
            firstSectionRowsList
                )
            )
    val interactiveListMessageObject = InteractiveListMessageObject (
          "whatsapp",
        "individual",
        "919820011185",
        "interactive",
        InteractiveListObject (
            "list",
            InteractiveHeader (
                "text",
                "HEADER_TEXT"
            ),
            InteractiveBody (
                "BODY_TEXT"
            ),
            InteractiveBody (
                "FOOTER_TEXT"
            ),
            InteractiveListAction (
                "BUTTON_TEXT",
                sectionsList
            )

        )
    )
    val gson = Gson()
    val interactiveListMessageString:String = gson.toJson (interactiveListMessageObject)
    println (interactiveListMessageString)
    sendRequestToFacebook (interactiveListMessageString)
}

fun sendInteractiveButtonMessage (){
    val buttonRow1 = ButtonRow (
        "reply",
        ButtonReply (
            "button1",
            "Button 1"
        )
    )
    val buttonRow2 = ButtonRow (
        "reply",
        ButtonReply (
            "button2",
            "Button 2"
        )
    )

    val interactiveButtonMessageObject = InteractiveButtonMessageObject (
        "whatsapp",
        "individual",
        "919820011185",
        "interactive",
        InteractiveObject (
            "button",
        InteractiveBody (
            "View Now"
    ),
        InteractiveAction (
            listOf (
                buttonRow1,
                buttonRow2
            )
        )

        )
    )
        val gson = Gson()
        val interactiveButtonMessageString:String = gson.toJson (interactiveButtonMessageObject) 
        println (interactiveButtonMessageString)
        sendRequestToFacebook (interactiveButtonMessageString)

    


}
fun sendImage (){
    val imageMessageObject = ImageMessageObject (
        "whatsapp",
        "individual",
        "919820011185",
        "image",
        ImageObject (
            "https://images.pling.com/img/00/00/48/70/84/1220648/e4fff450a6306e045f5c26801ce31c3efaeb.jpg"
        )
    )
    val gson = Gson()
    val imageMessageString:String = gson.toJson (imageMessageObject) 
    println (imageMessageString)
    sendRequestToFacebook (imageMessageString)
    

}
fun sendReactionMessage (){
    val reactionMessageObject = ReactionMessageObject (
        "whatsapp",
        "individual",
        "919820011185",
        "reaction",
        ReactionObject (
            "wamid.HBgLMzE2MjMzODk2MTYVAgARGBJGOEI4MzQ1Qjg2QkVEMkVDQzEA",
            "\uD83D\uDE00"
        )
    )
    val gson = Gson()
    val reactionMessageString:String = gson.toJson (reactionMessageObject) 
    println (reactionMessageString)
    sendRequestToFacebook (reactionMessageString)

}
fun sendTextMessage (){
val textMessageObject = TextMessageObject (
    "whatsapp",

    "individual",
    "919820011185",
    "text",
    TextMessageContent (
        false,
        "Hello World"
    )
)
 val gson = Gson()
 val textMessageString:String = gson.toJson (textMessageObject) 
 println (textMessageString)
 sendRequestToFacebook (textMessageString)
}
fun sendTemplateMessage (){
    val myLanguage = MyLanguage ("en_US")
    val myTemplate = MyTemplate ("hello_world",myLanguage)
    val gson = Gson()
    val requestBody = RequestBody ("whatsapp","919820011185","template",myTemplate)

	val requestBodyString: String = gson.toJson(requestBody)
    println (requestBodyString)
    // val requestBody: String = "{ \"messaging_product\": \"whatsapp\", \"to\": \"919820011185\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"
    sendRequestToFacebook (requestBodyString)
}
}

fun main() {
//     println(App().sendTemplateMessage())
    // println(App().sendTextMessage())
//    println (App().sendReactionMessage ())
     println (App().sendInteractiveButtonMessage())
//     println (App().sendInteractiveListMessage())
}
