package org.nak.systembanker.dao.contracts;

import org.nak.systembanker.entities.Request;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RequestContract {

    Request save(Request request);
    Optional<Request> findById(Long id);
    List<Request> findAll();
    boolean delete(Request request);
    Request update(Request request);
    List<Request> findRequestByDate(LocalDate date);
}
