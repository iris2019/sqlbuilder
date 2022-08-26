package com.youngdatafan.sqlbuilder.util;

import com.youngdatafan.sqlbuilder.enums.Cp;
import com.youngdatafan.sqlbuilder.enums.DataType;
import com.youngdatafan.sqlbuilder.enums.DatabaseType;
import com.youngdatafan.sqlbuilder.enums.FunctionType;
import com.youngdatafan.sqlbuilder.enums.JoinType;
import com.youngdatafan.sqlbuilder.enums.Op;
import com.youngdatafan.sqlbuilder.exception.NotSupportedException;
import com.youngdatafan.sqlbuilder.exception.SQLBuildException;

/**
 * 枚举工具类.
 *
 * @author yinkaifeng
 * @since 2021-09-08 4:42 下午
 */
public class EnumUtils {

    /**
     * 根据代码获取函数枚举类型.
     *
     * @param code 数据库代码
     * @return 函数枚举类型
     */
    public static FunctionType getFunctionTypeByCode(String code) {
        FunctionType type = FunctionType.getEnumByCode(code);
        if (type == null) {
            throw new NotSupportedException("表达式中包含非内置函数：" + code + "，可能会执行失败！");
        }
        return type;
    }

    /**
     * 根据数据库代码获取数据库类型.
     *
     * @param code 数据库代码
     * @return 数据库类型
     */
    public static DatabaseType getDatabaseTypeByCode(String code) {
        DatabaseType type = DatabaseType.getEnumByCode(code.toLowerCase().trim());
        if (type == null) {
            throw new SQLBuildException("暂不支持该数据库类型：" + code);
        }
        return type;
    }

    /**
     * 根据代码获取关联类型.
     *
     * @param code 关联代码
     * @return 关联类型
     */
    public static JoinType getJoinTypeByCode(String code) {
        JoinType type = JoinType.getEnumByCode(code);
        if (type == null) {
            throw new SQLBuildException("关联类型不正确：" + code);
        }
        return type;
    }

    /**
     * 根据代码获取数据类型.
     *
     * @param code 代码
     * @return 数据类型
     */
    public static DataType getDataTypeByCode(String code) {
        DataType type = DataType.getEnumByCode(code);
        if (type == null) {
            throw new SQLBuildException("数据类型不正确：" + code);
        }
        return type;
    }

    /**
     * 根据代码获取条件匹配方式.
     *
     * @param code 代码
     * @return 条件匹配方式
     */
    public static Op getOpByCode(String code) {
        if ("IN LIST".equalsIgnoreCase(code)) {
            return Op.IN;
        }
        if ("CONTAINS".equalsIgnoreCase(code)
            || "STARTS WITH".equalsIgnoreCase(code)
            || "ENDS WITH".equalsIgnoreCase(code)) {
            return Op.LIKE;
        }
        if ("NOT CONTAINS".equalsIgnoreCase(code)) {
            return Op.NOT_LIKE;
        }
        Op type = Op.getEnumByCode(code);
        if (type == null) {
            throw new SQLBuildException("条件匹配类型不正确：" + code);
        }
        return type;
    }

    /**
     * 根据代码获取条件组合方式.
     *
     * @param code 代码
     * @return 条件组合方式
     */
    public static Cp getCpByCode(String code) {
        if ("-".equals(code)) {
            return Cp.NONE;
        }
        Cp type = Cp.getEnumByCode(code);
        if (type == null) {
            throw new SQLBuildException("条件组合方式不正确：" + code);
        }
        return type;
    }
}
