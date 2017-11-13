package com.nuc.zjy.qa.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @项目名称：util
 * @类名称：Sensitive @类描述： 敏感词过滤，使用字典树（前缀树）
 *
 * @author 赵建银
 * @date 2017年11月1日
 * @time 下午3:35:41
 * @version 1.0
 */
@Service
public class Sensitive {

	public Sensitive() {
		// 读取敏感词库
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("minganci.txt");
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				addWord(lineTxt.trim());
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加关键词
	 * 
	 * @param lineTxt
	 */
	private void addWord(String lineTxt) {
		TreeNode tempNode = rootNode;

		for (int i = 0; i < lineTxt.length(); i++) {
			Character c = lineTxt.charAt(i);
			if (isSybol(c)) {
				continue;
			}
			// 获取当前结点
			TreeNode node = tempNode.getSubNode(c);
			// 判断当前结点是否存在，不存在创建新的节点将新的节点添加到当前结点，如果存在继续判断
			if (node == null) {
				node = new TreeNode();
				tempNode.addSubNode(c, node);
			}
			tempNode = node;
			// 如果是尾节点进行标记
			if (i == lineTxt.length() - 1) {
				tempNode.setKeyWordNode(true);
			}
		}
	}

	private class TreeNode {
		// 尾节点
		private boolean end = false;
		// 节点树,当前节点下所有的子节点
		private Map<Character, TreeNode> subNodes = new HashMap<Character, TreeNode>();

		/**
		 * 
		 * 添加节点
		 * 
		 * @param key
		 * @param node
		 */
		public void addSubNode(Character key, TreeNode node) {
			subNodes.put(key, node);
		}

		/**
		 * 获取节点
		 * 
		 * @param key
		 * @return
		 */
		public TreeNode getSubNode(Character key) {
			return subNodes.get(key);
		}

		/**
		 * 是否为尾节点
		 * 
		 * @return
		 */
		boolean isKeyWordEndNode() {
			return end;
		}

		/**
		 * 设置为尾节点
		 * 
		 * @param end
		 */
		void setKeyWordNode(boolean end) {
			this.end = end;
		}
	}

	// 根节点
	private TreeNode rootNode = new TreeNode();

	private boolean isSybol(char c) {
		int ic = (int) c;
		// 还可以加上不是英文！charUtils.isAsciiAlphanumberic(c);
		return ic < 0x2E80 || ic > 0x9FFF;
	}

	// 过滤
	public String filter(String txt) {
		if (txt.equals("")) {
			return txt;
		}

		// 接收字符串
		StringBuffer result = new StringBuffer();
		// 替换词
		String replacement = "***";
		// 初始化1标记
		TreeNode tempNode = rootNode;
		// 初始化 2和3标记
		int begin = 0;// 红
		int position = 0;// 黑
		// 循环查找
		while (position < txt.length()) {
			char c = txt.charAt(position);
			if (isSybol(c)) {// 过滤非法字符
				// 跳过
				if (tempNode == rootNode) {
					result.append(c);
					++begin;
				}
				++position;
				continue;
			}
			tempNode = tempNode.getSubNode(c);// 获取当前结点的子节点
			// 如果为null 说明不存在将位置后移
			if (tempNode == null) {
				result.append(txt.charAt(begin));
				position = begin + 1;
				begin = position;
				tempNode = rootNode;
				// 如果发现敏感词替换
			} else if (tempNode.isKeyWordEndNode()) {
				// 发现敏感词
				result.append(replacement);
				position = position + 1;
				begin = position;
				tempNode = rootNode;

			} else {
				// 是敏感词中的一部分，继续执行
				++position;
			}
		}
		// 最后一次的字符
		result.append(txt.substring(begin));
		return result.toString();

	}
}
