/**
 * @author Tony
 * @date 2018-01-10
 * @project rest_demo
 */
package com.edgedo.common.msgtool.ucpaas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.edgedo.common.msgtool.ucpaas.client.AbsRestClient;
import com.edgedo.common.msgtool.ucpaas.client.JsonReqClient;
import org.apache.commons.lang.StringUtils;


public class RestTest {

	static AbsRestClient InstantiationRestAPI() {
		return new JsonReqClient();
	}

	public static void testSendSms(String sid, String token, String appid, String templateid, String param, String mobile, String uid){
		try {
			String result=InstantiationRestAPI().sendSms(sid, token, appid, templateid, param, mobile, uid);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testSendSmsBatch(String sid, String token, String appid, String templateid, String param, String mobile, String uid){
		try {
			String result=InstantiationRestAPI().sendSmsBatch(sid, token, appid, templateid, param, mobile, uid);
			//System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testAddSmsTemplate(String sid, String token, String appid, String type, String template_name, String autograph, String content){
		try {
			String result=InstantiationRestAPI().addSmsTemplate(sid, token, appid, type, template_name, autograph, content);
			//System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void testGetSmsTemplate(String sid, String token, String appid, String templateid, String page_num, String page_size){
		try {
			String result=InstantiationRestAPI().getSmsTemplate(sid, token, appid, templateid, page_num, page_size);
			//System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void testEditSmsTemplate(String sid, String token, String appid, String templateid, String type, String template_name, String autograph, String content){
		try {
			String result=InstantiationRestAPI().editSmsTemplate(sid, token, appid, templateid, type, template_name, autograph, content);
			//System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void testDeleterSmsTemplate(String sid, String token, String appid, String templateid){
		try {
			String result=InstantiationRestAPI().deleterSmsTemplate(sid, token, appid, templateid);
			//System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * ????????????  ??????main???????????????????????????????????????(???????????? ?????????????????????)??????????????????
	 * ??????????????????????????????rest api ??????
	 * @throws IOException
	 * @method main
	 */
//	public static void main(String[] args) throws IOException{
//
//		System.out.println("??????????????????????????????(??????1),Enter?????????:");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		String methodNumber = br.readLine();
//
//		if (StringUtils.isBlank(methodNumber)){
//			System.out.println("???????????????????????????????????????");
//			return;
//		}
//
//		if (methodNumber.equals("1")) {  //??????????????????
//			String sid = "2aae49f0708762c432acc46ed3431d34";
//			String token = "1bcbfe20a35b39635b64ac4a5af56ab8";
//			String appid = "40b0add7510f4135b02db9bde0ddb6bc";
//			String templateid = "492296";
//			String param = "112211,ASD";
//			String mobile = "17633351285";
//			String uid = "";
//			testSendSms(sid, token, appid, templateid, param, mobile, uid);
//		} else if (methodNumber.equals("2")) { //??????????????????
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			String param = "";
//			String mobile = "";
//			String uid = "";
//			testSendSmsBatch(sid, token, appid, templateid, param, mobile, uid);
//		} else if (methodNumber.equals("3")) {  //????????????
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String type = "";
//			String template_name = "";
//			String autograph = "";
//			String content = "";
//			testAddSmsTemplate(sid, token, appid, type, template_name, autograph, content);
//		} else if (methodNumber.equals("4")) {  //????????????
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			String page_num = "";
//			String page_size = "";
//			testGetSmsTemplate(sid, token, appid, templateid, page_num, page_size);
//		} else if (methodNumber.equals("5")) {  //????????????
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			String type = "";
//			String template_name = "";
//			String autograph = "";
//			String content = "";
//			testEditSmsTemplate(sid, token, appid, templateid, type, template_name, autograph, content);
//		} else if (methodNumber.equals("6")) {  //????????????
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			testDeleterSmsTemplate(sid, token, appid, templateid);
//		}
//	}
}
