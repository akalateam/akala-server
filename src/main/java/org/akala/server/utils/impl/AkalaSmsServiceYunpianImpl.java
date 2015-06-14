package org.akala.server.utils.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.akala.server.utils.AkalaSmsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service("akalaSmsYunpianService")
public class AkalaSmsServiceYunpianImpl implements AkalaSmsService {
  
  private final Log logger = LogFactory.getLog(getClass());
  
  //http url for interface of getting account info
  //private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";
  
  //http url for interface of sending sms
  private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";
  
  //use the UTF-8 as the message encodeing
  private static String ENCODING = "UTF-8";
  
  private static String YUNPIAN_API_KEY = "8352597ec92c2701ed35d1d91e137517";
  
  private static String YUNPIAN_SMS_PREFIX = "【阿卡拉】";
  
  private static String CONSTANT_API_KEY = "apikey";
  
  private static String CONSTANT_API_MOBILE = "mobile";
  
  private static String CONSTANT_API_TEXT = "text";
  
  @Override
  public String sendSms(final String mobile, final String text) {
    Map<String, String> params = new HashMap<String, String>();
    params.put(CONSTANT_API_KEY, YUNPIAN_API_KEY);
    params.put(CONSTANT_API_MOBILE, mobile);
    params.put(CONSTANT_API_TEXT, text);
    String result = postMessage(URI_SEND_SMS, params);
    return result;
  }
  
  @Override
  public boolean sendCredentialText(final String mobile,final String credential){
    String result = sendSms(mobile,buildCredentialText(credential));
    boolean status = false;
    if (result != null){
      JSONParser parser = new JSONParser();
      try {
        JSONObject jsonObj =  (JSONObject)parser.parse(result);
        String code = jsonObj.get("code").toString();
        String msg = jsonObj.get("msg").toString();
        if (code.equals("0")){
          status = true;
        }else {
          logger.info("Sending Credential Failed with Code [" + code + "] Msg [" + msg + "]");
          status = false;
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return status;
  }
  
  private String buildCredentialText(String credential){
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append(YUNPIAN_SMS_PREFIX);
    strBuilder.append("您的验证码是");
    strBuilder.append(credential);
    return strBuilder.toString();
  }
  
  /**
   * post method base on the Httpclient 4.3
   * @param url
   * @param paramsMap
   * @return reponse
   */
  private static String postMessage(final String url, final Map<String, String> paramsMap) {
    CloseableHttpClient client = HttpClients.createDefault();
    String reponseText = "";
    CloseableHttpResponse response = null;
    try {
      HttpPost post = new HttpPost(url);
      if (paramsMap != null) {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        NameValuePair nameValuePair = null;
        for (Map.Entry<String, String> param : paramsMap.entrySet()) {
          nameValuePair = new BasicNameValuePair(param.getKey(), param.getValue());
          paramList.add(nameValuePair);
        }
        post.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
      }

      response = client.execute(post);
      HttpEntity entity = response.getEntity();

      if (entity != null) {
        reponseText = EntityUtils.toString(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (response != null) {
          response.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return reponseText;
  }

}
