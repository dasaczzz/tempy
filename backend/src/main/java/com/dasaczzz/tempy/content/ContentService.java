package com.dasaczzz.tempy.content;

import com.dasaczzz.tempy.content.dtos.CreateContentDTO;
import com.dasaczzz.tempy.content.dtos.ResponseContentDTO;
import com.dasaczzz.tempy.common.BaseService;

import java.util.UUID;

public interface ContentService extends BaseService<UUID, CreateContentDTO, ResponseContentDTO> { }
