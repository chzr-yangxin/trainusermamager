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

    @Autowired
    private YTaskRunService taskRunService;
    @Autowired
    private YTaskDetailService taskDetailService;

    @RequestMapping("/task")
    private ResponseData GetListTasks(){
        List<YTaskEntity> tasks = taskService.list();
        return ResponseUtil.OK(tasks);
    }

    @RequestMapping(value = "/sendtask/{taskid}", method = RequestMethod.POST)
    private ResponseData SendTask(@PathVariable String taskid, @RequestParam String tasktype){
        YTaskEntity task = taskService.getById(taskid);

        if(tasktype == null || tasktype.equals("")){
            tasktype = "实训";
        }

        ResponseData res = null;
        YTaskRunEntity runningTask = taskRunService.GetRunningTask();
        if(runningTask != null){
            res = ResponseUtil.FAIL("有正在执行的任务！");
        }else {
            if (task != null) {
                List<YSessionEntity> sesses = sessionService.list();
                if (sesses.size() != 5) {
                    res = ResponseUtil.FAIL("登录角色不完整！");
                } else {

                    String orderstr = task.getOrders();
                    String[] orderArr = orderstr.split(",");

                    Date ctime = new Date();
                    YTaskRunEntity tr = new YTaskRunEntity();
                    tr.setCtime(ctime);
                    tr.setFtime(ctime);
                    tr.setCurstep(0);
                    tr.setTaskid(task.getId());
                    tr.setTaskname(task.getTitle());
                    tr.setTaskstatus("进行中");
                    tr.setTasksteps(orderArr.length);
                    tr.setTasktype(tasktype);
                    taskRunService.save(tr);
                    for (int i = 0; i < orderArr.length; i++) {
                        String roleid = orderArr[i];
                        YTaskDetailEntity dt = new YTaskDetailEntity();
                        YSessionEntity ses = sessionService.GetSessionByRoleId(roleid);
                        if(ses != null){
                            dt.setCtime(ctime);
                            dt.setIscomputer(ses.getLogintype().equals("computerlogin") ? 1 : 0);
                            dt.setRoleid(roleid);
                            dt.setRunid(tr.getId());
                            dt.setScore(0.0f);
                            dt.setStatus(0);
                            dt.setTaskstep(i);
                            dt.setUserid(ses.getUserid());
                            taskDetailService.save(dt);
                        }
                    }
                    res = ResponseUtil.OK(null);
                }
            } else {
                res = ResponseUtil.FAIL("任务不存在！");
            }
        }
        return res;
    }

    @RequestMapping("/results")
    private ResponseData GetTaskResults(@RequestParam(defaultValue = "1", name = "pageindex", required = false) Integer pageindex, @RequestParam(defaultValue = "10", name = "pagesize", required = false) Integer pagesize){
        Map<String, Object> maps = taskRunService.GetFinishTaskes(pageindex, pagesize);
        List<YTaskRunEntity> results = (List<YTaskRunEntity>) maps.get("results");
        JSONArray arr = new JSONArray();
        for(YTaskRunEntity run: results){
            JSONObject obj = new JSONObject();
            obj.put("checkid", run.getId());
            obj.put("checkname", run.getTaskname());
            obj.put("taskid", run.getTaskid());
            obj.put("ctime", run.getFtime());
            arr.add(obj);
        }
        //        Map<String, Object> maps = taskResultService.GetTaskResultList(pageindex, pagesize);
//        List<YTaskResultEntity> results = (List<YTaskResultEntity>)maps.get("results");
//
//        JSONArray arr = new JSONArray();
//        for(YTaskResultEntity en: results){
//            JSONObject obj = new JSONObject();
//            obj.put("checkid", en.getCheckid());
//            obj.put("checkname", en.getCheckname());
//            obj.put("taskid", en.getTaskid());
//            obj.put("ctime", en.getCtime());
//            arr.add(obj);
//        }
        maps.put("results", arr);
        return ResponseUtil.OK(maps);
    }

    @RequestMapping("/taskdetail/{checkid}")
    private ResponseData GetTaskDetail(@PathVariable String checkid){
        List<YTaskDetailEntity> details = taskDetailService.GetDetailByRunId(checkid);
        JSONArray arr = new JSONArray();
        YTaskRunEntity runtask = taskRunService.getById(checkid);
        if(runtask != null) {
            for (YTaskDetailEntity res : details) {
                JSONObject obj = new JSONObject();
                obj.put("checkname", runtask.getTaskname());
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
        }

//        List<YTaskResultEntity> details = taskResultService.GetTaskDetails(checkid);
//        JSONArray arr = new JSONArray();
//        for(YTaskResultEntity res: details){
//            JSONObject obj = new JSONObject();
//            obj.put("checkname", res.getCheckname());
//            obj.put("score", res.getScore());
//            obj.put("finish", res.getStatus() == 1 ? "已完成" : "未完成");
//            String roleid = res.getRoleid();
//            YRoleEntity role = roleService.getById(roleid);
//            if(role != null){
//                obj.put("rolename", role.getDescription());
//            }else{
//                obj.put("rolename", "--");
//            }
//            String userid = res.getUserid();
//            if(userid.equals("--")){
//                obj.put("usercode", "--");
//                obj.put("nickname", "--");
//                obj.put("score", "--");
//            }else{
//                YUserEntity user = userService.getById(userid);
//                if(user != null){
//                    obj.put("usercode", user.getUsercode());
//                    obj.put("nickname", user.getNickname());
//                }else{
//                    obj.put("usercode", "--");
//                    obj.put("nickname", "--");
//                    obj.put("score", "--");
//                }
//            }
//            arr.add(obj);
//        }

        return ResponseUtil.OK(arr);
    }

    @RequestMapping("/mytask")
    private ResponseData GetMyTask(){
        YUserEntity user = (YUserEntity) SecurityUtils.getSubject().getPrincipal();

        JSONObject obj = new JSONObject();
        List<YTaskDetailEntity> tasks = new ArrayList<>();
        if(user != null) {
            tasks = taskDetailService.GetDetailByUserId(user.getId());
            if(tasks.size() > 0){
                YTaskRunEntity runtask = taskRunService.getById(tasks.get(0).getRunid());
                obj.put("task", runtask);
            }
            obj.put("mysteps", tasks);
        }
        return ResponseUtil.OK(obj);
    }

    @RequestMapping("/taskscore/{resid}")
    private ResponseData ScoreResult(@PathVariable String resid, @RequestParam Float score){
        YTaskDetailEntity dt = taskDetailService.getById(resid);
        if(dt != null){
            dt.setScore(score);
            dt.setStatus(1);
            taskDetailService.updateById(dt);
            //////
            Integer nextStep = dt.getTaskstep() + 1;
            YTaskRunEntity runtask = taskRunService.getById(dt.getRunid());
            if(runtask != null){
                Integer lastStep = runtask.getTasksteps();
                if(nextStep > lastStep){
                    // 任务执行完毕
                    runtask.setTaskstatus("已完成");
                    runtask.setFtime(new Date());
                    taskRunService.updateById(runtask);
                }else{
                    runtask.setCurstep(nextStep);
                    taskRunService.updateById(runtask);
                }
            }
        }

//        YTaskResultEntity en = taskResultService.getById(resid);
//        if(en != null){
//            en.setStatus(1);
//            en.setScore(score);
//            taskResultService.updateById(en);
//            //
//            List<YTaskResultEntity> rens = taskResultService.GetRunningTask();
//            // 任务结束了,删除所有session
//            if(rens == null || rens.size() <= 0){
//                YTaskFinishEntity exfin = taskFinishService.GetTaskByCheckId(en.getCheckid());
//                if(exfin == null) {
//                    YTaskFinishEntity finish = new YTaskFinishEntity();
//                    finish.setCheckid(en.getCheckid());
//                    finish.setTaskid(en.getTaskid());
//                    finish.setCtime(new Date());
//                    taskFinishService.save(finish);
//                }
//                sessionService.DeleteComputerSessions();
//            }
//        }
        return ResponseUtil.OK(null);
    }

    @RequestMapping("/runtask")
    private ResponseData GetRunningTask(){
        //Object results = null;
        YTaskRunEntity runtask = taskRunService.GetRunningTask();
//        List<YTaskResultEntity> reses = taskResultService.GetRunningTask();
//        if(reses != null && reses.size() > 0){
//            YTaskResultEntity en = reses.get(0);
//            ResponseData dat = GetTaskDetail(en.getCheckid());
//            results = dat.getData();
//        }
        return ResponseUtil.OK(runtask);
    }

    @RequestMapping("/lasttask")
    private ResponseData GetLastTask(){
        JSONObject obj = new JSONObject();
        YTaskRunEntity finishTask = taskRunService.GetLastFinishTask();
        if(finishTask != null){
            List<YTaskDetailEntity> details = taskDetailService.GetDetailByRunId(finishTask.getId());
            obj.put("detail", details);
            obj.put("task", finishTask);
        }
//        YTaskFinishEntity entity = taskFinishService.GetLastFinishTask();
//        if(entity != null){
//            ResponseData dat = GetTaskDetail(entity.getCheckid());
//            results = dat.getData();
//        }
        return ResponseUtil.OK(obj);
    }
}
