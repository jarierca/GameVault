package com.jarierca.gamevault.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "upload")
public interface UploadConfig {
	String dir();
}
