/*
 * TeleStax, Open Source Cloud Communications  
 * Copyright 2012, Telestax Inc and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.slee.resource.map.events;

import org.mobicents.protocols.ss7.map.api.MAPDialog;
import org.mobicents.protocols.ss7.map.api.dialog.MAPRefuseReason;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.tcap.asn.ApplicationContextName;

/**
 * @author amit bhayani
 * 
 */
public class DialogReject extends MAPEvent {

	private static final String EVENT_TYPE_NAME = "ss7.map.DIALOG_REJECT";

	private final MAPRefuseReason refuseReason;
	private final ApplicationContextName alternativeApplicationContext;
	private final MAPExtensionContainer extensionContainer;

	/**
	 * @param mapDialogWrapper
	 */
	public DialogReject(MAPDialog mapDialogWrapper, MAPRefuseReason refuseReason, ApplicationContextName alternativeApplicationContext,
			MAPExtensionContainer extensionContainer) {
		super(mapDialogWrapper, EVENT_TYPE_NAME, null);
		this.refuseReason = refuseReason;
		this.alternativeApplicationContext = alternativeApplicationContext;
		this.extensionContainer = extensionContainer;
	}

	public MAPRefuseReason getRefuseReason() {
		return refuseReason;
	}

	public ApplicationContextName getAlternativeApplicationContext() {
		return alternativeApplicationContext;
	}

	public MAPExtensionContainer getExtensionContainer() {
		return extensionContainer;
	}

	@Override
	public String toString() {
		return "DialogReject [refuseReason=" + refuseReason + ", alternativeApplicationContext=" + alternativeApplicationContext + ", extensionContainer="
				+ extensionContainer + ", " + this.mapDialogWrapper + "]";
	}

}
