package com.xiongjiawu.smartaccountbook.common.util;

import com.alibaba.fastjson.JSONObject;
import com.zhangzlyuyx.easy.core.ActionCallback;
import com.zhangzlyuyx.easy.core.Result;
import com.zhangzlyuyx.easy.core.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * http 操作类
 *
 */
public class HttpUtils {

	/**
	 * get 请求, 获取 数据流
	 * @param url 请求url
	 * @param actionCallback 数据响应回调
	 * @return
	 */
	public static Result<String> getReturnStream(String url, ActionCallback<InputStream> actionCallback){
		return getReturnStream(url, actionCallback, null);
	}

	/**
	 * get 请求, 获取 数据流
	 * @param url 请求url
	 * @param actionCallback 数据响应回调
	 * @param requestConfig 请求配置
	 * @return
	 */
	public static Result<String> getReturnStream(String url, ActionCallback<InputStream> actionCallback, RequestConfig requestConfig){
		CloseableHttpClient client = null;
		try {
			client = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			if(requestConfig != null){
				httpGet.setConfig(requestConfig);
			}
			HttpResponse respone = client.execute(httpGet);
			if(respone.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
		        return new Result<>(false, respone.getStatusLine().toString());
		    }
			HttpEntity responeEntity = respone.getEntity();
			if(responeEntity == null){
				return new Result<>(false, "获取响应信息失败!");
			}
			InputStream inputStream = responeEntity.getContent();
			if(actionCallback != null){
				actionCallback.action(inputStream);
			}
			return new Result<>(true, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(false, e.getMessage());
		}finally{
			// 释放客户端
        	try {
        		if(client != null){
            		client.close();
            	}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
        }
	}


	/**
	 * post 请求, 获取 string 结果
	 * @param url 请求url
	 * @param httpEntity post实体
	 * @param timeout 超时时间
	 * @return
	 */
	public static Result<String> postReturnString(String url, HttpEntity httpEntity, Integer timeout){
		if(timeout == null || timeout <= 0){
			return postReturnString(url, httpEntity, (RequestConfig)null);
		}
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout)
				.build();
		return postReturnString(url, httpEntity, config);
	}

	/**
	 * post 请求, 获取 string 结果
	 * @param url 请求url
	 * @param httpEntity post实体
	 * @return
	 */
	public static Result<String> postReturnString(String url, HttpEntity httpEntity){
		return postReturnString(url, httpEntity, -1);
	}

	/**
	 * post 请求, 获取 string 结果
	 * @param url 请求url
	 * @param httpEntity post实体
	 * @param requestConfig 请求配置
	 * @return
	 */
	public static Result<String> postReturnString(String url, HttpEntity httpEntity, RequestConfig requestConfig){
		CloseableHttpClient client = null;
		try {
			client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			//httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
			if(requestConfig != null){
				httpPost.setConfig(requestConfig);
			}
			httpPost.setEntity(httpEntity);
			HttpResponse response = client.execute(httpPost);
        	if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        		return new Result<String>(false, response.getStatusLine().toString());
        	}
        	HttpEntity responseEntity = response.getEntity();
        	String result = inputStreamToString(responseEntity.getContent());
        	//String content = EntityUtils.toString(responseEntity, "UTF-8");
        	//EntityUtils.consume(responseEntity);
        	return new Result<String>(true, "",result);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<String>(false, e.getMessage());
		}finally{
			// 释放客户端
        	try {
        		if(client != null){
            		client.close();
            	}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
        }
	}

	public static String inputStreamToString(InputStream is) {

        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return full string
        return total.toString();
    }

	/**
	 * post 请求, 获取 string 结果
	 * @param url
	 * @param params
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static Result<String> postReturnString(String url, List<BasicNameValuePair> params, String charset, Integer timeout){

		try {
			if(StringUtils.isEmpty(charset)){
				charset = "utf-8";
			}
			UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, charset);
			return postReturnString(url, httpEntity, timeout);
		} catch (Exception e) {
			return new Result<String>(false, e.getMessage());
		}
	}

	/**
	 * post 上传文件请求, 获取 string 结果
	 * @param url 请求url
	 * @param params 附加参数
	 * @param files
	 * @return
	 */
//	public static Result<String> postReturnString(String url, Map<String, String> params, MultipartFile... files){
//		if(files == null || files.length == 0){
//			return new Result<String>(false, "没有需要上传的文件!");
//		}
//		try {
//			MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
//			if(params != null){
//				for(Entry<String, String> kv : params.entrySet()){
//					builder.addTextBody(kv.getKey(), kv.getValue());
//				}
//			}
//			for(MultipartFile file : files){
//				builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
//			}
//			HttpEntity httpEntity = builder.build();
//			return postReturnString(url, httpEntity);
//		} catch (Exception e) {
//			return new Result<String>(false, e.getMessage());
//		}
//	}
//	public static Result<String> postReturnString1(String url, Map<String, String> params, List<MultipartFile> files){
//		if(files == null || files.size() == 0){
//			return new Result<String>(false, "没有需要上传的文件!");
//		}
//		try {
//			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//			if(params != null){
//				for(Entry<String, String> kv : params.entrySet()){
//					builder.addTextBody(kv.getKey(), kv.getValue());
//				}
//			}
//			for(MultipartFile file : files){
//				builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
//			}
//			HttpEntity httpEntity = builder.build();
//			return postReturnString(url, httpEntity);
//		} catch (Exception e) {
//			return new Result<String>(false, e.getMessage());
//		}
//	}

	/**
	 * post 上传文件请求, 获取 string 结果
	 * @param url
	 * @param params 附加参数
	 * @param files 文件列表
	 * @return
	 */
//	public static Result<String> postReturnString(String url, Map<String, String> params, File... files){
//		if(files == null || files.length == 0){
//			return new Result<String>(false, "没有需要上传的文件!");
//		}
//		try {
//			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//			if(params != null){
//				for(Entry<String, String> kv : params.entrySet()){
//					builder.addTextBody(kv.getKey(), kv.getValue());
//				}
//			}
//			for(File file : files){
//				builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, file.getName());
//			}
//			HttpEntity httpEntity = builder.build();
//			return postReturnString(url, httpEntity);
//		} catch (Exception e) {
//			return new Result<String>(false, e.getMessage());
//		}
//	}

	/**
	 * post 上传文件请求, 获取 string 结果
	 * @param url
	 * @param params 附加参数
	 * @param files 文件列表
	 * @return
	 */
//	public static Result<String> postReturnString(String url, Map<String, String> params, Map<String, InputStream> files){
//		if(files == null || files.size() == 0){
//			return new Result<String>(false, "没有需要上传的文件!");
//		}
//		try {
//			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//			if(params != null){
//				for(Entry<String, String> kv : params.entrySet()){
//					builder.addTextBody(kv.getKey(), kv.getValue());
//				}
//			}
//			for(Entry<String, InputStream> file : files.entrySet()){
//				builder.addBinaryBody(file.getKey(), file.getValue(), ContentType.MULTIPART_FORM_DATA, file.getKey());
//			}
//			HttpEntity httpEntity = builder.build();
//			return postReturnString(url, httpEntity);
//		} catch (Exception e) {
//			return new Result<String>(false, e.getMessage());
//		}
//	}

	/**
	 * post 上传文件请求,获取 fastjson 结果
	 * @param url
	 * @param params
	 * @param files
	 * @return
	 */
//	public static Result<JSONObject> postReturnFastJson(String url, Map<String, String> params, Map<String, InputStream> files){
//		Result<String> ret = postReturnString(url, params, files);
//		if(!ret.isSuccess()){
//			return new Result<>(false, ret.getMsg());
//		}
//		try {
//			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
//			return new Result<JSONObject>(true, "",jsonObject);
//		} catch (Exception e) {
//			return new Result<JSONObject>(false, e.getMessage());
//		}
//	}

	/**
	 * post 上传文件请求,获取 fastjson 结果
	 * @param url
	 * @param params 附加参数
	 * @param files 文件列表
	 * @return
	 */
//	public static Result<JSONObject> postReturnFastJson(String url, Map<String, String> params, File... files){
//		Result<String> ret = postReturnString(url, params, files);
//		if(!ret.isSuccess()){
//			return new Result<>(false, ret.getMsg());
//		}
//		try {
//			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
//			return new Result<JSONObject>(true, "",jsonObject);
//		} catch (Exception e) {
//			return new Result<JSONObject>(false, e.getMessage());
//		}
//	}

	/**
	 * post 上传文件请求,获取 fastjson 结果
	 * @param url
	 * @param params 附加参数
	 * @param files
	 * @return
	 */
//	public static Result<JSONObject> postReturnFastJson(String url, Map<String, String> params, MultipartFile... files){
//		Result<String> ret = postReturnString(url, params, files);
//		if(!ret.isSuccess()){
//			return new Result<>(false, ret.getMsg());
//		}
//		try {
//			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
//			return new Result<JSONObject>(true, "",jsonObject);
//		} catch (Exception e) {
//			return new Result<JSONObject>(false, e.getMessage());
//		}
//	}
//	public static Result<JSONObject> postReturnFastJson1(String url, Map<String, String> params, List<MultipartFile> files){
//		Result<String> ret = postReturnString1(url, params, files);
//		if(!ret.isSuccess()){
//			return new Result<>(false, ret.getMsg());
//		}
//		try {
//			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
//			return new Result<JSONObject>(true, "",jsonObject);
//		} catch (Exception e) {
//			return new Result<JSONObject>(false, e.getMessage());
//		}
//	}

	/**
	 * post 请求, 获取 fastjson 结果
	 * @param url
	 * @param httpEntity
	 * @return
	 */
	public static Result<JSONObject> postReturnFastJson(String url, HttpEntity httpEntity){
		Result<String> ret = postReturnString(url, httpEntity);
		if(!ret.isSuccess()){
			return new Result<>(false, ret.getMsg());
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
			return new Result<JSONObject>(true, "",jsonObject);
		} catch (Exception e) {
			return new Result<JSONObject>(false, e.getMessage());
		}
	}

	/**
	 * post 上传文件请求获取 net.sj json 结果
	 * @param url
	 * @param params 附加参数
	 * @param files 文件列表
	 * @return
	 */
//	public static Result<JSONObject> postReturnNetsfJson(String url, Map<String, String> params, Map<String, InputStream> files){
//		Result<String> ret = postReturnString(url, params, files);
//		if(!ret.isSuccess()){
//			return new Result<>(false, ret.getMsg());
//		}
//		try {
//			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
//			return new Result<JSONObject>(true, "",jsonObject);
//		} catch (Exception e) {
//			return new Result<JSONObject>(false, e.getMessage());
//		}
//	}

	/**
	 * post 上传文件请求获取 net.sj json 结果
	 * @param url
	 * @param params 附加参数
	 * @param files 文件列表
	 * @return
	 */
//	public static Result<JSONObject> postReturnNetsfJson(String url, Map<String, String> params, File... files){
//		Result<String> ret = postReturnString(url, params, files);
//		if(!ret.isSuccess()){
//			return new Result<>(false, ret.getMsg());
//		}
//		try {
//			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
//			return new Result<JSONObject>(true, "",jsonObject);
//		} catch (Exception e) {
//			return new Result<JSONObject>(false, e.getMessage());
//		}
//	}

	/**
	 * post 上传文件请求获取 net.sj json 结果
	 * @param url
	 * @param params 附加参数
	 * @param httpEntity
	 * @return
	 */
//	public static Result<JSONObject> postReturnNetsfJson(String url, Map<String, String> params, MultipartFile... files){
//		Result<String> ret = postReturnString(url, params, files);
//		if(!ret.isSuccess()){
//			return new Result<>(false, ret.getMsg());
//		}
//		try {
//			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
//			return new Result<JSONObject>(true, "",jsonObject);
//		} catch (Exception e) {
//			return new Result<JSONObject>(false, e.getMessage());
//		}
//	}

	/**
	 * post 请求, 获取 net.sj json 结果
	 * @param url
	 * @param httpEntity
	 * @return
	 */
	public static Result<JSONObject> postReturnNetsfJson(String url, HttpEntity httpEntity){
		Result<String> ret = postReturnString(url, httpEntity);
		if(!ret.isSuccess()){
			return new Result<>(false, ret.getMsg());
		}
		try {
			JSONObject jsonObject = JSONObject.parseObject((ret.getData()));
			return new Result<JSONObject>(true, "",jsonObject);
		} catch (Exception e) {
			return new Result<JSONObject>(false, e.getMessage());
		}
	}
}
