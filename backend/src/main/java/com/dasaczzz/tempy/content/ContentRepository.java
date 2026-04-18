package com.dasaczzz.tempy.content;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentModel, UUID> { }
