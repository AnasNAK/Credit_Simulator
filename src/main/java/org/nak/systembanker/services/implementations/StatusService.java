package org.nak.systembanker.services.implementations;

import org.nak.systembanker.dao.implementations.StatusDao;
import org.nak.systembanker.entities.Status;
import org.nak.systembanker.services.contracts.StatusContract;

import java.util.Optional;

public class StatusService implements StatusContract {
    private StatusDao statusDao;
    public StatusService() {
        statusDao = new StatusDao();
    }

    @Override
    public Optional<Status> findByStatus(String statusName) {
        return statusDao.findByStatus(statusName);
    }

}
