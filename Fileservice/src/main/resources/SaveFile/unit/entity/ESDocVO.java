package com.tuniu.mob.ocsfaq.faq.entity;

import java.util.Map;

import com.tuniu.mob.ocsfaq.common.util.TnStringUtils;

/**
 * Created by libing2 on 2016/10/17.
 */
public class ESDocVO {
    private int schemaId;
    private String docId;
    private String content;
    private int esOperation;

    public int getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(int schemaId) {
        this.schemaId = schemaId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEsOperation() {
        return esOperation;
    }

    public void setEsOperation(int esOperation) {
        this.esOperation = esOperation;
    }

    @Override
    public String toString() {
        return "ESDocVO{" +
                "schemaId=" + schemaId +
                ", docId='" + docId + '\'' +
                ", content='" + content + '\'' +
                ", esOperation=" + esOperation +
                '}';
    }

    public enum ESOperationEnum {
        UNKNOWN(0, "未知操作"),
        ADD_DOC(1, "添加文档"),
        UPDATE_DOC(2, "更新文档"),
        DELETE_DOC(3, "删除文档");

        public final int value;
        public final String description;
        private static Map<Integer, ESOperationEnum> map = TnStringUtils.num2EnumMap(ESOperationEnum.values(), (ESOperationEnum e) -> e.value);

        ESOperationEnum(final int value, final String description) {
            this.value = value;
            this.description = description;
        }

        public static ESOperationEnum int2Enum(final int value) {
            return map.containsKey(value) ? map.get(value) : UNKNOWN;
        }
    }
}
