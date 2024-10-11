package org.nak.systembanker.dao.contracts;

import org.nak.systembanker.entities.Status;

import java.util.List;
import java.util.Optional;

public interface StatusContract {
    List<Status> findAll();
    Optional<Status> findByStatus(String statusName);
}
