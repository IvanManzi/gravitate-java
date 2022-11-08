package com.util;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SendRequestUtils
{
    public static final String HTTP_METHOD_POST = "POST";

    public static final String HTTP_METHOD_GET = "GET";
    private static final String pindoToken = "eyJhbGciOiJub25lIn0.eyJpZCI6NjE3LCJyZXZva2VkX3Rva2VuX2NvdW50IjowfQ.";
    private static final String apiKey = "LazEX6t1KGdhEIeD7qxUr/zOD5+yWcA+ZKb3icrvAaFCagZN1fjjZR/30AWq1/OGTn9/AlTi7wNgkLYmDpteZA==";
    private static final String restEndpoint = "https://rest-ww.telesign.com";




    /**
     * 发送验证码到手机号
     *
     * @return String
     * @Param String phone, String verificationCode
     */

    public static boolean sendPhoneNumberVerificationCode(String phoneNumber, String verificationCode) throws IOException, JSONException {
        String message = "Your Sawa verification code is " + verificationCode;
        String messageSender = "SawaMob";

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to",phoneNumber);
        jsonObject.put("text",message);
        jsonObject.put("sender", messageSender);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url("https://api.pindo.io/v1/sms/")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + pindoToken)
                .build();
        Response response = client.newCall(request).execute();
        return true;
    }



    /**
     * 对包含中文的字符串进行转码，此为UTF-8服务器那边要进行一次解码
     *
     * @param value
     * @return
     * @throws Exception
     */
    private static String encode(String value ) throws Exception
    {
        return URLEncoder.encode( value, Constants.ENCODE );
    }


    public static String sendHttp(String url, Map<String, String> paramMap, String strEncoding ) throws Exception
    {
        if ( url == null || "".equals( url ) )
        {
            throw new Exception( "Please provide the remote request address." );
        }
        DataOutputStream out = null;
        BufferedInputStream reader = null;
        HttpURLConnection connection = null;
        try
        {
            StringBuilder sb = new StringBuilder();
            for ( Map.Entry<String,String> entry : paramMap.entrySet() )
            {
                if (entry.getValue()!=null&&!entry.getValue().equals("null")) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append("&");
                }
            }
            if ( sb.length() > 0 )
            {
                sb.deleteCharAt( sb.length() - 1 );
            }
            URL u = new URL( url );
            connection = (HttpURLConnection)u.openConnection();
            int code;
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setConnectTimeout( 180000 ); // 连接超时为3分钟
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "accept", "*/*" );
            connection.setRequestProperty( "Content-Length", String.valueOf( sb.length() ) );
            connection.setRequestProperty( "User-Agent", "Mozilla/4.0" );
            connection.setRequestProperty( "Content-Type", "application/x-www-com.admin_service.form-urlencoded; text/html; charset=" + strEncoding );
            out = new DataOutputStream( connection.getOutputStream() );
            // 写入参数
            out.write( String.valueOf( sb ).getBytes( strEncoding ) );
            code = connection.getResponseCode();
            if ( code == HttpURLConnection.HTTP_OK )
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                reader = new BufferedInputStream( connection.getInputStream() );
                int b;
                byte[] bit = new byte[1024];
                while ( -1 != (b = reader.read( bit )) )
                {
                    byteArrayOutputStream.write( bit, 0, b );
                }
                return new String( byteArrayOutputStream.toByteArray(), strEncoding );
            }
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            if ( out != null )
            {
                out.flush();
                out.close();
            }
            if ( reader != null )
            {
                reader.close();
            }
            if ( connection != null )
            {
                connection.disconnect();
            }
        }
        return null;
    }


    public static String sendHttps(String url, String sb, String strEncoding ) throws Exception
    {
        if ( url == null || "".equals( url ) )
        {
            throw new Exception( "Please provide the remote request address." );
        }
        DataOutputStream out = null;
        BufferedInputStream reader = null;
        HttpURLConnection connection = null;
        try
        {

            URL u = new URL( url );
            connection = (HttpURLConnection)u.openConnection();
            int code;
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setConnectTimeout( 180000 ); // 连接超时为3分钟
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "accept", "*/*" );
            connection.setRequestProperty( "Content-Length", String.valueOf( sb.length() ) );
            connection.setRequestProperty( "User-Agent", "Mozilla/4.0" );
            connection.setRequestProperty( "Content-Type", "application/x-www-com.admin_service.form-urlencoded; text/html; charset=" + strEncoding );
            out = new DataOutputStream( connection.getOutputStream() );
            // 写入参数
            out.write( sb.getBytes( strEncoding ) );
            code = connection.getResponseCode();
            if ( code == HttpURLConnection.HTTP_OK )
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                reader = new BufferedInputStream( connection.getInputStream() );
                int b;
                byte[] bit = new byte[1024];
                while ( -1 != (b = reader.read( bit )) )
                {
                    byteArrayOutputStream.write( bit, 0, b );
                }
                return new String( byteArrayOutputStream.toByteArray(), strEncoding );
            }
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            if ( out != null )
            {
                out.flush();
                out.close();
            }
            if ( reader != null )
            {
                reader.close();
            }
            if ( connection != null )
            {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 模拟HTTP 请求 提交json数据
     *
     * @return
     */
    public static String sendHttpToJson(String url, String paramMap, String strEncoding ) throws Exception
    {
        if ( url == null || "".equals( url ) )
        {
            throw new Exception( "Please provide the remote request address." );
        }
        DataOutputStream out = null;
        BufferedInputStream reader = null;
        HttpURLConnection connection = null;
        try
        {
            URL u = new URL( url );
            connection = (HttpURLConnection)u.openConnection();
            int code;
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setConnectTimeout( 180000 ); // 连接超时为3分钟
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "Content-Length", paramMap );
            connection.setRequestProperty( "User-Agent", "Mozilla/4.0" );
            connection.setRequestProperty( "Content-Type", "application/json;charset=" + strEncoding );
            out = new DataOutputStream( connection.getOutputStream() );
            // 写入参数
            out.write( String.valueOf( paramMap ).getBytes( strEncoding ) );
            code = connection.getResponseCode();
            if ( code == HttpURLConnection.HTTP_OK )
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                reader = new BufferedInputStream( connection.getInputStream() );
                int b;
                byte[] bit = new byte[1024];
                while ( -1 != (b = reader.read( bit )) )
                {
                    byteArrayOutputStream.write( bit, 0, b );
                }
                return new String( byteArrayOutputStream.toByteArray(), strEncoding );
            }
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            if ( out != null )
            {
                out.flush();
                out.close();
            }
            if ( reader != null )
            {
                reader.close();
            }
            if ( connection != null )
            {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 模拟HTTP 请求 提交普通数据
     *
     * @return
     */
    public static String sendHttp(String url, String paramMap, String strEncoding ) throws Exception
    {
        if ( url == null || "".equals( url ) )
        {
            throw new Exception( "Please provide the remote request address." );
        }
        DataOutputStream out = null;
        BufferedInputStream reader = null;
        HttpURLConnection connection = null;
        try
        {
            URL u = new URL( url );
            connection = (HttpURLConnection)u.openConnection();
            int code;
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setConnectTimeout( 180000 ); // 连接超时为3分钟
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "Content-Length", paramMap );
            connection.setRequestProperty( "User-Agent", "Mozilla/4.0" );
            connection.setRequestProperty( "Content-Type", "application/x-www-com.admin_service.form-urlencoded; text/html; charset=" + strEncoding );
            out = new DataOutputStream( connection.getOutputStream() );
            // 写入参数
            out.write( String.valueOf( paramMap ).getBytes( strEncoding ) );
            code = connection.getResponseCode();
            if ( code == HttpURLConnection.HTTP_OK )
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                reader = new BufferedInputStream( connection.getInputStream() );
                int b;
                byte[] bit = new byte[1024];
                while ( -1 != (b = reader.read( bit )) )
                {
                    byteArrayOutputStream.write( bit, 0, b );
                }
                return new String( byteArrayOutputStream.toByteArray(), strEncoding );
            }
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            if ( out != null )
            {
                out.flush();
                out.close();
            }
            if ( reader != null )
            {
                reader.close();
            }
            if ( connection != null )
            {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * http上传文件方法
     *
     * @param url
     *            -->请求地址
     * @param paramMap
     *            -->传输参数
     * @param file
     *            --->上传文件集合
     * @return
     * @throws Exception
     */
    public static String sendHttpFile(String url, Map<String, String> paramMap, Collection<File> file ) throws Exception
    {
        return new String( sendHttp( url, paramMap, file ) );
    }

    /**
     * 模拟HTTP 请求 进行上传文件
     *
     * @param url
     * @param paramMap
     * @param file
     * @return
     * @throws Exception
     */
    @SuppressWarnings( "static-access" )
    private static byte[] sendHttp(String url, Map<String, String> paramMap, Collection<File> file ) throws Exception
    {
        if ( url == null || "".equals( url ) )
        {
            throw new Exception( "Please provide the remote request address." );
        }
        DataOutputStream out = null;
        BufferedInputStream reader = null;
        HttpURLConnection connection = null;
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/com.admin_service.form-data";
        String CHARSET = Constants.ENCODE;
        try
        {
            URL u = new URL( url );
            int code;
            connection = (HttpURLConnection)u.openConnection();
            connection.setDoInput( true );// 允许输入
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setConnectTimeout( 10000 ); // 连接超时为10秒
            connection.setRequestMethod( HTTP_METHOD_POST );
            connection.setRequestProperty( "connection", "keep-alive" );
            connection.setRequestProperty( "Charsert", CHARSET );
            connection.setRequestProperty( "Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY );
            StringBuilder sb = new StringBuilder();
            for ( Map.Entry<String, String> entry : paramMap.entrySet() )
            {
                sb.append( PREFIX );
                sb.append( BOUNDARY );
                sb.append( LINEND );
                sb.append( "Content-Disposition: com.admin_service.form-data; name=\"" + entry.getKey() + "\"" + LINEND );
                sb.append( "Content-Type: text/plain; charset=" + CHARSET + LINEND );
                sb.append( "Content-Transfer-Encoding: 8bit" + LINEND );
                sb.append( LINEND );
                sb.append( entry.getValue() );
                sb.append( LINEND );
            }
            out = new DataOutputStream( connection.getOutputStream() );
            out.write( sb.toString().getBytes() );
            for ( File f : file )
            {
                setFile( out, f, BOUNDARY, CHARSET );
            }
            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            out.write( end_data );
            out.flush();
            code = connection.getResponseCode();
            if ( code == connection.HTTP_OK )
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                reader = new BufferedInputStream( connection.getInputStream() );
                byte[] bit = new byte[1024];
                int b = 0;
                while ( -1 != (b = reader.read( bit )) )
                {
                    byteArrayOutputStream.write( bit, 0, b );
                }
                return byteArrayOutputStream.toByteArray();
            }
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            if ( out != null )
            {
                out.flush();
                out.close();
            }
            if ( reader != null )
            {
                reader.close();
            }
            if ( connection != null )
            {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 后台上传时候设置文件方法
     *
     * @param out
     *            输出流
     * @param file
     *            本地文件对象
     * @param BOUNDARY
     *            文件分割符号
     * @param CHARSET
     *            字符编码
     * @throws Exception
     */
    private static void setFile(DataOutputStream out, File file, String BOUNDARY, String CHARSET ) throws Exception
    {
        FileInputStream in = null;
        try
        {
            String PREFIX = "--", LINEND = "\r\n";
            out.writeBytes( PREFIX );
            out.writeBytes( BOUNDARY );
            out.writeBytes( LINEND );
            out.writeBytes( "Content-Disposition: com.admin_service.form-data; name=\"file\"; filename=\"" + encode( file.getName() ) + "\"" + LINEND );
            out.writeBytes( "Content-Type: application/octet-stream; charset=" + CHARSET + LINEND );
            out.writeBytes( LINEND );
            in = new FileInputStream( file );
            byte[] b = new byte[1024];
            int n;
            while ( (n = in.read( b )) != -1 )
            {
                out.write( b, 0, n );
            }
            out.writeBytes( LINEND );
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            //IOUtils.closeQuietly( in );
        }
    }

    /**
     * 请求指定url,返回图片
     *
     * @return
     */
    public static byte[] downImgUrl( String url ) throws Exception
    {
        if ( url == null || "".equals( url ) )
        {
            throw new Exception( "Please provide the remote request address." );
        }
        BufferedInputStream reader = null;
        HttpURLConnection connection = null;
        try
        {
            URL u = new URL( url );
            connection = (HttpURLConnection)u.openConnection();
            int code;
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setConnectTimeout( 180000 ); // 连接超时为3分钟
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "User-Agent", "Mozilla/4.0" );
            // 写入参数
            code = connection.getResponseCode();
            if ( code == HttpURLConnection.HTTP_OK )
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                reader = new BufferedInputStream( connection.getInputStream() );
                int b;
                byte[] bit = new byte[1024];
                while ( -1 != (b = reader.read( bit )) )
                {
                    byteArrayOutputStream.write( bit, 0, b );
                }
                return byteArrayOutputStream.toByteArray();
            }
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            if ( reader != null )
            {
                reader.close();
            }
            if ( connection != null )
            {
                connection.disconnect();
            }
        }
        return null;
    }


    public static OutputStream sendHttpToIMG(String url, Map<String, String> paramMap, String strEncoding , OutputStream toClient) throws Exception
    {
        if ( url == null || "".equals( url ) )
        {
            throw new Exception( "Please provide the remote request address." );
        }
        DataOutputStream out = null;
        BufferedInputStream reader = null;
        HttpURLConnection connection = null;
        try
        {
            StringBuilder sb = new StringBuilder();
            for ( Map.Entry<String,String> entry : paramMap.entrySet() )
            {
                if (entry.getValue()!=null&&!entry.getValue().equals("null")) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append("&");
                }
            }
            if ( sb.length() > 0 )
            {
                sb.deleteCharAt( sb.length() - 1 );
            }
            URL u = new URL( url );
            connection = (HttpURLConnection)u.openConnection();
            int code;
            connection.setDoOutput( true );
            connection.setUseCaches( false );
            connection.setConnectTimeout( 180000 ); // 连接超时为3分钟
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "accept", "*/*" );
            connection.setRequestProperty( "Content-Length", String.valueOf( sb.length() ) );
            connection.setRequestProperty( "User-Agent", "Mozilla/4.0" );
            connection.setRequestProperty( "Content-Type", "application/x-www-com.admin_service.form-urlencoded; text/html; charset=" + strEncoding );
            out = new DataOutputStream( connection.getOutputStream() );
            // 写入参数
            out.write( String.valueOf( sb ).getBytes( strEncoding ) );
            code = connection.getResponseCode();
            if ( code == HttpURLConnection.HTTP_OK )
            {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                reader = new BufferedInputStream( connection.getInputStream() );
                InputStream inputStream = connection.getInputStream();
                byte[] data = new byte[1024];
                // 得到向客户端输出二进制数据的对象
                int len =0;
                while ((len = inputStream.read(data)) != -1) {
                    toClient.write(data,0,len);
                }
                return toClient;
            }
        } catch ( Exception e )
        {
            e.printStackTrace();
            throw e;
        } finally
        {
            if ( out != null )
            {
                out.flush();
                out.close();
            }
            if ( reader != null )
            {
                reader.close();
            }
            if ( connection != null )
            {
                connection.disconnect();
            }
        }
        return null;
    }




    //随机数
    private static Random random = new Random();
    private static String account = "HviewTechLtd";
    private static String userid = "1322";
    private static String pswd = "147258";
    private static String url = "http://139.224.17.45:8888/sms.aspx";


    public static String SendPost(String msg, String phone) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("account", account);
        map.put("password", pswd);
        map.put("mobile", phone);
        map.put("userid", userid);
        map.put("content", msg);
        map.put("action", "send");
        map.put("sendTime", "");
        map.put("extno", "");
        return sendHttp(url,map,"UTF-8");
    }


    /**
     * 随机生成六位验证码
     * @return
     */
    public static String getRandomCode(){
        StringBuffer code = new StringBuffer("");
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();

    }

    /**
     * 随机生成指定位数
     * @return
     */
    public static String getRandomCode(int num){
        StringBuffer code = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();

    }


    public static void main(String[] args) {
        try {
            //System.out.println(SendPost("【美程出行】亲爱的用户，您的短信验证码为"+getRandomCode()+"，3分钟内有效，若非本人操作请忽略","13540604032"));
            System.out.println(sendPhoneNumberVerificationCode("+250782607626","124232"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}