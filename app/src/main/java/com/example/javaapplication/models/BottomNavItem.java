package com.example.javaapplication.models;

import androidx.annotation.NonNull;
import androidx.compose.ui.graphics.painter.Painter;

import org.jetbrains.annotations.NotNull;

public class BottomNavItem {
    private @NotNull String label;
    private @NotNull Painter icon;
    private @NotNull String route;

    public BottomNavItem(@NotNull String label, @NotNull Painter painterResource, @NotNull String route) {
        this.label = label;
        this.icon = painterResource;
        this.route = route;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(@NonNull String label) {
        this.label = label;
    }

    public Painter getIcon() {
        return icon;
    }

    public void setIcon(@NonNull Painter icon) {
        this.icon = icon;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(@NonNull String route) {
        this.route = route;
    }
}
