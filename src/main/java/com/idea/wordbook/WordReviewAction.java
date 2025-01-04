package com.idea.wordbook;

import cn.yiiguxing.plugin.translate.wordbook.WordBookService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.project.Project;

public class WordReviewAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        WordBookSettings settings = WordBookSettings.getInstance();
        int interval = settings.getInterval();
        int quantity = settings.getQuantity();
        String algorithm = settings.getAlgorithm();
        WordBookScheduler scheduler = ApplicationManager.getApplication().getService(WordBookScheduler.class);
        if (scheduler.isRunning()) {
            scheduler.stop();
            Messages.showMessageDialog(project, "WordBook Scheduler Stopped", "Info", Messages.getInformationIcon());
        } else {
            scheduler.start(interval, quantity, algorithm);
            Messages.showMessageDialog(project, "WordBook Scheduler Started", "Info", Messages.getInformationIcon());
        }
    }
}

