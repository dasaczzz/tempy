package com.dasaczzz.tempy.content;

import java.util.UUID;
import com.dasaczzz.tempy.common.BaseService;
import com.dasaczzz.tempy.content.dtos.CreateContentDTO;
import com.dasaczzz.tempy.content.dtos.ResponseContentDTO;

public interface ContentService extends BaseService<UUID, CreateContentDTO, ResponseContentDTO> { }
