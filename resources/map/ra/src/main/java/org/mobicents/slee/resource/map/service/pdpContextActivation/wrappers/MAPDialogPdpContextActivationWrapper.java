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

package org.mobicents.slee.resource.map.service.pdpContextActivation.wrappers;

import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.primitives.GSNAddress;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;
import org.mobicents.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.service.pdpContextActivation.MAPDialogPdpContextActivation;
import org.mobicents.slee.resource.map.MAPDialogActivityHandle;
import org.mobicents.slee.resource.map.MAPResourceAdaptor;
import org.mobicents.slee.resource.map.wrappers.MAPDialogWrapper;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class MAPDialogPdpContextActivationWrapper extends MAPDialogWrapper<MAPDialogPdpContextActivation> implements MAPDialogPdpContextActivation {

	public MAPDialogPdpContextActivationWrapper(MAPDialogPdpContextActivation wrappedDialog, MAPDialogActivityHandle activityHandle,
			MAPResourceAdaptor ra) {
		super(wrappedDialog, activityHandle, ra);
	}

	@Override
	public MAPDialogPdpContextActivation getWrappedDialog() {
		return this.wrappedDialog;
	}

    @Override
    public Long addSendRoutingInfoForGprsRequest(IMSI imsi, GSNAddress ggsnAddress, ISDNAddressString ggsnNumber, MAPExtensionContainer extensionContainer)
            throws MAPException {
        return this.wrappedDialog.addSendRoutingInfoForGprsRequest(imsi, ggsnAddress, ggsnNumber, extensionContainer);
    }

    @Override
    public Long addSendRoutingInfoForGprsRequest(int customInvokeTimeout, IMSI imsi, GSNAddress ggsnAddress, ISDNAddressString ggsnNumber,
            MAPExtensionContainer extensionContainer) throws MAPException {
        return this.wrappedDialog.addSendRoutingInfoForGprsRequest(customInvokeTimeout, imsi, ggsnAddress, ggsnNumber, extensionContainer);
    }

    @Override
    public void addSendRoutingInfoForGprsResponse(long invokeId, GSNAddress sgsnAddress, GSNAddress ggsnAddress, Integer mobileNotReachableReason,
            MAPExtensionContainer extensionContainer) throws MAPException {
        this.wrappedDialog.addSendRoutingInfoForGprsResponse(invokeId, sgsnAddress, ggsnAddress, mobileNotReachableReason, extensionContainer);
    }

}
