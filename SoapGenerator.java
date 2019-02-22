import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.WinHttpClients;

public class SoapGenerator {
	private static final String SOAP_ELEMENT_PASSWORD="Password";
	private static final String SOAP_ELEMENT_USERNAME="Username";
	private static final String SOAP_ELEMENT_USERNAME_TOKEN="UsernameToken";
	private static final String SOAP_ELEMENT_SECURITY="Security";
	private static final String NAMESPACE_SECURITY="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String NAMESPACE_SECURITY="http://docs.oasis-open.org/wss/oasis-wss-wssecurity-secext-1.1.xsd";
	private static final String PREFIX_SECURITY="wsse";
	private static final int REQUEST_TIMEOUT=30000;
	private static final int CONNECTION_TIMEOUT=20000;
	private static final RequestConfig requestConfig=RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(REQUEST_TIMEOUT).build();
	private static final HttpClient httpClient = WinHttpClients.custom().build();

	public void static createtextcontent() {
		try{
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			
			SOAPPart soapPart = soapMessage.getSOAPPart();
			SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
			
			soapEnvelope.addNameSpaceDeclaration("obj", "http://servicecontext.ntpsoa.nordea.com/object");
			soapEnvelope.addNameSpaceDeclaration("v1", "http://soa.nordea.com/creditandcapital/creditdecisionandagreementconnection/v1");

			//Two ways to extract header
			SOAPHeader soapHeader = soapEnvelope.getHeader();
			//soapHeader = soapMessage.getSOAPHeader();

			//Two ways to extract body
			SOAPBody soapBody = soapEnvelope.getBody();
			//soapBody = soapMessage.getSOAPBody();

			SOAPElement soapElementSecurityHeader = soapHeader.addChildElement(SOAP_ELEMENT_SECURITY, PREFIX_SECURITY, NAMESPACE_SECURITY);
			SOAPElement soapElementUserNameToken = soapElementSecurityHeader.addChildElement(SOAP_ELEMENT_USERNAME_TOKEN, PREFIX_SECURITY);
			SOAPElement soapElementUserName = soapElementUserNameToken.addChildElement(SOAP_ELEMENT_USERNAME, PREFIX_SECURITY);			
			soapElementUserName.addTextNode("SVC4543");

			SOAPElement soapElementPassword = soapElementUserNameToken.addChildElement(SOAP_ELEMENT_PASSWORD, PREFIX_SECURITY);
			soapElementPassword.addTextNode("enevVSSFkj");

			SOAPElement serviceContext = soapHeader.addChildElement("servicecontext","obj");
			serviceContext.setAttribute("schemaVersion","1");
			serviceContext.addChildElement("userId","obj").addTextNode("G76769");
			serviceContext.addChildElement("technicalUserId","obj").addTextNode("CD01");
			serviceContext.addChildElement("sessionId","obj").addTextNode("DKNPHV09877-76hhf");
			serviceContext.addChildElement("reuestId","obj").addTextNode("118766-877363");
			serviceContext.addChildElement("applicationId","obj").addTextNode("5305");
			serviceContext.addChildElement("channelId","obj").addTextNode("NETBANK");
			serviceContext.addChildElement("clientType","obj").addTextNode("WEB");
			serviceContext.addChildElement("clientComponent","obj").addTextNode("SoapUI");
			serviceContext.addChildElement("clientComponentVersion","obj").addTextNode("1.0");
			serviceContext.addChildElement("clientAction","obj").addTextNode("CallCreateConnection");
			serviceContext.addChildElement("clientView","obj").addTextNode("LatuOperation");
			serviceContext.addChildElement("processInstance","obj");
			serviceContext.addChildElement("authMethod","obj").addTextNode("NINAA");
			serviceContext.addChildElement("officeMode","xsi","http://www.w3.org/2001/XMLSchema-Instance").setAttribute("xsi:nil","true");
			serviceContext.addChildElement("userLocation","obj").addTextNode("10.87.88.09");

		} catch(Exception e) {
			
		}
	}

}