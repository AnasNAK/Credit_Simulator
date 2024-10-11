package org.nak.systembanker.services.implementations;

import org.nak.systembanker.dao.implementations.RequestStatusDao;
import org.nak.systembanker.entities.RequestStatus;
import org.nak.systembanker.services.contracts.RequestStatusContract;

public class RequestStatusService implements RequestStatusContract {
    private RequestStatusDao requestStatusDao;
    public RequestStatusService(){
        requestStatusDao = new RequestStatusDao();
    }

    @Override
    public RequestStatus save(RequestStatus requestStatus) {
        return requestStatusDao.save(requestStatus);
    }

}
