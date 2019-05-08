
package com.huihuang.conterller;

import com.huihuang.annotation.CreateToken;
import com.huihuang.annotation.JavascrIpt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	// 转发到index页面
	@RequestMapping("/index")
    @CreateToken
	public String index() {
		return "index";
	}

	// 接受頁面 參數
	@RequestMapping("/postIndex")
    @JavascrIpt
	public String postIndex(HttpServletRequest request) {
		request.setAttribute("name", request.getParameter("name"));
		return "forward";
	}

}
