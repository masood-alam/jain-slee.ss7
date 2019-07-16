/*
 * Mobicents, Communications Middleware
 * 
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party
 * contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 *
 * Boston, MA  02110-1301  USA
 */
package org.mobicents.slee.resources.isup.ra;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.slee.EventTypeID;
import javax.slee.ServiceID;
import javax.slee.resource.FireableEventType;
import javax.slee.resource.ReceivableService;
import javax.slee.resource.ReceivableService.ReceivableEvent;

/**
 *
 * @author kulikov
 */
public class FireableEventTypeFilter {

    /**
     * Holds mappings eventTypeID --> Set(ServiceID) which are interested in receiving event
     */
    private final ConcurrentHashMap<EventTypeID, Set<ServiceID>> eventID2serviceIDs = 
            new ConcurrentHashMap<EventTypeID, Set<ServiceID>>(31);

    /**
     * Holds mappings eventTypeID --> Set(ServiceID) which are interested in initial receiving event
     */
    private final ConcurrentHashMap<EventTypeID, Set<ServiceID>> initialEventID2serviceIDs = 
            new ConcurrentHashMap<EventTypeID, Set<ServiceID>>(31);
    /**
     * checks if event should be filtered or not
     * @param eventType
     * @return true is event is to be filtered, false otherwise
     */
    public boolean filterEvent(FireableEventType eventType) {
        return !eventID2serviceIDs.containsKey(eventType.getEventType());
    }

    public boolean filterInitialEvent(FireableEventType eventType) {
        return !initialEventID2serviceIDs.containsKey(eventType.getEventType());
    }
    
    /**
     * Informs the filter that a receivable service is now active.
     * For the events related with the service, and if there are no other
     * services bound, then events of such event type should now not be filtered.
     * 
     * @param receivableService
     */
    public void serviceActive(ReceivableService receivableService) {
        for (ReceivableEvent receivableEvent : receivableService.getReceivableEvents()) {
        	Set<ServiceID> servicesReceivingEvent;
        	if(!receivableEvent.isInitialEvent())
        	{
        		servicesReceivingEvent = eventID2serviceIDs.get(receivableEvent.getEventType());
        		if (servicesReceivingEvent == null) {
        			servicesReceivingEvent = new HashSet<ServiceID>();
        			Set<ServiceID> anotherSet = eventID2serviceIDs.putIfAbsent(receivableEvent.getEventType(), servicesReceivingEvent);
        			if (anotherSet != null) {
        				servicesReceivingEvent = anotherSet;
        			}
        		}
        	}
        	else
        	{
        		servicesReceivingEvent = initialEventID2serviceIDs.get(receivableEvent.getEventType());
        		if (servicesReceivingEvent == null) {
        			servicesReceivingEvent = new HashSet<ServiceID>();
        			Set<ServiceID> anotherSet = initialEventID2serviceIDs.putIfAbsent(receivableEvent.getEventType(), servicesReceivingEvent);
        			if (anotherSet != null) {
        				servicesReceivingEvent = anotherSet;
        			}
        		}
        	}
        	
            synchronized (servicesReceivingEvent) {
                servicesReceivingEvent.add(receivableService.getService());
            }
        }
    }

    /**
     * Informs the filter that a receivable service is now inactive.
     * For the events related with the service, if there are no other
     * services bound, then events of such event type should be filtered 
     * 
     * @param receivableService
     */
    public void serviceInactive(ReceivableService receivableService) {
        for (ReceivableEvent receivableEvent : receivableService.getReceivableEvents()) {
            Set<ServiceID> servicesReceivingEvent = eventID2serviceIDs.get(receivableEvent.getEventType());
            if (servicesReceivingEvent != null) {
                synchronized (servicesReceivingEvent) {
                    servicesReceivingEvent.remove(receivableService.getService());
                }
            }
        }
    }

    /**
     * Informs the filter that a receivable service is now stopping.
     * @param receivableService
     */
    public void serviceStopping(ReceivableService receivableService) {
        // do nothing		
    }
}
