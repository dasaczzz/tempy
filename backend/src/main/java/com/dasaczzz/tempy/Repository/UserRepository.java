package com.dasaczzz.tempy.Repository;

import com.dasaczzz.tempy.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> { }
