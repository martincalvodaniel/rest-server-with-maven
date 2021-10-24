package com.dmartinc.poc.utils;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.model.PocModelContent;

public class PocTestUtils {

    public static final long ID = Long.MIN_VALUE;
    public static final String CONTENT = "CONTENT";
    public static final String CONTENT_2 = CONTENT + "_2";

    public static final PocModel POC_MODEL = aPocModel(String.valueOf(ID), CONTENT);
    public static final PocModel POC_MODEL_2 = aPocModel(String.valueOf(ID), CONTENT_2);
    public static final PocModelContent POC_MODEL_CONTENT = aPocModelContent(CONTENT);
    public static final PocModelContent POC_MODEL_CONTENT_2 = aPocModelContent(CONTENT_2);

    public static PocModelContent aPocModelContent(String content) {
        PocModelContent ret = new PocModelContent();
        ret.setContent(content);
        return ret;
    }

    public static PocModel aPocModel(String id, String content) {
        PocModel ret = new PocModel();
        ret.setId(id);
        ret.setPocModelContent(aPocModelContent(content));
        return ret;
    }
}
