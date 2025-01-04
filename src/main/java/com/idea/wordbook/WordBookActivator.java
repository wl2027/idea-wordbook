package com.idea.wordbook;


import cn.yiiguxing.plugin.translate.wordbook.WordBookService;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.wm.IdeFrame;
import org.jetbrains.annotations.NotNull;

public class WordBookActivator implements ApplicationActivationListener {

    @Override
    public void applicationActivated(@NotNull IdeFrame ideFrame) {
        // 确保获取到 WordBookSettings 实例
        WordBookSettings settings = ServiceManager.getService(WordBookSettings.class);
        int interval = settings.getInterval();
        int quantity = settings.getQuantity();
        String algorithm = settings.getAlgorithm();
        WordBookScheduler scheduler = ApplicationManager.getApplication().getService(WordBookScheduler.class);
        scheduler.start(interval, quantity, algorithm);
    }

}

