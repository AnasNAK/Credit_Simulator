package org.nak.systembanker.services.implementations;

import org.nak.systembanker.dao.implementations.RequestDao;
import org.nak.systembanker.dao.implementations.RequestStatusDao;
import org.nak.systembanker.dao.implementations.StatusDao;
import org.nak.systembanker.entities.Request;
import org.nak.systembanker.entities.RequestStatus;
import org.nak.systembanker.entities.Status;
import org.nak.systembanker.services.contracts.RequestContract;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RequestService implements RequestContract {
    private RequestDao requestDao;
    private StatusDao statusDao;
    private RequestStatusDao requestStatusDao;
    public RequestService() {
        requestDao = new RequestDao();
        statusDao = new StatusDao();
        requestStatusDao = new RequestStatusDao();

    }

    @Override
    public Request createRequest(Request request) {
        Optional<Status> status = statusDao.findByStatus("PENDING");
     if (request != null){
        if (status.isPresent()){
            RequestStatus requestStatus = new RequestStatus();
            requestStatus.setStatus(status.get());
            requestStatus.setRequest(request);
            requestStatus.setDateInsert(LocalDateTime.now());

            requestDao.save(request);
            requestStatusDao.save(requestStatus);
        }
     }
     return request;
    }

    @Override
    public List<Request> getAllRequests() {
        return List.of();
    }

    @Override
    public Optional<Request> getRequestById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteRequestById(Request request) {
        return false;
    }

    @Override
    public Optional<Request> updateRequest(Request request) {
        return Optional.empty();
    }
}
