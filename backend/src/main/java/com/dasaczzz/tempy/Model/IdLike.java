package com.dasaczzz.tempy.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdLike implements Serializable {

  private String idPost;
  private String idUser;

}