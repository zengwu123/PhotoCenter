package com.photo.center.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: zeng wu
 * @create: 2020-06-16 19:58
 * @description: 封装jpa 分页类
 */
public class JpaPageHelper {

    public static PageInfo SetStartPage(List<?> list, int pageNow, int Size) {
        boolean isFristPage = false;
        boolean isLastPage = false;
        boolean haveNexPage = false;
        boolean havePerPage = false;
        int pageSize = 0;
        int totalRow = list.size();
        int fromIndex = (pageNow - 1) * Size;
        int toIndex = pageNow * Size;
        if (fromIndex == 0) {
            isFristPage = true;
        } else if (!isFristPage) {
            havePerPage = true;
        }
        if (toIndex >= totalRow) {
            toIndex = totalRow;
            isLastPage = true;
        } else if (!isLastPage) {
            haveNexPage = true;
        }
        if (totalRow % Size == 0) {
            pageSize = totalRow / Size;
        } else {
            pageSize = totalRow / Size + 1;
        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageNow);
        pageInfo.setPages(pageSize);
        pageInfo.setTotal(totalRow);
        pageInfo.setList(list.subList(fromIndex, toIndex));

        return pageInfo;
    }
}
