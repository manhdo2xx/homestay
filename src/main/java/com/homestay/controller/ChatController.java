package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.ChatEntity;
import com.homestay.entity.view.ChatView;
import com.homestay.service.ChatService;
import com.homestay.utils.MPUtil;
import com.homestay.utils.PageUtils;
import com.homestay.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ChatEntity chat,
                  HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("administrator")) {
            chat.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();

        PageUtils page = chatService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chat), params), params));

        return R.ok().put("data", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, ChatEntity chat,
                  HttpServletRequest request) {
        if (!request.getSession().getAttribute("role").toString().equals("administrator")) {
            chat.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();

        PageUtils page = chatService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chat), params), params));
        return R.ok().put("data", page);
    }

    @RequestMapping("/lists")
    public R list(ChatEntity chat) {
        EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();
        ew.allEq(MPUtil.allEQMapPre(chat, "chat"));
        return R.ok().put("data", chatService.selectListView(ew));
    }

    @RequestMapping("/query")
    public R query(ChatEntity chat) {
        EntityWrapper<ChatEntity> ew = new EntityWrapper<ChatEntity>();
        ew.allEq(MPUtil.allEQMapPre(chat, "chat"));
        ChatView chatView = chatService.selectView(ew);
        return R.ok("Query customer service chat successfully").put("data", chatView);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ChatEntity chat = chatService.selectById(id);
        chat = chatService.selectView(new EntityWrapper<ChatEntity>().eq("id", id));
        return R.ok().put("data", chat);
    }

    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        ChatEntity chat = chatService.selectById(id);
        chat = chatService.selectView(new EntityWrapper<ChatEntity>().eq("id", id));
        return R.ok().put("data", chat);
    }


    @RequestMapping("/save")
    public R save(@RequestBody ChatEntity chat, HttpServletRequest request) {
        chat.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(chat);
        if (StringUtils.isNotBlank(chat.getAsk())) {
            chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userId", request.getSession().getAttribute("userId")));
            chat.setUserid((Long) request.getSession().getAttribute("userId"));
            chat.setIsreply(1);
        }
        if (StringUtils.isNotBlank(chat.getReply())) {
            chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userId", chat.getUserid()));
            chat.setAdminid((Long) request.getSession().getAttribute("userId"));
        }
        chatService.insert(chat);
        return R.ok();
    }

    @RequestMapping("/add")
    public R add(@RequestBody ChatEntity chat, HttpServletRequest request) {
        chat.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        chat.setUserid((Long) request.getSession().getAttribute("userId"));
        if (StringUtils.isNotBlank(chat.getAsk())) {
            chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userId", request.getSession().getAttribute("userId")));
            chat.setUserid((Long) request.getSession().getAttribute("userId"));
            chat.setIsreply(1);
        }
        if (StringUtils.isNotBlank(chat.getReply())) {
            chatService.updateForSet("isreply=0", new EntityWrapper<ChatEntity>().eq("userId", chat.getUserid()));
            chat.setAdminid((Long) request.getSession().getAttribute("userId"));
        }
        chatService.insert(chat);
        return R.ok();
    }

    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChatEntity chat, HttpServletRequest request) {
        chatService.updateById(chat);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        chatService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
