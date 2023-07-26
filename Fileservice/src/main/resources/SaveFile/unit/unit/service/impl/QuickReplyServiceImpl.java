package com.tuniu.mob.ocsfaq.faq.unit.service.impl;

import com.tuniu.mob.ocsfaq.faq.dao.QuickReplyCategoryMapper;
import com.tuniu.mob.ocsfaq.faq.dao.QuickReplyMapper;
import com.tuniu.mob.ocsfaq.faq.entity.QuickReply;
import com.tuniu.mob.ocsfaq.faq.entity.QuickReplyCategory;
import com.tuniu.mob.ocsfaq.faq.pebbleService.entity.BindCategoryResponse;
import com.tuniu.mob.ocsfaq.faq.pebbleService.entity.PageResponse;
import com.tuniu.mob.ocsfaq.faq.unit.service.IQuickReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuickReplyServiceImpl implements IQuickReplyService {


    @Resource
    QuickReplyCategoryMapper quickReplyCategoryMapper;

    @Resource
    QuickReplyMapper quickReplyMapper;


    @Override
    public int addQuickReplyCategory(QuickReplyCategory quickReplyCategory) {


        return quickReplyCategoryMapper.insertSelective(quickReplyCategory);
    }

    @Override
    public BindCategoryResponse delQuickReplyCategoryById(Integer id) {

        BindCategoryResponse  defaultResponse = BindCategoryResponse.getBindCategoryResponseByResult(0);
        QuickReply record = new QuickReply();
        record.setReplyCategoryId(id);
        int count = quickReplyMapper.selectCountByExample(record);
        if(count>0){
            defaultResponse.setMsg("该分类下存在未删除的快捷回复,无法删除");
        }
        int r = quickReplyCategoryMapper.deleteByPrimaryKey(id);
        return BindCategoryResponse.getBindCategoryResponseByResult(r);
    }

    @Override
    public int addQuickReply(QuickReply quickReply) {

        return quickReplyMapper.insertSelective(quickReply);
    }

    @Override
    public int delQuickReply(Integer id) {

        return quickReplyMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int editQuickReply(QuickReply quickReply) {
        return quickReplyMapper.updateByPrimaryKeySelective(quickReply);
    }

    @Override
    public int editQuickReplyCategory(QuickReplyCategory record) {
        return quickReplyCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int selectQuickReplyCountByExample(QuickReply quickReply) {
        return quickReplyMapper.selectCountByExample(quickReply);
    }

    @Override
    public PageResponse<QuickReplyCategory> selectQuickReplyCategoryList(QuickReplyCategory record) {
        PageResponse<QuickReplyCategory> response  = PageResponse.getDefaultResponse();

        record.InitPageWithDefault(1,10);
          int totalCount = quickReplyCategoryMapper.selectCountByExample(record);

          List<QuickReplyCategory> data = new ArrayList<>();
          if (totalCount > 0) {
           data = quickReplyCategoryMapper.selectByExample(record);
          }
        return PageResponse.createSuccessResponse(totalCount,data);
    }

    @Override
    public PageResponse<QuickReply> selectQuickReplyList(QuickReply record) {
        PageResponse<QuickReply> response  = PageResponse.getDefaultResponse();

        record.InitPageWithDefault(1,10);

        int totalCount = quickReplyMapper.selectCountByExample(record);
        List<QuickReply> data = new ArrayList<>();

        if(totalCount>0){
           data = quickReplyMapper.selectByExample(record);
        }

        return PageResponse.createSuccessResponse(totalCount,data);
    }
}
