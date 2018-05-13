package mksgroup.goodway.requesting.domain.service;

import mksgroup.goodway.requesting.domain.repository.ReadOnlyRepository;

/**
 *
 * @author ThachLN
 * @param <TE>
 * @param <T>
 */
public abstract class ReadOnlyBaseService<TE, T> {

    private ReadOnlyRepository<TE, T> repository;

    ReadOnlyBaseService(ReadOnlyRepository<TE, T> repository) {
        this.repository = repository;
    }
}
