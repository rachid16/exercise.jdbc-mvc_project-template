package com.github.perscholas.dto;

import com.github.perscholas.model.EntityInterface;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by leon on 8/14/2020.
 */
public interface DtoInterface<EntityType extends EntityInterface> {
    ResultSet getResultSet();
    List<EntityType> toList();
}
