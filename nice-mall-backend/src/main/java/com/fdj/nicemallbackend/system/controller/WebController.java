package com.fdj.nicemallbackend.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname WebController
 * @Description TODO
 * @Date 19-12-6 下午5:30
 * @Created by xns
 */
@Controller
public class WebController {
    /**
     * templates文件夹，是放置模板文件的，因此需要视图解析器来解析它。所以必须通过服务器内部进行访问，也就是要走控制器--服务--视图解析器这个流程才行。
     * static文件夹，既不能直接访问，也不能通过服务器访问到。因此，这个文件夹，可能是放一些css、图片这样的文件供服务器内部引用。 
     * @param module
     * @param module2
     * @param name
     * @return
     */
    @RequestMapping(value="/{model}/{model2}/{name}")
    public String commonController(@PathVariable String module,@PathVariable String module2,@PathVariable String name){
        return module+"/"+module2+"/"+name;
    }

    @RequestMapping(value="/{module}/{name}")
    public String commonController2(@PathVariable String module,@PathVariable String name){
        return module+"/"+name;
    }

    @RequestMapping(value="/{name}")
    public String commonController(@PathVariable String name){
        return name;
    }
}
