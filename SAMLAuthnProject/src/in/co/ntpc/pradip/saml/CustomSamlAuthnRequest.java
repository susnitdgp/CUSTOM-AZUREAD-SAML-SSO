package in.co.ntpc.pradip.saml;


import com.ibm.websphere.security.NotImplementedException;
import com.ibm.websphere.wssecurity.wssapi.WSSException;
import com.ibm.websphere.wssecurity.wssapi.WSSUtilFactory;
import com.ibm.ws.wssecurity.token.UTC;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;



import javax.servlet.http.HttpServletRequest;
import com.ibm.wsspi.security.web.saml.AuthnRequestProvider;

public class CustomSamlAuthnRequest implements AuthnRequestProvider
{
    private static final String UNIQUE_ID_PREFIX = "pradip_";
    //private static final String ISSUER = "PradipPortalTest";
    private static final String ISSUER = "https://pradip.ntpc.co.in";
    private static final String SSO_URL = "https://login.microsoftonline.com/2c631f90-6a65-4bb3-a626-c0f6f5790a9a/saml2";
    
    
    public HashMap<String, String> getAuthnRequest(final HttpServletRequest req, final String erroMsg, final String acsUrl, final ArrayList<String> ssoUrls) throws NotImplementedException {
        final HashMap<String, String> map = new HashMap<String, String>();
        map.put("ssoUrl", SSO_URL);
        final String relayState = UUID.randomUUID().toString();
        map.put("relayState", relayState);
        final String requestId = generateUniqueID();
        map.put("requestId", requestId);
        final String issuer = ISSUER;
        final String destination = "https://login.microsoftonline.com/2c631f90-6a65-4bb3-a626-c0f6f5790a9a/saml2";
        
        final String authnMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" ID=\"" + requestId + "\" Version=\"2.0\" " + "IssueInstant=\"" + UTC.format(new Date()) + "\" ForceAuthn=\"false\" IsPassive=\"false\" " + "ProtocolBinding=\"urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST\" " + "AssertionConsumerServiceURL=\"" + acsUrl + "\" " + "Destination=\"" + destination + "\"> " + "<saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">" + issuer + "</saml:Issuer> <samlp:NameIDPolicy " + "Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified\" " + "AllowCreate=\"true\" /></samlp:AuthnRequest>";
        //final String base64Encoded = Base64.encode(authnMessage.getBytes());
        
        // get an instance of the WSSUtilFactory
        WSSUtilFactory wssuf;
		try {
			wssuf = WSSUtilFactory.getInstance();
			
			// base64 encode the authn request string
	        String encAuthnMsg = wssuf.encode(authnMessage.getBytes());
			
			//put the base64-encoded authn request in the map
            map.put(AuthnRequestProvider.AUTHN_REQUEST, encAuthnMsg);
			
		} catch (WSSException e) {
			
			e.printStackTrace();
		}
        
        return map;
    }
    
    public String getIdentityProviderOrErrorURL(final HttpServletRequest req, final String erroMsg, final String acsUrl, final ArrayList<String> ssoUrls) throws NotImplementedException {
        return null;
    }
    
    public static String generateUniqueID(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            prefix = UNIQUE_ID_PREFIX;
        }
        return String.valueOf(prefix) + UUID.randomUUID();
    }
    
    public static String generateUniqueID() {
        return generateUniqueID(null);
    }
}