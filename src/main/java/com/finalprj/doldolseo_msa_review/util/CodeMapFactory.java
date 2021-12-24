package com.finalprj.doldolseo_msa_review.util;

import java.util.HashMap;
import java.util.Map;

/*
 * 코드 매핑 유틸
 * 지역및 컨텐츠타입 코드-이름 매핑 유틸
 *
 * @Author 김경일
 * @Date 2021/08/06
 */
public class CodeMapFactory {

    public Map<Integer, String> getAreaMap() {
        Map<Integer, String> areaMap = new HashMap<>();

        areaMap.put(1, "강남");
        areaMap.put(2, "강북");
        areaMap.put(3, "광화문");
        areaMap.put(4, "명동");
        areaMap.put(5, "여의도");
        areaMap.put(6, "잠실");
        areaMap.put(7, "홍대");
        areaMap.put(0, "etc");
        areaMap.put(null, "전체");

        return areaMap;
    }

    public Map<Integer, String> getContentTypeMap() {
        Map<Integer, String> contentTypeMap = new HashMap<>();

        contentTypeMap.put(1, "축제&행사");
        contentTypeMap.put(2, "음식");
        contentTypeMap.put(3, "쇼핑");
        contentTypeMap.put(4, "문화&관광");
        contentTypeMap.put(0, "etc");
        contentTypeMap.put(null, "전체");

        return contentTypeMap;
    }

}
