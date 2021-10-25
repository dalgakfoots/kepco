package egovframework.com.vm.service.impl;

import org.springframework.stereotype.Service; 

import egovframework.com.vm.service.VmApiService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;


@Service("VmApiService")
public class VmApiServiceImpl extends EgovAbstractServiceImpl implements VmApiService {

//	private final String space = "KEPCO-MAIN";
//    private final String username = "covim";
//    private final String password = "C0resec1!";
//    private final String vAppId = "ebf9cf0f-75cd-4c02-aec2-1cf747b64e41";
////    private final String vmId = "vm-1b3d6073-3afb-4947-a468-18c8246631af";


	
	
	 
//	public String getTicketForUrl(String vmId) throws Exception{
//        try {
//            String cookie = getCookie();
//            if(cookie != null && cookie.length() > 0) {
//                Map<String, String> information = getInformation(cookie);
//                if (!information.isEmpty()) {
//                    Map<String, String> ticket = getTicket(cookie, information.get("session_id"), information.get("jwp"), information.get("orgId"), vmId);
//                    if (ticket != null) {
//                        // System.out.println("[Host] : " + ticket.get("host"));
//                        // System.out.println("[Vmx] : " + ticket.get("vmx"));
//                        System.out.println("[Ticket] : " + ticket.get("ticket"));
//                        
//                        return ticket.get("ticket");
//                        // System.out.println("[Port] : " + ticket.get("port"));
//                    } else {
//                        System.out.println("[ × ] 티켓 파싱 실패!");
//                    }
//                } else {
//                    System.out.println("[ × ] 세션 정보 획득 실패!");
//                }
//            } else {
//                System.out.println("[ × ] 로그인 실패!");
//            }
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//		return ticket;
//    }
//
//
//
//	    private String getDocPage(HttpURLConnection connection) throws Exception {
//	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
//	        String line;
//	        StringBuilder sb = new StringBuilder();
//	        while ((line = br.readLine()) != null)
//	            sb.append(line+"\n");
//	        br.close();
//	        return sb.toString();
//	    }
//
//	    private List<String> getRegex(String plainText, String regex) {
//	        List<String> returnList = new ArrayList<>();
//	        Pattern pattern = Pattern.compile(regex);
//	        Matcher matcher = pattern.matcher(plainText);
//	        while(matcher.find()){
//	            returnList.add(matcher.group(1));
//	        }
//	        return returnList;
//	    }
//
//	    public String getCookie()throws Exception{
//	        HttpURLConnection connection = null;
//	        String urlParameters = "service=tenant:"+space+"&username="+username+"&password="+password;
//	        byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
//	        int postDataLength = postData.length;
//	        URL url= new URL( "https://vmware.ncloud.com/login/security_check" );
//	        System.out.println("[Connecting...] https://vmware.ncloud.com/login/security_check?service=tenant:"+space+"&username="+username+"&password="+password);
//	        connection= (HttpURLConnection) url.openConnection();
//	        connection.setRequestMethod( "POST" );
//	        connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
//
//	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//	        connection.setRequestProperty ("Accept", "*/*");
//	        connection.setRequestProperty ("Accept-Encoding", "gzip, deflate, br");
//	        connection.setRequestProperty ("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6,zh-TW;q=0.5,zh;q=0.4");
//	        connection.setRequestProperty ("Connection", "keep-alive");
//	        connection.setRequestProperty ("Content-type", "application/x-www-form-urlencoded");
//	        connection.setRequestProperty ("Cookie", "ncp_lang=ko-KR; ncp_nnb=U2FsdGVkX1%2B9YHwKcIAqB8fsZbKRunSxKjNNEEOEsj7aDLV2G45u7HMZdLP3EkTv3ckQPFyVs9q8Io%2Bh2XG8GostPeNQpdIQ5YzcZ3lb45ZLtC9H9Ykscj72%2BIOXazVU; _pk_ses..9c73=0");
//	        connection.setRequestProperty ("Host", "vmware.ncloud.com");
//	        connection.setRequestProperty ("Origin", "https://vmware.ncloud.com");
//	        connection.setRequestProperty ("Referer", "https://vmware.ncloud.com/login/?service=tenant:"+space+"&redirectTo=%2Ftenant%2F"+space);
//	        connection.setRequestProperty ("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");
//	        connection.setRequestProperty ("sec-ch-ua-mobile", "?0");
//	        connection.setRequestProperty ("Sec-Fetch-Dest", "empty");
//	        connection.setRequestProperty ("Sec-Fetch-Mode", "cors");
//	        connection.setRequestProperty ("Sec-Fetch-Site", "same-origin");
//	        connection.setRequestProperty ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
//
//	        connection.setDoInput(true);
//	        connection.setDoOutput( true );
//	        connection.setUseCaches(true);
//	        connection.setDefaultUseCaches(true);
//	        connection.setInstanceFollowRedirects( true );
//
//	        try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
//	            wr.write( postData );
//	        }
//	        connection.connect();
//	        Map<String, List<String>> header = connection.getHeaderFields();
//	        StringBuilder sb = new StringBuilder();
//	        if(header.containsKey("Set-Cookie")) {
//	            List<String> cookie = header.get("Set-Cookie");
//	            for (int count = 0; count < cookie.size(); count ++) {
//	                sb.append(cookie.get(count));
//	            }
//	        }
//	        connection.disconnect();
//	        return sb.toString();
//	    }
//
//	    public Map<String, String> getInformation(String cookie) throws Exception{
//	        Map<String, String> returnMap = new HashMap<>();
//	        URL url = new URL("https://vmware.ncloud.com/tenant/"+space+"/vdcs/"+ vAppId +"/vm");
//	        System.out.println("[Connecting...] https://vmware.ncloud.com/tenant/"+space+"/vdcs/"+ vAppId +"/vm");
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	        connection.setRequestMethod("GET");
//	        // 이전 로그인 세션 정보 적용
//	        connection.setRequestProperty( "Cookie", cookie);
//
//	        String docText = getDocPage(connection);
//
//	        // 정규표현식으로 파싱
//	        List<String> varList = getRegex(docText, "var.+= '(.+)'");
//	        List<String> orgIdList = getRegex(docText, "orgId : '(.+)'");
//
//	        if(varList.size() > 2 && ! orgIdList.isEmpty()) {
//	            returnMap.put("session_id", varList.get(0));
//	            returnMap.put("jwp", varList.get(2));
//	            returnMap.put("orgId", orgIdList.get(0));
//	        }
//	        return returnMap;
//	    }
//
//	    public Map<String, String> getTicket(String cookie, String sessionId, String jwp, String orgId, String vmId)throws Exception {
//	        HttpURLConnection connection = null;
//	        URL url= new URL( "https://vmware.ncloud.com/api/vApp/"+ vmId +"/screen/action/acquireMksTicket" );
//	        System.out.println("[Connecting...] https://vmware.ncloud.com/api/vApp/"+ vmId +"/screen/action/acquireMksTicket");
//	        connection= (HttpURLConnection) url.openConnection();
//	        connection.setRequestMethod( "POST" );
//	        connection.setRequestProperty("Accept", "application/*+xml;version=32.0");
//	        connection.setRequestProperty ("Accept-Encoding", "gzip, deflate, br");
//	        connection.setRequestProperty ("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
//	        connection.setRequestProperty ("Authorization", "Bearer '+jwp");
//	        connection.setRequestProperty ("Cache-Control", "no-cache, no-store, must-revalidate");
//	        connection.setRequestProperty ("Connection", "keep-alive");
//	        connection.setRequestProperty ("Content-Length", "0");
//	        connection.setRequestProperty ("Content-Type", "application/*+xml;charset=utf-8");
//	        connection.setRequestProperty ("Cookie", cookie);
//	        connection.setRequestProperty ("Host", "vmware.ncloud.com");
//	        connection.setRequestProperty ("Origin", "https://vmware.ncloud.com");
//	        connection.setRequestProperty ("Pragma", "no-cache");
//	        connection.setRequestProperty ("Referer", "https://vmware.ncloud.com/tenant/"+space+"/vdcs/"+ vAppId +"/vm");
//	        connection.setRequestProperty ("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");
//	        connection.setRequestProperty ("sec-ch-ua-mobile", "?0");
//	        connection.setRequestProperty ("Sec-Fetch-Dest", "empty");
//	        connection.setRequestProperty ("Sec-Fetch-Mode", "cors");
//	        connection.setRequestProperty ("Sec-Fetch-Site", "same-origin");
//	        connection.setRequestProperty ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
//	        connection.setRequestProperty ("X-Requested-With", "XMLHttpRequest");
//	        connection.setRequestProperty ("x-vcloud-authorization", sessionId);
//	        connection.setRequestProperty ("x-vmware-vcloud-auth-context", "WebConsole-test");
//	        connection.setRequestProperty ("X-VMWARE-VCLOUD-TENANT-CONTEXT\"", orgId);
//
//	        connection.setDoInput(true);
//	        connection.setDoOutput( true );
//	        connection.setUseCaches(true);
//	        connection.setDefaultUseCaches(true);
//	        connection.setInstanceFollowRedirects( true );
//
//	        String docText = getDocPage(connection);
//
//	        List<String> hostList = getRegex(docText, "<Host>(.+)</Host>");
//	        List<String> vmxList = getRegex(docText, "<Vmx>(.+)</Vmx>");
//	        List<String> wssWebsocketList = getRegex(docText, "<Ticket>(.+)</Ticket>");
//	        List<String> portList = getRegex(docText, "<Port>(.+)</Port>");
//
//	        if(hostList.isEmpty() || vmxList.isEmpty() || wssWebsocketList.isEmpty() || portList.isEmpty()) {
//	            return null;
//	        } else {
//	            Map<String, String> returnMap = new HashMap<>();
//	            returnMap.put("host", hostList.get(0));
//	            returnMap.put("vmx", vmxList.get(0));
//	            returnMap.put("ticket", wssWebsocketList.get(0));
//	            returnMap.put("port", portList.get(0));
//	            return returnMap;
//	        }
//	    }
	    
	private final String space = "KEPCO-2st";
    private final String username = "covim";
    private final String password = "C0resec1!";
    private final String vAppId = "07f37287-5010-4d92-941b-c8215a2c9f8d";
//    private final String vmId = "vm-727b4bac-0f6a-41aa-945b-e9ea42521e57";
    
    
    public String getTicketForUrl(String vmId) throws Exception {
    	String res = null;
        try {
            String cookie = getCookie();
            if(cookie != null && cookie.length() > 0) {
                Map<String, String> information = getInformation(cookie);
                if ( ! information.isEmpty()) {
                    Map<String, String> ticket = getTicket(information.get("session_id"), information.get("jwp"), information.get("orgId"), vmId);
                    res = ticket.get("ticket");
                    if (ticket != null) {
                        System.out.println("[Host] : " + ticket.get("host"));
                        System.out.println("[Vmx] : " + ticket.get("vmx"));
                        System.out.println("[Ticket] : " + ticket.get("ticket"));
                        System.out.println("[Port] : " + ticket.get("port"));
                    } else {
                        System.out.println("[ × ] 티켓 파싱 실패!");
                    }
                } else {
                    System.out.println("[ × ] 세션 정보 획득 실패!");
                }
            } else {
                System.out.println("[ × ] 로그인 실패!");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
        return res;
    }

    private String getDocPage(HttpURLConnection connection) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null)
            sb.append(line+"\n");
        br.close();
        return sb.toString();
    }

    
    private List<String> getRegex(String plainText, String regex) {
        List<String> returnList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(plainText);
        while(matcher.find()){
            returnList.add(matcher.group(1));
        }
        return returnList;
    }
    

    public String getCookie()throws Exception{
        HttpURLConnection connection = null;
        URL url= new URL( "https://vmware.ncloud.com/cloudapi/1.0.0/sessions" );
        System.out.println("[Connecting...] https://vmware.ncloud.com/cloudapi/1.0.0/sessions");
        connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod( "POST" );
        connection.setRequestProperty ("Accept", "application/json;version=34.0");
        connection.setRequestProperty ("Content-Type", "application/json;version=34.0");
        connection.setRequestProperty ("Authorization", "Basic " + Base64.getEncoder().encodeToString(new String(username+"@"+space+":"+password).getBytes()));
        connection.setRequestProperty ("Host", "vmware.ncloud.com");
        connection.setRequestProperty ("Origin", "https://vmware.ncloud.com");
        connection.setRequestProperty ("Referer", "https://vmware.ncloud.com/login/?service=tenant:" + space + "&redirectTo=%2Ftenant%2F"+space);
        connection.setRequestProperty ("sec-ch-ua", "Chromium\";v=\"94\", \"Google Chrome\";v=\"94\", \";Not A Brand\";v=\"99\"");
        connection.setRequestProperty ("sec-ch-ua-mobile", "?0");
        connection.setRequestProperty ("sec-ch-ua-platform", "\"Windows\"");
        connection.setRequestProperty ("Sec-Fetch-Dest", "empty");
        connection.setRequestProperty ("Sec-Fetch-Mode", "cors");
        connection.setRequestProperty ("Sec-Fetch-Site", "same-origin");
        connection.setRequestProperty ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36");
        connection.setDoInput(true);
        connection.setDoOutput( true );
        connection.setUseCaches(true);
        connection.setDefaultUseCaches(true);
        connection.setInstanceFollowRedirects( true );

        connection.connect();
        Map<String, List<String>> header = connection.getHeaderFields();
        StringBuilder sb = new StringBuilder();
        if(header.containsKey("Set-Cookie")) {
            List<String> cookie = header.get("Set-Cookie");
            for (int count = 0; count < cookie.size(); count ++) {
                sb.append(cookie.get(count));
            }
        }
        connection.disconnect();
        return sb.toString();
    }
    

    public Map<String, String> getInformation(String cookie) throws Exception{
        Map<String, String> returnMap = new HashMap<>();
        URL url = new URL("https://vmware.ncloud.com/tenant/"+space+"/vdcs/"+vAppId+"/vm");
        System.out.println("[Connecting...] https://vmware.ncloud.com/tenant/"+space+"/vdcs/"+ vAppId +"/vm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // 이전 로그인 세션 정보 적용
        connection.setRequestProperty( "Cookie", cookie);

        String docText = getDocPage(connection);

        // 정규표현식으로 파싱
        List<String> varList = getRegex(docText, "var.+= '(.+)'");
        List<String> orgIdList = getRegex(docText, "orgId : '(.+)'");

        if(varList.size() > 2 && ! orgIdList.isEmpty()) {
            returnMap.put("session_id", varList.get(0));
            returnMap.put("jwp", varList.get(2));
            returnMap.put("orgId", orgIdList.get(0));
        }
        return returnMap;
    }

    public Map<String, String> getTicket(String sessionId, String jwp, String orgId, String vmId)throws Exception {
        HttpURLConnection connection = null;
        URL url= new URL( "https://vmware.ncloud.com/api/vApp/"+ vmId +"/screen/action/acquireMksTicket" );
        System.out.println("[Connecting...] https://vmware.ncloud.com/api/vApp/"+ vmId +"/screen/action/acquireMksTicket");
        connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod( "POST" );
        connection.setRequestProperty("Accept", "application/*+xml;version=32.0");
        connection.setRequestProperty ("Accept-Encoding", "gzip, deflate, br");
        connection.setRequestProperty ("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        connection.setRequestProperty ("Authorization", "Bearer "+jwp);
        connection.setRequestProperty ("Cache-Control", "no-cache, no-store, must-revalidate");
        connection.setRequestProperty ("Connection", "keep-alive");
        connection.setRequestProperty ("Content-Length", "0");
        connection.setRequestProperty ("Content-Type", "application/*+xml;charset=utf-8");
        connection.setRequestProperty ("Cookie", "ncp_lang=ko-KR; ncp_nnb=U2FsdGVkX1%2BQdx%2FPQjfiujv9HSk5fyFudQPo40LnhOVIPjyxbuPpC8msy%2F%2FBqhWC0jLhoYQvt8S%2BzEASjNVvZMyaXwKd4pNgVlVqxzckAlM3tqXiaF0TnJf2uZ9e1Ci9; ncp_region=KR; ncp_platform=CLASSIC; ncp_locale=ko_KR; ncp=\"pvwq05UcvI7d1INS4vdIvvKg+F5OwXSX0g2N7m5VO8sjfgb9psLA3G1CsFVfgHpPTEOMYxEylXnCYuZKC6aYT9T1GE820uPEVwepQjZ9G9g3ZIckHBp2flp/j2CZfGKgns4F3tvRBYPrIkE6qvWo/sEGkeJO408NGEMLRghuqxxP37noD2CCfFwHx819eZdW+Fquk0+mfwX7BdPw0/9zfIOhKTqT6jYW69C6Fo+zYJnQK8epfRVqXcag88Hqoy4PTWjWAxYIhxkeRzVG1mNNd87bMXQP7G5U5cf3uDv6owMbNY0Bnd+5Yh/YhYXL/Ye/VkSkIVMJc/fZwYjMosWW4DK7Qpwz2MxYqAE1jA3gjx6UJSZv8TvfQs3DkeecrTBA5J3SksF9p+1GtgXSCzHAzSjaIdlSc0UABb9CwNX2DyLvi4NLmJv8dyl2RTJ5HSZauRnahz+C0ku5EuxmVl8Mqw==\"; ncp_version=v2; ncp_my_session_key=my_session:auth.ncloud.com:2548251_3k3gz37kn4; vcloud_jwt=eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjb2RtaW4iLCJpc3MiOiJkMjkyZGM4Ny1jNmJkLTRiMjUtOTk0MC05ZDY5NjA1MTUzYjBANmIyNGU0MzgtZDFkNy00Nzc0LTk1MGItMmY2YWJlZmViMmUxIiwiZXhwIjoxNjI1MjA0OTQxLCJ2ZXJzaW9uIjoidmNsb3VkXzEuMCIsImp0aSI6IjRmYWFlNTBiZDM1NTRiNjk4ODkwYjYyMWE3ODIxYzk3In0.d59Rgjf16kXIPNitoqDjf-W8mp4DxW9lQzzv_ALw5NAEl0AFpu48gbTRma84KrA08u8Jc35lw_sY6UKxUPFF7rnifGdjuUPKpFNYnoGuFbWkXg7VsUb8t3sOm5ZzFsAHT6OZ4ElkfZjJ4pTfpxZFJ_lcYZth2KA7zHV5iQWDNBr9o0Yc4TIAMvaymM_ci88owopLB5QfshcNqitJJzBxKEvct-pjQmBTRjByGUlWBJ5GMv0od3uzgrax-vqqU-P3ObjP44V2zF7HL-Icirn1uAsZpFEcRUOGR41SHqtULJ_X0NmvLc3Zm-XZe-0LpyGl0P5pz5W8OkUpNwl_VTmhJg; vcloud_session_id=4faae50bd3554b698890b621a7821c97; _pk_ses..9c73=0");
        connection.setRequestProperty ("Expires", "0");
        connection.setRequestProperty ("Host", "vmware.ncloud.com");
        connection.setRequestProperty ("Origin", "https://vmware.ncloud.com");
        connection.setRequestProperty ("Pragma", "no-cache");
        connection.setRequestProperty ("Referer", "https://vmware.ncloud.com/tenant/"+space+"/vdcs/"+ vAppId +"/vm");
        connection.setRequestProperty ("sec-ch-ua", "\" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"91\", \"Chromium\";v=\"91\"");
        connection.setRequestProperty ("sec-ch-ua-mobile", "?0");
        connection.setRequestProperty ("Sec-Fetch-Dest", "empty");
        connection.setRequestProperty ("Sec-Fetch-Mode", "cors");
        connection.setRequestProperty ("Sec-Fetch-Site", "same-origin");
        connection.setRequestProperty ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        connection.setRequestProperty ("X-Requested-With", "XMLHttpRequest");
        connection.setRequestProperty ("x-vcloud-authorization", sessionId);
        connection.setRequestProperty ("x-vmware-vcloud-auth-context", space);
        connection.setRequestProperty ("X-VMWARE-VCLOUD-TENANT-CONTEXT\"", orgId);

        connection.setDoInput(true);
        connection.setDoOutput( true );
        connection.setUseCaches(true);
        connection.setDefaultUseCaches(true);
        connection.setInstanceFollowRedirects( true );

        String docText = getDocPage(connection);

        List<String> hostList = getRegex(docText, "<Host>(.+)</Host>");
        List<String> vmxList = getRegex(docText, "<Vmx>(.+)</Vmx>");
        List<String> wssWebsocketList = getRegex(docText, "<Ticket>(.+)</Ticket>");
        List<String> portList = getRegex(docText, "<Port>(.+)</Port>");

        if(hostList.isEmpty() || vmxList.isEmpty() || wssWebsocketList.isEmpty() || portList.isEmpty()) {
            return null;
        } else {
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("host", hostList.get(0));
            returnMap.put("vmx", vmxList.get(0));
            returnMap.put("ticket", wssWebsocketList.get(0));
            returnMap.put("port", portList.get(0));
            return returnMap;
        }
    }

}