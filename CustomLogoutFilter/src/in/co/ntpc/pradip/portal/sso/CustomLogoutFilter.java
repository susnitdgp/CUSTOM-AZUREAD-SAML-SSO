package in.co.ntpc.pradip.portal.sso;


import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedActionException;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.portal.auth.ExplicitLogoutFilter;
import com.ibm.portal.auth.FilterChainContext;
import com.ibm.portal.auth.LogoutFilterChain;
import com.ibm.portal.auth.exceptions.LogoutException;
import com.ibm.portal.security.SecurityFilterConfig;
import com.ibm.portal.security.exceptions.SecurityFilterInitException;
import com.ibm.websphere.security.WSSecurityException;
import com.ibm.websphere.security.WSSecurityHelper;
import com.ibm.websphere.security.auth.WSSubject;
import com.ibm.websphere.wssecurity.wssapi.WSSException;
import com.ibm.websphere.wssecurity.wssapi.WSSUtilFactory;
import com.ibm.websphere.wssecurity.wssapi.token.SAMLToken;
import com.ibm.wsspi.wssecurity.saml.data.SAMLAttribute;
import com.ibm.wsspi.wssecurity.saml.data.SAMLNameID;


public class CustomLogoutFilter implements ExplicitLogoutFilter {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, FilterChainContext filterChainContext,
			LogoutFilterChain arg3) throws LogoutException, LoginException {
		
		System.out.println("GOSWAMI: "+ "Custom Logout Filter Start");
		String redirectUrl="";
		
		//WSSecurityHelper.revokeSSOCookies(request, response);
		
		try {
			WSSUtilFactory wssf=WSSUtilFactory.getInstance();
			SAMLToken token=wssf.getSaml20Token();
			
			//SAML session value
			String session=token.getSamlID();
			
			//SAML name attribute value
			String name=token.getSAMLNameID().getValue();
			
			redirectUrl="https://pradiptest.ntpc.co.in/PradipSamlSSO/dologout.jsp?name="+name+"?session="+session;
		    filterChainContext.setRedirectURL(redirectUrl);
			
			
		} catch (WSSException e) {
			
			redirectUrl="https://pradiptest.ntpc.co.in/wps/portal";
		    
			
			//e.printStackTrace();
		}catch(Exception ex) {
			redirectUrl="https://pradiptest.ntpc.co.in/wps/portal";
		}
	
		
		/*
		try {

			Subject subject = WSSubject.getRunAsSubject();
			
			Set<Principal> sls=subject.getPrincipals();
			for(Principal p:sls) {
				System.out.println(p.getName());
			}
			
			
		    @SuppressWarnings("unchecked")
			SAMLToken samlToken = (SAMLToken) AccessController.doPrivileged(
		                new java.security.PrivilegedExceptionAction() {
		                    public Object run() throws java.lang.Exception
		                    {
		                        final java.util.Iterator<SAMLToken> authIterator = subject.getPrivateCredentials(SAMLToken.class).iterator();
		                        if ( authIterator.hasNext() ) {
		                            final SAMLToken token = (SAMLToken) authIterator.next();
		                            return token;
		                        }
		                        return null;
		                    }
		                });
		    
		    SAMLNameID nameId= samlToken.getSAMLNameID();
		    //samlToken.get
		    List<SAMLAttribute>  attributes = samlToken.getSAMLAttributes();
		    
		    String session=samlToken.getSamlID();
		    System.out.println("SessionIndex: " + samlToken.getSamlID());
		    String name=nameId.getValue();		    
		   
		    //details.setNameIdFormat("urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress");
		    
			    if (attributes != null && !attributes.isEmpty()) {
		            for (SAMLAttribute attr : attributes) {
		                //System.out.println("Attr Name:" + attr.getName());
		                
		                if(attr.getName().equals("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress")) {
		                	//name=attr.getStringAttributeValue()[0];
		                	System.out.println("NameId: " + attr.getStringAttributeValue()[0]);
		                }
		                
		                if (attr.getStringAttributeValue() != null) {
		                    for (int i = 0; i < attr.getStringAttributeValue().length; i++) {
		                    	System.out.println("Attr Value: " + attr.getStringAttributeValue()[i]);
		                    }
		
		                }
		            }
		        }
			    
			    String redirectUrl="https://pradiptest.ntpc.co.in/PradipSamlSSO/dologout.jsp?name="+name+"?session="+session;
			    filterChainContext.setRedirectURL(redirectUrl);
			    
			  
			}
			catch(WSSecurityException ex) {
				
				ex.printStackTrace();
			}
			catch(PrivilegedActionException ex) {
				
				ex.printStackTrace();
				
			}
		    **/
		
		
		System.out.println("GOSWAMI: FILTER END");
		arg3.logout(request, response, filterChainContext);
		
	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(SecurityFilterConfig arg0) throws SecurityFilterInitException {
		// TODO Auto-generated method stub
		
	}
	
	

}
