/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
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

package org.mobicents.slee.resource.tcap.wrappers;

import org.mobicents.protocols.ss7.sccp.parameter.SccpAddress;
import org.mobicents.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortIndication;
import org.mobicents.protocols.ss7.tcap.asn.AbortSource;
import org.mobicents.protocols.ss7.tcap.asn.ApplicationContextName;
import org.mobicents.protocols.ss7.tcap.asn.ResultSourceDiagnostic;
import org.mobicents.protocols.ss7.tcap.asn.UserInformation;
import org.mobicents.slee.resource.tcap.events.UserAbortEvent;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class UserAbortEventImpl extends DialogEventImpl<TCUserAbortIndication> implements UserAbortEvent {

	public UserAbortEventImpl(TCAPDialogWrapper wrappedDialog, TCUserAbortIndication wrappedComponent) {
		super(wrappedDialog, wrappedComponent);
	}

	@Override
	public Boolean IsAareApdu() {
		return this.wrappedComponent.IsAareApdu();
	}

	@Override
	public Boolean IsAbrtApdu() {
		return this.wrappedComponent.IsAbrtApdu();
	}

	@Override
	public UserInformation getUserInformation() {
		return this.wrappedComponent.getUserInformation();
	}

	@Override
	public AbortSource getAbortSource() {
		return this.wrappedComponent.getAbortSource();
	}

	@Override
	public ApplicationContextName getApplicationContextName() {
		return this.wrappedComponent.getApplicationContextName();
	}

	@Override
	public ResultSourceDiagnostic getResultSourceDiagnostic() {
		return this.wrappedComponent.getResultSourceDiagnostic();
	}

    @Override
    public SccpAddress getOriginatingAddress() {
        return this.wrappedComponent.getOriginatingAddress();
    }

}
