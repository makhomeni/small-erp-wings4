package com.wings4.client;

import com.nepxion.swing.action.JSecurityAction;
import com.nepxion.swing.icon.IconFactory;
import com.nepxion.swing.topic.TopicContext;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ronnie
 * Date: 8/18/12
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class MaterialsController {

    public static JSecurityAction getCategoryAction(){
        JSecurityAction action = new JSecurityAction() {
            @Override
            public void execute(ActionEvent actionEvent) {
                MaterialsControlOutlook m = new MaterialsControlOutlook();
            }
        };
        return action;
    }

    public static JSecurityAction getProductAction(){
        JSecurityAction action = new JSecurityAction() {
            @Override
            public void execute(ActionEvent actionEvent) {
                new MaterialsControlOutlook().productExplore();
            }
        };
        return action;
    }



    public static JSecurityAction getAboutAction(){
        JSecurityAction action = new JSecurityAction("Look",
                IconFactory.getSwingIcon("about.png"), "About Nepxion Swing 1.0"){
            public void execute(ActionEvent e){
                Component topicComponent = TopicContext.getTopicComponent();
                if (topicComponent == null){
                    return;
                }

                topicComponent.setVisible(true);
            }
        };

        return action;
    }
}
