package org.nak.systembanker.services.implementations;

import org.nak.systembanker.dao.implementations.RequestDao;
import org.nak.systembanker.entities.Request;
import org.nak.systembanker.services.contracts.RequestContract;

import java.util.List;
import java.util.Optional;

public class RequestService implements RequestContract {
    private RequestDao dao;
    public RequestService() {
        dao = new RequestDao();
    }

    @Override
    public Request createRequest(Request request) {
     if (request != null){
         dao.save(request);
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
