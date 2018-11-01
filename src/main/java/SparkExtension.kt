import com.google.gson.GsonBuilder
import spark.Response

val gson = GsonBuilder().disableHtmlEscaping().create()

fun Response.sendJson(obj: Any): String {
    return gson.toJson(obj)+"\n"
}
