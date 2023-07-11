package com.wy.cybertodoadmin.system.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/7 14:45:41
 */
@Data
public class CasheObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String key;

    private String value;

    private long timeOut;

}
