package demo.order; 

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.message.Message;

@WebService(serviceName="OrderProcessService", portName="OrderProcessPort")
public class OrderProcessImpl implements OrderProcess {

	@Resource
	WebServiceContext wsc;

	public String processOrder(Order order) {
		System.out.println("Processing order...");
		String orderID = validate(order);
        return orderID;
    }

	/**
	 * Validates the order and returns the order ID
	**/
	private String validate(Order order) {
		System.out.println("Getting the operation info from the message context ");
		MessageContext ctx = wsc.getMessageContext();
		QName operation = (QName) ctx.get(Message.WSDL_OPERATION);
		System.out.println("The operation name is " + operation);
		QName WSDL_PORT = (QName) ctx.get(Message.WSDL_PORT);
		System.out.println("The WSDL_PORT name is " + WSDL_PORT);
		QName WSDL_SERVICE = (QName) ctx.get(Message.WSDL_SERVICE);
		System.out.println("The WSDL_SERVICE name is " + WSDL_SERVICE);
		QName WSDL_INTERFACE = (QName) ctx.get(Message.WSDL_INTERFACE);
		System.out.println("The WSDL_INTERFACE name is " + WSDL_INTERFACE);
		QName QUERY_STRING = (QName) ctx.get(Message.QUERY_STRING);
		System.out.println("The QUERY_STRING name is " + QUERY_STRING);
		QName MTOM_ENABLED = (QName) ctx.get(Message.MTOM_ENABLED);
		System.out.println("The MTOM_ENABLED name is " + MTOM_ENABLED);
//		QName HTTP_REQUEST_METHOD = (QName) ctx.get(Message.HTTP_REQUEST_METHOD);
//		System.out.println("The HTTP_REQUEST_METHOD name is " + HTTP_REQUEST_METHOD);

		String custID = order.getCustomerID();
		String itemID = order.getItemID();
		int qty = order.getQty();
		double price = order.getPrice();

		if (custID != null && itemID != null && !custID.equals("") && 
				!itemID.equals("") && qty > 0 && price > 0.0) {
			return "ORD1234";
		}
		return null;
	}

}

