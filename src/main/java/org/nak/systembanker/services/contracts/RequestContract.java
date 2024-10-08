package org.nak.systembanker.services.contracts;

import org.nak.systembanker.entities.Request;

import java.util.List;
import java.util.Optional;

public interface RequestContract {
    Request createRequest(Request request);
    List<Request> getAllRequests();
    Optional<Request> getRequestById(Long id);
    boolean deleteRequestById(Request request);
    Optional<Request> updateRequest(Request request);



}
