package org.mobicents.slee.resource.map.service.sms.wrappers;

import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.mobicents.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMResponse;

/**
 * 
 * @author amit bhayani
 *
 */
public class SendRoutingInfoForSMResponseWrapper extends SmsMessageWrapper<SendRoutingInfoForSMResponse> implements
		SendRoutingInfoForSMResponse {

	private static final String EVENT_TYPE_NAME = "ss7.map.service.sms.SEND_ROUTING_INFO_FOR_SM_RESPONSE";

	public SendRoutingInfoForSMResponseWrapper(MAPDialogSmsWrapper mAPDialog, SendRoutingInfoForSMResponse req) {
		super(mAPDialog, EVENT_TYPE_NAME, req);
	}

	public MAPExtensionContainer getExtensionContainer() {
		return this.wrappedEvent.getExtensionContainer();
	}

	public IMSI getIMSI() {
		return this.wrappedEvent.getIMSI();
	}

	public LocationInfoWithLMSI getLocationInfoWithLMSI() {
		return this.wrappedEvent.getLocationInfoWithLMSI();
	}

	public Boolean getMwdSet() {
		return this.wrappedEvent.getMwdSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SendRoutingInfoForSMResponseWrapper [wrapped=" + this.wrappedEvent + "]";
	}

}
