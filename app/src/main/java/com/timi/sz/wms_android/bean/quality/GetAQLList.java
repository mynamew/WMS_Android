package com.timi.sz.wms_android.bean.quality;

import java.util.List;

/**
 * $dsc  获取AQL列表的数据返回
 * author: timi
 * create at: 2017-10-21 11:37
 */

public class GetAQLList {

    private List<String> levelCodeLists;
    private List<String> aqlLists;

    public List<String> getLevelCodeLists() {
        return levelCodeLists;
    }

    public void setLevelCodeLists(List<String> levelCodeLists) {
        this.levelCodeLists = levelCodeLists;
    }

    public List<String> getAqlLists() {
        return aqlLists;
    }

    public void setAqlLists(List<String> aqlLists) {
        this.aqlLists = aqlLists;
    }
}
