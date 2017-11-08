package com.nuc.zjy.qa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.zjy.qa.bean.Message;
import com.nuc.zjy.qa.dao.MessageDAO;

/**
 * @项目名称：QA
 * @类名称：MessageService @类描述：
 *
 * @author 赵建银
 * @date 2017年11月8日
 * @time 下午3:25:16
 * @version 1.0
 */
@Service
public class MessageService {

	@Autowired
	MessageDAO messageDAO;

	@Autowired
	Sensitive sensitive;

	public void addMessage(Message message) {
		message.setContent(sensitive.filter(message.getContent()));
		messageDAO.addMessage(message);
	}

	public List<Message> getConversionDetil(String conversionId) {
		return messageDAO.getConversionId(conversionId);
	}

	public List<Message> getConversionList(int userId) {
		return messageDAO.getConversionList(userId);
	}

	public int getConversionCount(int userId, String conversionId) {
		return messageDAO.getConversionCount(conversionId, userId);

	}

}
