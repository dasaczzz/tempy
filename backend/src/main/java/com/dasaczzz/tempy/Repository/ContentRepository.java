package com.dasaczzz.tempy.Repository;

import com.dasaczzz.tempy.Model.ContentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentRepository extends JpaRepository<ContentModel, UUID> { }
