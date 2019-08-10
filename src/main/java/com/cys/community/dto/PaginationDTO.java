package com.cys.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote  sam
 * @since  2019-08-06-5:05 PM
 * @author
 **/

/**
 * 页面控制逻辑
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>(); //
    private Integer totalPage;//

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        //=============展示逻辑=======================
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }//for

        showPrevious = !(page == 1); //如果不是第一页
        //如果当前页是最后一页，就没有下一页
        showNext = !(page == totalPage);

        //是否展示第一页？
        showFirstPage = !(pages.contains(1));
        //是否展示最后一页？
        showEndPage = !(pages.contains(totalPage));
    }
}
