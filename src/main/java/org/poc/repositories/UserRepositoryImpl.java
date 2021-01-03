package org.poc.repositories;

import org.poc.repositories.interfaces.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
}
