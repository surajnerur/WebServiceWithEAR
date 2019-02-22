import org.apache.commons.beanutils.BeanMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.WinHttpClients;
import org.apache.struts2.json.JSONUtil;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WebServiceClient {

  private static final int REQUEST_TIMEOUT=30000;
  private static final int CONNECTION_TIMEOUT=20000;
  private static final RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(REQUEST_TIMEOUT).build();
  //private static final HttpClient httpClient = WinHttpClients.custom().setDefaultRequestConfig(requestConfig).build();
  private static final HttpClient httpClient = WinHttpClients.custom().build();

  public static void main(String[] args) {
    string content = "{\"state\":{\"userId\":\"Gxxx\",\"branchId\":\"0001\"}}";
    createObjectFromJson(content);	
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("firstName","suraj");
    jsonObject.put("lastName","nerurkar");
    String data = "{\"person\": "+jsonobject.toString()+"}";
    String url="http://localhost:7501/hltwebservice-service/hello/com.workflow.StatementFI";
    testRestResponse("http://localhost:7501/hltwebservice-service/hello/com.workflow.StatementFI",data);
    testRestResponse("http://localhost:7501/hltwebservice-service/hello/com.workflow.StatementFI?rep=sun",data);
    testRestResponse("http://localhost:7501/hltwebservice-service/hello/result",data);
  }

  private static void testRestResponse(String url, String data) {
   RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(REQUEST_TIMEOUT).build();
   HttpPost httpPost = new HttpPost(url);
   //httpPost.addHeader("accept", "application/json"); response return type should be set in accept header
   httpPost.addHeader("accept", "text/plain");
   httpPost.addHeader("content-type", "application/json");
   httpPost.setConfig(requestConfig);
   
   try{
    StringEntity entity = new StringEntity(data, "UTF-8");
    httpPost.setEntity(entity);
    HttpResponse httpResponse = httpClient.execute(httpPost);
    BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
    String line;
    StringBuffer output = new StringBuffer();
    while((line = br.readLine()) != null) {
     output.append(line);
    }
    System.out.println(output);
   } catch (Exception e) {
    
   }
  }

  private static String createJSONRequest(Object object) throws Exception {
   Set<String> propKeys = new TreeSet<String>();
   Field[] fields = object.getClass().getDeclaredFields();
   for(Field field : fields) {
     propKeys.add(object.getClass().getSimpleName().toLowerCase() + "." + field.getName());
   }
   Map objMap = new TreeMap();
   Map propertyMap = new TreeMap();
   BeanMap beanMap = new BeanMap(object);
   Set keys = beanMap.keySet();
   Iterator keysIterator = keys.iterator();
   while (keysIterator.hasNext()) {
    String propertyName = keysIterator.next();
    if(propKeys.contains(object.getClass().getSimpleName().toLowerCase() + "." + propertyName)){
     propertyMap.put(propertyName, beanMap.get(propertyName));
    }
   }
   objMap.put(object.getClass().getSimpleName().toLowerCase(), propertyMap);
   String content = JSONUtil.serialize(objMap, null, null, false, false, true);
   System.out.println(content);
   return content;
  }

  private static void createObjectFromJson(String content) throws Exception {
   Gson gson = GsonBuilder.create();
   Map map = (Map)JSONUtil.deserialize(content);
   Object model = gson.fromJson(gson.toJson(map.get(<Class>.class.getSimpleName().toLowerCase())), <Class>.class);
   System.out.println(model); 
  }
}