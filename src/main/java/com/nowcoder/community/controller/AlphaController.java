package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.selectData();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        // 获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " " + value);
        }
        System.out.println(request.getParameter("code"));

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter();) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // GET请求
    // /students?current=1&limit=20
    // DispatcherServlet 会自动将请求中的参数赋值给方法中的同名参数
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // 第二种传参方式
    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    // POST请求
    // 参数名与html表单中参数名一致，可以直接传过来
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "cf");
        modelAndView.addObject("age", 25);
        modelAndView.setViewName("/demo/view");

        return modelAndView;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "Szu");
        model.addAttribute("age", 40);
        return "/demo/view";
    }

    // 响应JSON数据(异步请求中)
    // Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "cf");
        emp.put("age", 25);
        emp.put("salary", 12000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "cf");
        emp.put("age", 25);
        emp.put("salary", 12000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "cf");
        emp.put("age", 26);
        emp.put("salary", 14000.00);
        list.add(emp);

        return list;
    }

    // homework
    @RequestMapping(path = "/class", method = RequestMethod.GET)
    @ResponseBody
    public String getClass(@RequestParam(name = "index") int index){
        String s = String.valueOf(index) + "班";

        return s;
    }

    @RequestMapping(path = "/add_class", method = RequestMethod.POST)
    @ResponseBody
    public String addClass(int classIndex){
        String s = String.valueOf(classIndex) + "班添加成功！";
        return s;
    }

    @RequestMapping(path = "/city", method = RequestMethod.GET)
    public String getCity(Model model){
        model.addAttribute("name", "武汉");
        model.addAttribute("salary", 12000);

        return "/demo/homework";
    }

    @RequestMapping(path = "/json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> returnJson(){
        Map<String, Object> map = new HashMap<>();
        map.put("json", "json数据");

        return map;
    }


}
