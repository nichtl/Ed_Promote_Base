package com.tuniu.mob.ocsfaq.faq.unit.service;

import com.tuniu.mob.ocsfaq.faq.entity.QuickReply;
import com.tuniu.mob.ocsfaq.faq.entity.QuickReplyCategory;
import com.tuniu.mob.ocsfaq.faq.pebbleService.entity.BindCategoryResponse;
import com.tuniu.mob.ocsfaq.faq.pebbleService.entity.PageResponse;

import java.util.List;

public interface  IQuickReplyService {
    int addQuickReplyCategory(QuickReplyCategory quickReplyCategory);

    BindCategoryResponse delQuickReplyCategoryById(Integer id);


    int addQuickReply(QuickReply quickReply);


    int delQuickReply(Integer id);

    int editQuickReply(QuickReply quickReply);

    int editQuickReplyCategory(QuickReplyCategory record);

    int selectQuickReplyCountByExample(QuickReply quickReply);

    PageResponse<QuickReplyCategory> selectQuickReplyCategoryList(QuickReplyCategory record);


    PageResponse<QuickReply>    selectQuickReplyList(QuickReply record);





}
