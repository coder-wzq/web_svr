package cc.blueview.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class StringUtil {

	private static ObjectMapper om;
	private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	public static boolean isNull(String str)
	{
		return str == null || str.trim().isEmpty();
	}
	
	public static boolean isNotNull(String str)
	{
		return str != null && !str.trim().isEmpty();
	}
	
	public static String trim(String str)
	{
		return str == null ? null : str.trim();
	}
	
	public static String join(List<String> list)
	{
		return StringUtils.collectionToCommaDelimitedString(list);
	}
	
	private static ObjectMapper getObjectMapper()
	{
		if(om == null)
		{
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.ALWAYS);
			om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		}
		
		return om;
	}
	
	public static String getJson(Object obj) 
	{
		try
		{
			return getObjectMapper().writeValueAsString(obj);
		}
		catch(Exception e)
		{
			logger.error("write Object to json failed " + obj, e);
		}
		return null;
	}
	
	public static <T> T getObject(String json, Class<T> t) throws JsonParseException, JsonMappingException, IOException
	{
		return getObjectMapper().readValue(json, t);
	}
	
	public static String formatQuery(String key){
		return key.replaceAll("%", "");
	}
	
	public static String decodeBase64Str(String input)  
    {  
        byte[] debytes = Base64.decodeBase64(new String(input).getBytes());  
        return new String(debytes);  
    }

    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }
}
