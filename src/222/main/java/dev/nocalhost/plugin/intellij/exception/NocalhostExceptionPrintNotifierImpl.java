package dev.nocalhost.plugin.intellij.exception;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import dev.nocalhost.plugin.intellij.topic.NocalhostExceptionPrintNotifier;
import dev.nocalhost.plugin.intellij.ui.console.NocalhostErrorWindow;

/**
 * @author shihh
 * @since 2022/10/18
 */
public class NocalhostExceptionPrintNotifierImpl implements NocalhostExceptionPrintNotifier {

    private Project project;

    private ToolWindow toolWindow;

    public NocalhostExceptionPrintNotifierImpl (Project project, ToolWindow toolWindow) {
        this.project = project;
        this.toolWindow = toolWindow;
    }

    @Override
    public void action(String title, String contentMsg, String eMessage) {
        NocalhostErrorWindow nocalhostErrorWindow = new NocalhostErrorWindow(project, title, contentMsg, eMessage);
        ContentManager contentManager = toolWindow.getContentManager();
        Content content = ContentFactory.getInstance().createContent(nocalhostErrorWindow, nocalhostErrorWindow.getTitle(), false);
        content.setDisposer(nocalhostErrorWindow);
        contentManager.addContent(content);
        contentManager.setSelectedContent(content);
    }
}
