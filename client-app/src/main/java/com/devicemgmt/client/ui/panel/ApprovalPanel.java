package com.devicemgmt.client.ui.panel;

import com.devicemgmt.client.service.ClientService;
import com.devicemgmt.client.ui.Styles;
import com.devicemgmt.client.ui.UIHelper;
import com.devicemgmt.common.dto.DashboardDTO;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ApprovalPanel extends VBox {
     private final ClientService svc;

    public ApprovalPanel(ClientService svc) {
        this.svc = svc;
        setStyle("-fx-background-color: " + Styles.CONTENT_BG + ";");
        setPadding(new Insets(24));
        setSpacing(20);
        buildUI();
    }

    private void buildUI() {
        Label title = UIHelper.titleLabel("📊  Approvals - Phê duyệt");
        getChildren().add(title);

        Label loading = new Label("Đang tải dữ liệu...");
        loading.setStyle("-fx-text-fill: " + Styles.TEXT_SECONDARY + ";");
        getChildren().add(loading);

        new Thread(() -> {
            DashboardDTO data = svc.getDashboardData();
            Platform.runLater(() -> {
                getChildren().remove(loading);
                // renderDashboard(data);
            });
        }).start();
    }

    
}
