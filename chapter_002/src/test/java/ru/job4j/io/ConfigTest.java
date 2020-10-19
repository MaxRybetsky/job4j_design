package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/first.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Max Rybetsky")
        );
        assertThat(
                config.value("userid"),
                is("12")
        );
    }

    @Test
    public void whenPairWithCommentAndEmptyStrings() {
        String path = "./data/second.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Max Rybetsky")
        );
        assertThat(
                config.value("website"),
                is("vk.com")
        );
    }
}