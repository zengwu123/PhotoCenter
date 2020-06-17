package com.photo.center.controller;

import com.photo.center.service.UserService;
import com.photo.center.util.PageUtil;
import com.photo.center.vo.MenuVO;
import com.photo.center.vo.SysUserVO;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zengwu
 * @Date: 2020/05/16 22:01
 * @Description: Hello 测试控制类
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String getLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getAdmin() {
        return "main";
    }

    @RequestMapping(value = "/queryMenuList", method = RequestMethod.POST)
    @ResponseBody
    public List<MenuVO> getLoginOut(Authentication authentication) throws Exception {
        List<MenuVO> menuVOS = userService.queryMenuList(authentication.getName());
        return menuVOS;
    }


    @RequestMapping(value = "/learn", method = RequestMethod.GET)
    public String getLearn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "learn-resource";
    }


    @RequestMapping(value = "/learn1", method = RequestMethod.GET)
    public String getLearn1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "learn-resource2";
    }

    private Resource exportResource;

    @PostConstruct
    public void setup() {
        exportResource = new ClassPathResource("/template/test.xlsx");
    }

    @RequestMapping(value = "/export")
    public void exportSalaryTemplet(HttpServletResponse response) throws Exception {
        List<SysUserVO> list = userService.queryUserList(new PageUtil());
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        // 生成Excel文件
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS("C:\\Users\\Administrator\\Desktop\\test.xlsx", map, "C:\\Users\\Administrator\\Desktop\\test1.xlsx");

        //将数据渲染到excel模板上
        Workbook workbook = new XLSTransformer().transformXLS(exportResource.getInputStream(), map);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        workbook.write(output);
        String filename = "员工薪资单.xls";
        response.setContentType(String.format("%s;charset=utf-8", "application/x"));
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String(filename.getBytes("utf-8"), "iso8859-1"));
        response.setHeader("Content-Length", String.valueOf(output.toByteArray().length));
        response.getOutputStream().write(output.toByteArray());
    }
}