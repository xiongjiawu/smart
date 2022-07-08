package com.xiongjiawu.smartaccountbook.api.feign.base;


import com.xiongjiawu.smartaccountbook.common.shiro.UserShiroPrincipal;
import net.sourceforge.pinyin4j.PinyinHelper;

public abstract class FeignBaseController {

	/**
	 * 获取 shiro 登录用户信息
	 * @return
	 */
	public UserShiroPrincipal getShiroUser() {
		return UserShiroPrincipal.getInstance();
	}

	/**
	 * 得到中文首字母
	 *
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}
}
