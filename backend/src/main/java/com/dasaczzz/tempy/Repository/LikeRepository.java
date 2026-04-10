package com.dasaczzz.tempy.Repository;

import com.dasaczzz.tempy.Model.IdLike;
import com.dasaczzz.tempy.Model.LikeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeModel, IdLike> { }
