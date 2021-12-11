package com.example.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    var count=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchUI()
    }

    private fun launchUI() {
        btn.setOnClickListener { fetchData() }
       /* sendJson()
        btn.setOnClickListener {
            jsonParser()
        }*/
    }

    private fun sendJson() {
        val users= arrayListOf<User>()
        val user= User()
        user.name="hari"
        user.age=1

        users.add(User("gowtham",23))
        users.add(User("mani",25))
        val json = Gson().toJson(users)
        println("harry ----------------------------->>>>>>>>>>>>>>$json")

        /*val json = JSONObject()
        json.put("name","gowtham")
        json.put("age",23)
        println(json)*/
    }

    private fun jsonParser() {
        val jsonData = "{\n" +
                "  \"statusCode\": 200,\n" +
                "  \"message\": \"Successfully fetched the channels\",\n" +
                "  \"result\": {\n" +
                "    \"is_outside_visible\": false,\n" +
                "    \"channels\": [\n" +
                "      {\n" +
                "        \"name\": \"Q-Kids\",\n" +
                "        \"id\": \"cfd381e4-be54-4e6e-af07-528d6fac6c09\",\n" +
                "        \"image_url\": \"https://tatadigital-prod-cdn.adobecqms.net/content/dam/ihcl-fine-dine-app/default/discovery/Kids-discovery-section-image.jpg/jcr:content/renditions/cq5dam.web.640.640.png\",\n" +
                "        \"created_at\": \"2020-12-10T15:37:55.7Z\",\n" +
                "        \"header_text\": \"Qmin Kids\",\n" +
                "        \"body_hero_text\": \"Kids Special Menu\",\n" +
                "        \"body_description\": \"An all-things Kids menu curated for those little eyes to light up. Fun-filled meals, tasty bites and delicious treats filled with exciting ingredients with a colourful twist. All-time favourites like jalapeno poppers, nuggets, burgers, chocolate brownies and more that kids will love. Let the fun begin!\",\n" +
                "        \"query_attribute\": \"qmin_kids\",\n" +
                "        \"icon_url\": null,\n" +
                "        \"restaurant_id\": null,\n" +
                "        \"static_content_url\": \"base_url?catgory=qmin_kids\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Family Feast\",\n" +
                "        \"id\": \"e98b91f8-5978-4969-93e5-33d87a9dc4fa\",\n" +
                "        \"image_url\": \"https://tatadigital-prod-cdn.adobecqms.net/content/dam/ihcl-fine-dine-app/festive/family-feast/Qmin_Platter_Banner_326-90-px.jpg/jcr:content/renditions/cq5dam.web.640.640.png\",\n" +
                "        \"created_at\": \"2020-12-10T15:37:55.7Z\",\n" +
                "        \"header_text\": \"Family Feast\",\n" +
                "        \"body_hero_text\": \"Family Meals\",\n" +
                "        \"body_description\": \"Family Meals\",\n" +
                "        \"query_attribute\": \"Q_family\",\n" +
                "        \"icon_url\": null,\n" +
                "        \"restaurant_id\": \"a6f81803-1ce7-48f6-89a8-75dfcc1b5bfd\",\n" +
                "        \"static_content_url\": \"base_url?catgory=Q_family\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Signature\",\n" +
                "        \"id\": \"21caa2b8-0294-4ecf-b8ad-82e6c3dbf357\",\n" +
                "        \"image_url\": \"https://tatadigital-prod.adobecqms.net/content/dam/ihcl-fine-dine-app/festive/dawaat-e-khaas/Banner2.png/jcr:content/renditions/cq5dam.web.640.640.png\",\n" +
                "        \"created_at\": \"2020-12-10T15:37:55.7Z\",\n" +
                "        \"header_text\": \"Signatures\",\n" +
                "        \"body_hero_text\": \"Meals for all\",\n" +
                "        \"body_description\": \"One Box Meals and Biryani Platters\",\n" +
                "        \"query_attribute\": \"Q_Signature\",\n" +
                "        \"icon_url\": null,\n" +
                "        \"restaurant_id\": null,\n" +
                "        \"static_content_url\": \"base_url?catgory=Q_Signature\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Celebrations\",\n" +
                "        \"id\": \"e0c80c29-532d-4a9f-b597-4a368b4c1778\",\n" +
                "        \"image_url\": \"https://tatadigital-prod-cdn.adobecqms.net/content/dam/ihcl-fine-dine-app/default/discovery/Comfort-discover-section-image.jpg/jcr:content/renditions/cq5dam.web.640.640.png\",\n" +
                "        \"created_at\": \"2020-12-10T15:37:55.7Z\",\n" +
                "        \"header_text\": \"\",\n" +
                "        \"body_hero_text\": \"\",\n" +
                "        \"body_description\": \"\",\n" +
                "        \"query_attribute\": \"qmin_celebrations\",\n" +
                "        \"icon_url\": null,\n" +
                "        \"restaurant_id\": null,\n" +
                "        \"static_content_url\": \"base_url?catgory=qmin_celebrations\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Comfort\",\n" +
                "        \"id\": \"b42621ad-a75d-4e86-bee1-cb466e2a1d76\",\n" +
                "        \"image_url\": \"https://tatadigital-prod-cdn.adobecqms.net/content/dam/ihcl-fine-dine-app/default/discovery/Comfort-discovery-section-image.jpg/jcr:content/renditions/cq5dam.web.640.640.png\",\n" +
                "        \"created_at\": \"2020-12-10T15:37:55.7Z\",\n" +
                "        \"header_text\": \"Qmin Comfort\",\n" +
                "        \"body_hero_text\": \"Happiness Delivered\",\n" +
                "        \"body_description\": \"Soulful Combo Meals, Nostalgic Flavours, Sweet Cravings. All-of-your-favourite comfort food, just a click away as you smile away your every mood. With Qmin, every day let comfort come home.\",\n" +
                "        \"query_attribute\": \"qmin_comfort\",\n" +
                "        \"icon_url\": null,\n" +
                "        \"restaurant_id\": null,\n" +
                "        \"static_content_url\": \"base_url?catgory=qmin_comfort\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"is_encrypted\": false\n" +
                "}"

        val data = Gson().fromJson(jsonData, FetchData::class.java)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = QminAdapter(data.result.channels)

    }

    private fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://covid-19-data.p.rapidapi.com/country/code?code=it"
        val stringRequest = object : StringRequest(Method.GET, url, { response ->
            println("response ------>  $response")
            loadUI(response)
        }, {
            val err = VolleyError(it)
            println(String(it.networkResponse.data ?: byteArrayOf()))
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["x-rapidapi-host"] = "covid-19-data.p.rapidapi.com"
                headers["x-rapidapi-key"] = "5615de1195mshc2142c7c3f7456cp1bb812jsn9ade89a8d9dd"
                return headers
            }
        }

        queue.add(stringRequest)
    }

    private fun loadUI(response: String) {
        val datas = Gson().fromJson(response, Array<Data>::class.java)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = ProvinceAdapter(this, datas)
    }

}