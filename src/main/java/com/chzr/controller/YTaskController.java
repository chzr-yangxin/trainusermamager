package com.chzr.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chzr.common.ResponseData;
import com.chzr.common.ResponseUtil;
import com.chzr.entity.*;
import com.chzr.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class YTaskController {
    @Autowired
    private YTaskService taskService;
    @Autowired
    private YRoleService roleService;
    @Autowired
    private YTaskResultService taskResultService;
    @Autowired
    private YSessionService sessionService;
    @Autowired
    private YUserService userService;

    @Autowired
    private YTaskFinishService taskFinishService;

    @RequestMapping("/task")
    private ResponseData GetListTasks(){
        List<YTaskEntity> tasks = taskService.list();
        return ResponseUtil.OK(tasks);
    }

    @RequestMapping(value = "/sendtask/{taskid}", method = RequestMethod.POST)
    private ResponseData SendTask(@PathVariable String taskid){
        YTaskEntity task = taskService.getById(taskid);

        ResponseData res = null;
        if(task != null){
            List<YSessionEntity> sesses = sessionService.list();
            if(sesses.size() != 5){
                res = ResponseUtil.FAIL("登录角色不完整！");
            }else{
                String checkid = UUID.randomUUID().toString();
                Date ctime = new Date();
                for(YSessionEntity ses: sesses){
                    YTaskResultEntity tr = new YTaskResultEntity();
                    tr.setCheckid(checkid);
                    tr.setCheckname(task.getTitle());
                    tr.setScore(0.0f);
                    tr.setStatus(0);
                    if(ses.getUserid().equals("--")){
                        tr.setStatus(1);
                    }

                    tr.setTaskid(taskid);
                    tr.setUserid(ses.getUserid());
                    tr.setRoleid(ses.getRoleid());
                    tr.setCtime(ctime);
                    taskResultService.save(tr);
                }
                res = ResponseUtil.OK(null);
            }
        }else{
            res = ResponseUtil.FAIL("任务不存在！");
        }
        return res;
    }

    @RequestMapping("/results")
    private ResponseData GetTaskResults(@RequestParam(defaultValue = "1", name = "pageindex", required = false) Integer pageindex, @RequestParam(defaultValue = "10", name = "pagesize", required = false) Integer pagesize){
        Map<String, Object> maps = taskResultService.GetTaskResultList(pageindex, pagesize);
        List<YTaskResultEntity> results = (List<YTaskResultEntity>)maps.get("results");

        JSONArray arr = new JSONArray();
        for(YTaskResultEntity en: results){
            JSONObject obj = new JSONObject();
            obj.put("checkid", en.getCheckid());
            obj.put("checkname", en.getCheckname());
            obj.put("taskid", en.getTaskid());
            obj.put("ctime", en.getCtime());
            arr.add(obj);
        }
        maps.put("results", arr);
        return ResponseUtil.OK(maps);
    }

    @RequestMapping("/taskdetail/{checkid}")
    private ResponseData GetTaskDetail(@PathVariable String checkid){
        List<YTaskResultEntity> details = taskResultService.GetTaskDetails(checkid);
        JSONArray arr = new JSONArray();
        for(YTaskResultEntity res: details){
            JSONObject obj = new JSONObject();
            obj.put("checkname", res.getCheckname());
            obj.put("score", res.getScore());
            obj.put("finish", res.getStatus() == 1 ? "已完成" : "未完成");
            String roleid = res.getRoleid();
            YRoleEntity role = roleService.getById(roleid);
            if(role != null){
                obj.put("rolename", role.getDescription());
            }else{
                obj.put("rolename", "--");
            }
            String userid = res.getUserid();
            if(userid.equals("--")){
                obj.put("usercode", "--");
                obj.put("nickname", "--");
                obj.put("score", "--");
            }else{
                YUserEntity user = userService.getById(userid);
                if(user != null){
                    obj.put("usercode", user.getUsercode());
                    obj.put("nickname", user.getNickname());
                }else{
                    obj.put("usercode", "--");
                    obj.put("nickname", "--");
                    obj.put("score", "--");
                }
            }
            arr.add(obj);
        }

        return ResponseUtil.OK(arr);
    }

    @RequestMapping("/mytask")
    private ResponseData GetMyTask(){
        YUserEntity user = (YUserEntity) SecurityUtils.getSubject().getPrincipal();

        List<YTaskResultEntity> tasks = new ArrayList<>();
        if(user != null) {
            tasks = taskResultService.GetTaskResultByUserid(user.getId());
        }
        return ResponseUtil.OK(tasks);
    }

    @RequestMapping("/taskscore/{resid}")
    private ResponseData ScoreResult(@PathVariable String resid, @RequestParam Float score){
        YTaskResultEntity en = taskResultService.getById(resid);
        if(en != null){
            en.setStatus(1);
            en.setScore(score);
            taskResultService.updateById(en);
            //
            List<YTaskResultEntity> rens = taskResultService.GetRunningTask();
            // 任务结束了,删除所有session
            if(rens == null || rens.size() <= 0){
                YTaskFinishEntity exfin = taskFinishService.GetTaskByCheckId(en.getCheckid());
                if(exfin == null) {
                    YTaskFinishEntity finish = new YTaskFinishEntity();
                    finish.setCheckid(en.getCheckid());
                    finish.setTaskid(en.getTaskid());
                    finish.setCtime(new Date());
                    taskFinishService.save(finish);
                }
                sessionService.DeleteComputerSessions();
            }
        }
        return ResponseUtil.OK(null);
    }

    @RequestMapping("/runtask")
    private ResponseData GetRunningTask(){
        Object results = null;
        List<YTaskResultEntity> reses = taskResultService.GetRunningTask();
        if(reses != null && reses.size() > 0){
            YTaskResultEntity en = reses.get(0);
            ResponseData dat = GetTaskDetail(en.getCheckid());
            results = dat.getData();
        }
        return ResponseUtil.OK(results);
    }

    @RequestMapping("/lasttask")
    private ResponseData GetLastTask(){
        Object results = null;
        YTaskFinishEntity entity = taskFinishService.GetLastFinishTask();
        if(entity != null){
            ResponseData dat = GetTaskDetail(entity.getCheckid());
            results = dat.getData();
        }
        return ResponseUtil.OK(results);
    }
}
