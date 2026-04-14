package com.dasaczzz.tempy.content;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentRepository extends JpaRepository<ContentModel, UUID> { }
