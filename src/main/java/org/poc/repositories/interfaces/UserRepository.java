package org.poc.repositories.interfaces;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.poc.models.User;

public interface UserRepository extends PanacheRepositoryBase<User, Long> {
}
