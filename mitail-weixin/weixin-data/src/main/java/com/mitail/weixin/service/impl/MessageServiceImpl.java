package com.mitail.weixin.service.impl;

import com.mitail.weixin.entity.Message;
import com.mitail.weixin.mapper.MessageMapper;
import com.mitail.weixin.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2021-12-10
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
