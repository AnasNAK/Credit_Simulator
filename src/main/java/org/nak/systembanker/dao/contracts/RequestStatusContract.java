package org.nak.systembanker.dao.contracts;

import org.nak.systembanker.entities.RequestStatus;

public interface RequestStatusContract {
    RequestStatus save(RequestStatus requestStatus);
}
