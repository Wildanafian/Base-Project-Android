package core.common.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Wildan Nafian on 12/01/2022.
 * Github https://github.com/Wildanafian
 * wildanafian8@gmail.com
 */

/**
 * Gson
 *
 * Convert model to json string
 * toJson(data)
 *
 * Convert json string to model
 * fromJson<Model>(data)
 */
fun Any?.toJson(): String {
    return try {
        Gson().toJson(this ?: "")
    } catch (e: Exception) {
        ""
    }
}

inline fun <reified T> String?.fromJson(): T? {
    return try {
        Gson().fromJson<T>(this ?: "", object : TypeToken<T>() {}.type)
    } catch (e: Exception) {
        Gson().fromJson<T>("", object : TypeToken<T>() {}.type)
    }
}

/**
 * JSON
 * jsonObject {
 *  put("key", "data")
 *  put("key2", jsonArray{})
 * }
 *
 * jsonArray {
 *  put(jsonObject{
 *      put("key", "data")
 *      put("key", "data")
 *  })
 * }
 *
 */
inline fun jsonObject(argsBuilder: JSONObject.() -> Unit): JSONObject =
    JSONObject().apply(argsBuilder)

inline fun jsonArray(argsBuilder: JSONArray.() -> Unit): JSONArray = JSONArray().apply(argsBuilder)
