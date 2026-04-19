package com.seamley.amazon.support;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class CucumberTeardownAllure {

    private CucumberTeardownAllure() {}

    public static void runTeardownAttachments(Scenario scenario, WebDriver driver, String platform) {
        if (driver == null || scenario == null) {
            return;
        }
        try {
            String safeBase = (platform + "_" + scenario.getName()).replaceAll("[^a-zA-Z0-9_-]", "_");
            Path dir = Paths.get(System.getProperty("user.dir"), "target", "screenshots");
            Files.createDirectories(dir);
            String imageFileName = safeBase + "_teardown.png";
            Path imagePath = dir.resolve(imageFileName);

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            if (Files.exists(src.toPath()) && !src.toPath().equals(imagePath)) {
                Files.deleteIfExists(src.toPath());
            }

            String summary =
                    "platform=" + platform
                            + "\nscenario=" + scenario.getName()
                            + "\nfailed=" + scenario.isFailed()
                            + "\nimagePath=" + imagePath.toAbsolutePath();
            Allure.attachment("teardown-summary.txt", summary);

            try (InputStream is = Files.newInputStream(imagePath)) {
                Allure.attachment(imageFileName, is);
            }

            byte[] png = Files.readAllBytes(imagePath);
            scenario.attach(png, "image/png", imageFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
