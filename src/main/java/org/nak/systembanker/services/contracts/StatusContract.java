package org.nak.systembanker.services.contracts;

import org.nak.systembanker.entities.Status;

import java.util.Optional;

public interface StatusContract {
    Optional<Status> findByStatus(String statusName);
}
